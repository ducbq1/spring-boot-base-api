
package vn.vietinbank.evolve;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import vn.vietinbank.evolve.common.AppProperties;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@SpringBootApplication
@EnableRetry
@EnableAsync
@EnableAspectJAutoProxy
@ConfigurationPropertiesScan
public class EvolveTellerApplication implements CommandLineRunner {

    private final Environment environment;
    private final AppProperties appProperties;
    public EvolveTellerApplication(Environment environment, AppProperties appProperties) {
        this.environment = environment;
        this.appProperties = appProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(EvolveTellerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(environment.getProperty("spring.profiles.active"));
        Map<String, String> maps = new HashMap<>();

        String protocol = "http";
        String serverPort = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }

        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("The host name could not be determined, using `localhost` as fallback");
        }

        String inputArguments = String.join(" ", ManagementFactory.getRuntimeMXBean().getInputArguments());
        maps.put("runtime-args", formatInputArgument(inputArguments));

        // 1. Locale
        Locale locale = Locale.getDefault();

        // 2. Heap memory usage
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long max = heapMemoryUsage.getMax();
        long committed = heapMemoryUsage.getCommitted();
        long used = heapMemoryUsage.getUsed();
        long free = committed - used;

        // 3. Swagger URLs
        String baseUrl = String.format("%s://%s:%s%s%s", protocol, hostAddress, serverPort, contextPath, appProperties.getApiPrefix());
        String swaggerUi = baseUrl + "swagger-ui/index.html";
        String swaggerApiDocs = baseUrl + "v3/api-docs";
        String swaggerResources = baseUrl + "swagger-resources";

        log.info("🚀========================[ APPLICATION STARTED ]========================🚀");
        log.info("🌍 Locale                : {}", locale);
        log.info("🏠 Local URL:            : {}", baseUrl);
        log.info("📚 Swagger UI            : {}", swaggerUi);
        log.info("📄 Swagger API Docs      : {}", swaggerApiDocs);
        log.info("📦 Swagger Resources     : {}", swaggerResources);
        log.info("💾 Heap Max Size         : {}", formatMemorySize(max));
        log.info("💾 Heap Total (Committed): {}", formatMemorySize(committed));
        log.info("💾 Heap Used             : {}", formatMemorySize(used));
        log.info("💾 Heap Free             : {}", formatMemorySize(free));
        log.info("✅======================================================================✅");
    }


    private static String formatMemorySize(long bytes) {
        long kb = bytes / 1024;
        long mb = kb / 1024;
        return mb + " MB";
    }

    private static String formatInputArgument(String inputArguments) {
        StringBuilder formattedArguments = new StringBuilder();
        String[] arguments = inputArguments.split(",");
        for (String argument : arguments) {
            formattedArguments.append(argument).append("\n");
        }
        return formattedArguments.toString().trim();
    }

}

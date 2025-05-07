package vn.vietinbank.evolve.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerInfoPrinter implements CommandLineRunner {

    private final WebServerApplicationContext webServerAppCtxt;

    public ServerInfoPrinter(WebServerApplicationContext webServerAppCtxt) {
        this.webServerAppCtxt = webServerAppCtxt;
    }

    @Override
    public void run(String... args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String ipAddress = address.getHostAddress();
        int port = webServerAppCtxt.getWebServer().getPort();
        System.out.println("Server is running at http://" + ipAddress + ":" + port);
        System.out.println("Swagger UI is available at http://localhost:" + port + "/swagger-ui/index.html");
    }
}

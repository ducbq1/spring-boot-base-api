package vn.vietinbank.evolve.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.config")
@Getter
@Setter
public class AppProperties {
    private int defaultTimeout;
    private String languageDefault;
    private String apiPrefix;
}

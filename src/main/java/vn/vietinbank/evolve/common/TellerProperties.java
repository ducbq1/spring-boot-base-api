package vn.vietinbank.evolve.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.redis")
public class TellerProperties {
    private String tellerPrefix;
}

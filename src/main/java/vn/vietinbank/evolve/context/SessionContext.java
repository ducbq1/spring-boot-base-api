package vn.vietinbank.evolve.context;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
public class SessionContext {
    private String username;
    private String sessionToken;
    private String clientIp;
    private String userAgent;
    private Set<String> roles;
    private Instant loginAt;
    private Instant expiresAt;
    private String locale;
    private String deviceId;
    private boolean isAuthenticated;
}

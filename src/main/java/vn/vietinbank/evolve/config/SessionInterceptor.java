package vn.vietinbank.evolve.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vn.vietinbank.evolve.context.SessionContext;
import vn.vietinbank.evolve.context.SessionContextHolder;

import java.time.Instant;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        String username = request.getHeader("X-Username");
        String token = request.getHeader("Authorization");
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        SessionContext context = SessionContext.builder()
            .username(username)
            .sessionToken(token)
            .clientIp(clientIp)
            .userAgent(userAgent)
            .loginAt(Instant.now()) // hoặc lấy từ token
            .isAuthenticated(token != null)
            .build();

        SessionContextHolder.setContext(context);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        SessionContextHolder.clearContext();
    }
}

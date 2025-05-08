package vn.vietinbank.evolve.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class ApiPrefixConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {
            @Override
            protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
                Class<?> clazz = method.getDeclaringClass();
                if (clazz.isAnnotationPresent(RestController.class)) {
                    mapping = RequestMappingInfo.paths("/api").build().combine(mapping);
                }
                super.registerHandlerMethod(handler, method, mapping);
            }
        };
    }
}

package com.example.demo.config;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CorrelationIdFilter implements WebFilter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String correlationId = getOrCreateCorrelationId(exchange.getRequest());

        // Add Correlation ID to request for downstream use
        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header(CORRELATION_ID_HEADER, correlationId)
                .build();

        // Add it to MDC for logging (optional)
        MDC.put(CORRELATION_ID_HEADER, correlationId);

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        return chain.filter(mutatedExchange)
                .doFinally(signalType -> MDC.remove(CORRELATION_ID_HEADER));
    }

    private String getOrCreateCorrelationId(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        return headers.getFirst(CORRELATION_ID_HEADER) != null
                ? headers.getFirst(CORRELATION_ID_HEADER)
                : UUID.randomUUID().toString();
    }
}

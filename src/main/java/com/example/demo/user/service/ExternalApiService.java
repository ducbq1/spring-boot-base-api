package com.example.demo.user.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Retryable(
        value = { RuntimeException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000) // 2s giữa mỗi lần retry
    )
    public String callExternalApi() {
        System.out.println("Đang gọi API...");
        // Giả lập lỗi
        throw new RuntimeException("API error");
    }

    @Recover
    public String recover(RuntimeException e) {
        System.out.println("Tất cả retries thất bại. Thực hiện fallback.");
        return "Fallback response";
    }
}

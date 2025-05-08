package vn.vietinbank.evolve.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    @Async
    public void log(String action, Object[] args) {

    }

    @Async
    public void log(String action) {

    }
}

package vn.vietinbank.evolve.user.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vn.vietinbank.evolve.service.AuditService;
import vn.vietinbank.evolve.user.event.AuditEvent;

@Component
public class AuditEventListener {
    private final AuditService auditService;
    public AuditEventListener(AuditService auditService) {
        this.auditService = auditService;
    }

    @Async
    @EventListener
    public void onAuditEvent(AuditEvent event) {
        auditService.log(event.getAuditLog().action());
    }
}

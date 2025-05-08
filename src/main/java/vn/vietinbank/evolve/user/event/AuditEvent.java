package vn.vietinbank.evolve.user.event;

import org.springframework.context.ApplicationEvent;
import vn.vietinbank.evolve.common.AuditLog;

public class AuditEvent extends ApplicationEvent {
    private final AuditLog auditLog;
    public AuditEvent(Object source, AuditLog auditLog) {
        super(source);
        this.auditLog = auditLog;
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }
}

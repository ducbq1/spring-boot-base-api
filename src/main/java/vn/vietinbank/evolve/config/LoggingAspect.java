package vn.vietinbank.evolve.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import vn.vietinbank.evolve.common.AuditLog;
import vn.vietinbank.evolve.service.AuditService;

@Aspect
@Component
public class LoggingAspect {

    private final AuditService auditService;

    public LoggingAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @Before("execution(* vn.vietinbank..*Service.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🚀 Call: " + joinPoint.getSignature().getName());
    }

//    @Around("@annotation(AuditLog)")
//    public Object logAfter(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result = null;
//        Object[] args = joinPoint.getArgs();
//        String action = auditLog.action();
//
//        try {
//            result = joinPoint.proceed(args);
//            auditService.log(action, args);
//        } catch (Throwable e) {
//            auditService.log(action, args);
//            throw e;
//        }
//
//        return result;
//    }
}

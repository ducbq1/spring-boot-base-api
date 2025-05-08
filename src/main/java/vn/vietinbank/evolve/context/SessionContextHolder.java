package vn.vietinbank.evolve.context;

public class SessionContextHolder {

    private static final ThreadLocal<SessionContext> sessionContext = new ThreadLocal<>();

    public static SessionContext getContext() {
        return sessionContext.get();
    }

    public static void setContext(SessionContext context) {
        sessionContext.set(context);
    }

    public static void clearContext() {
        sessionContext.remove();
    }
}

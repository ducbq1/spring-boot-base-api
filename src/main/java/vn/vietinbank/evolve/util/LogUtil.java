package vn.vietinbank.evolve.util;

import org.slf4j.Logger;

public class LogUtil {
    public static void success(Logger log, String msg) {
        log.info("✅ {}", msg);
    }

    public static void error(Logger log, String msg) {
        log.error("❌ {}", msg);
    }

    public static void warn(Logger log, String msg) {
        log.warn("⚠️ {}", msg);
    }
}

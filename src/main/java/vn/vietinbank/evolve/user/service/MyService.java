package vn.vietinbank.evolve.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    // Tạo logger cho lớp
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    public void performAction() {
        logger.info("Thực hiện hành động...");
        try {
            // Code có thể gây lỗi
            throw new RuntimeException("Có lỗi xảy ra");
        } catch (Exception e) {
            logger.error("Lỗi khi thực hiện hành động", e); // Log lỗi
        }
    }
}

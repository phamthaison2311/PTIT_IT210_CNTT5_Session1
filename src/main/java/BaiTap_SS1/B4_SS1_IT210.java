package BaiTap_SS1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BÁO CÁO PHÂN TÍCH: LỰA CHỌN GIẢI PHÁP TIÊM PHỤ THUỘC (DI)
 * * So sánh giữa Constructor Injection và Field Injection:
 * +-------------------+---------------------------------------+---------------------------------------+
 * | Tiêu chí          | Constructor Injection                 | Field Injection                       |
 * +-------------------+---------------------------------------+---------------------------------------+
 * | Tính bất biến     | Có (Dùng được từ khóa final)          | Không (Field phải để mutable)         |
 * | Unit Test         | Dễ (Không cần Spring Container)       | Khó (Phải dùng Reflection/Mockito)    |
 * | Độ an toàn        | Đảm bảo đối tượng không bao giờ null  | Dễ bị NullPointerException tại runtime|
 * | Khuyến nghị       | Standard Best Practice (Spring 5+)    | Không nên dùng (Deprecated dần)       |
 * +-------------------+---------------------------------------+---------------------------------------+
 * * -> Constructor Injection.
 */


interface MessageSender {
    void send(String recipient, String content) throws Exception;
}

class EmailSender implements MessageSender {
    public void send(String recipient, String content) {
        System.out.println("[EMAIL] Gửi tới " + recipient + ": " + content);
    }
}

class SmsSender implements MessageSender {
    public void send(String recipient, String content) throws Exception {
        // Giả lập bẫy dữ liệu: Lỗi kết nối mạng SMS
        boolean isNetworkDown = true;
        if (isNetworkDown) {
            throw new Exception("Mất kết nối Gateway SMS!");
        }
        System.out.println("[SMS] Gửi tới " + recipient + ": " + content);
    }
}

@Service
public class B4_SS1_IT210 {
    private final MessageSender emailSender;
    private final MessageSender smsSender;

    // Constructor Injection: Tiêm phụ thuộc ngay khi khởi tạo
    @Autowired
    public B4_SS1_IT210(MessageSender emailSender, MessageSender smsSender) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    /**
     * Xử lý thông báo nạp tiền thành công
     */
    public void notifyRechargeSuccess(String username, double amount) {
        String message = "Tai khoan " + username + " da duoc nap " + amount + "đ.";

        // 1. Gửi Email
        try {
            emailSender.send(username, message);
        } catch (Exception e) {
            System.err.println("Lỗi gửi Email: " + e.getMessage());
        }

        // 2. Gửi SMS
        try {
            smsSender.send(username, message);
        } catch (Exception e) {
            // Log lỗi nhưng không chặn luồng nghiệp vụ chính
            System.err.println("[CẢNH BÁO HỆ THỐNG]: SMS Gateway gặp sự cố. " + e.getMessage());
            System.out.println("Gợi ý: Hệ thống sẽ thử lại sau hoặc gửi qua kênh dự phòng.");
        }
    }
}
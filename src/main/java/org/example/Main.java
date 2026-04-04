package org.example;

import org.example.config.AppConfig;
import org.example.model.SystemConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo Spring Container từ lớp cấu hình Java
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Lấy Bean SystemConfig từ IoC Container
        SystemConfig config = context.getBean(SystemConfig.class);

        // 3. In kết quả ra màn hình Console
        config.display();
    }
}
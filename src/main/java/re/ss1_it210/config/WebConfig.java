//package re.ss1_it210.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import re.ss1_it210.config.utils.InputMethod;
//
//@Configuration // đay là lớp cấu hình
//@EnableWebMvc
//@ComponentScan(basePackages = {"re.ss1_it210"}) // quét các lớp trong gói để tạo bean
//public class WebConfig { // Nơi cấu hình ứng dụng
//    // Bean là thành phần cốt lõi cảu ứng dụng
//    // Tuân thủ nguyên tắc IOC: các đói tượng được tạo và quản lý trong Spring Container
//    // C1: sử dụng @Bean trong lớp cấu hình
////    @Bean // đánh dấu trên phương thức tạo bean
////    public InputMethod inputMethod() {
////        return new InputMethod();
////    }
//
//    // C2: sử dụng 4 chú thích @Component, @Service, @Repository, @Controller trên lớp đối tượng
//
//    // DI: lấy ra đối tượng bean trong container
//
//
//    // Cấu hình Bean giao diện
////    @Bean
////    public ViewResolver viewResolver() {
////        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
////        viewResolver.setPrefix("/views/"); // Đường dẫn đến thư mục chứa các view
////        viewResolver.setSuffix(".jsp");
////        return viewResolver;
////    }
//}

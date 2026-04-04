package org.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

    @Value("PTIT - Chi nhánh Hà Nội")
    private String branchName;

    @Value("08:00 AM")
    private String openingTime;

    // Chỉ cần hàm display() để in ra Console
    public void display() {
        System.out.println("Branch Name: " + branchName);
        System.out.println("Opening Time: " + openingTime);
    }
}
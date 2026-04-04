package BaiTap_SS1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * BÁO CÁO PHÂN TÍCH & THIẾT KẾ GIẢI PHÁP: ORDER FOOD SERVICE
 * * 1. Input: userId (Long), foodId (Long), quantity (int)
 * 2. Output: Thông báo đặt hàng thành công hoặc ném ra ngoại lệ (Exception) cụ thể.
 * 3. Logic xử lý:
 * - B1: Kiểm tra tồn kho qua InventoryRepository.
 * - B2: Tính tổng tiền dựa trên giá món ăn.
 * - B3: Kiểm tra số dư tài khoản qua UserAccountRepository.
 * - B4: Thực hiện trừ kho và trừ tiền đồng thời (Atomic).
 */



interface InventoryRepository {
    int getStock(Long foodId);
    double getPrice(Long foodId);
    void updateStock(Long foodId, int newStock);
}

interface UserAccountRepository {
    double getBalance(Long userId);
    void deductMoney(Long userId, double amount);
}


@Service
public class B3_SS1_IT210 {

    private final InventoryRepository inventoryRepo;
    private final UserAccountRepository userAccountRepo;

    // Sử dụng Constructor Injection - Spring IoC sẽ tự tiêm các bean phù hợp vào đây
    @Autowired
    public B3_SS1_IT210(InventoryRepository inventoryRepo, UserAccountRepository userAccountRepo) {
        this.inventoryRepo = inventoryRepo;
        this.userAccountRepo = userAccountRepo;
    }

    /**
     * Phương thức xử lý đặt đồ ăn với cơ chế "Bẫy dữ liệu"
     */
    public void placeOrder(Long userId, Long foodId, int quantity) throws Exception {

        // 1. Kiểm tra lỗi kho
        int currentStock = inventoryRepo.getStock(foodId);
        if (currentStock < quantity) {
            throw new Exception("LỖI NGHIỆP VỤ: Món ăn này đã hết hoặc không đủ số lượng trong kho!");
        }

        // 2. Tính toán tài chính
        double itemPrice = inventoryRepo.getPrice(foodId);
        double totalCost = itemPrice * quantity;

        // 3. Kiểm tra lỗi tài khoản
        double currentBalance = userAccountRepo.getBalance(userId);
        if (currentBalance < totalCost) {
            throw new Exception("LỖI NGHIỆP VỤ: Số dư tài khoản không đủ (Thiếu: " + (totalCost - currentBalance) + "đ)");
        }

        // 4. Thực thi cập nhật dữ liệu
        inventoryRepo.updateStock(foodId, currentStock - quantity);
        userAccountRepo.deductMoney(userId, totalCost);

        System.out.println("--- ĐẶT MÓN THÀNH CÔNG ---");
        System.out.println("User ID: " + userId + " | Món: " + foodId + " | SL: " + quantity);
        System.out.println("Tổng chi phí: " + totalCost + "đ đã được trừ vào tài khoản.");
    }
}
package lk.ijse.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private String OrderID;
    private String ItemID;
    private String details;
    private int qty;
    private double unitPrice;
    private String itemID;

    public OrderDetail(String orderID, String itemCode, String details, int qty, double unitPrice) {
    }
}
package lk.ijse.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetail {
    private String OrderID;
    private String ItemID;
    private String details;
    private int qty;
    private double unitPrice;


}
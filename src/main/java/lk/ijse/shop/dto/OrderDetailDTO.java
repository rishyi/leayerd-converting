package lk.ijse.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private String OrderID;
    private String ItemID;
    private String details;
    private int qty;
    private double unitPrice;

}
package lk.ijse.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private String id;
    private String itemName;
    private String qtyOnHand;
    private String details;
    public  double unitPrice;

}

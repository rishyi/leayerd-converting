package lk.ijse.shop.model.ItemTm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CartTm {
    private String itemCode;
    private String Name;
    private double QtyOnHand;
    private String details;
    private double UnitPrice;
    private double total;
    private JFXButton btnRemove;
}

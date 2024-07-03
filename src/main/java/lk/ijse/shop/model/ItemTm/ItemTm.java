package lk.ijse.shop.model.ItemTm;

import lombok.*;


@Setter
@Getter
public class ItemTm {
    private String id;
    private String itemName;
    private String qtyOnHand;
    private String details;
    private double unitPrice;

    public ItemTm(String id, String itemName, String qtyOnHand, String details, double unitPrice) {
        this.id = id;
        this.itemName = itemName;
        this.qtyOnHand = qtyOnHand;
        this.details = details;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", details='" + details + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

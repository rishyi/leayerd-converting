package lk.ijse.shop.model;

public class Item {

    private String id;
    private String itemName;
    private String qtyOnHand;
    private String details;
    public  double unitPrice;



    public Item(String id, String itemName, String qtyOnHand, String details , double unitPrice) {
        this.id = id;
        this.itemName = itemName;
        this.qtyOnHand = qtyOnHand;
        this.details = details;
        this.unitPrice = unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", details='" + details + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

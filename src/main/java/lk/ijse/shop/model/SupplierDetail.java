package lk.ijse.shop.model;

public class SupplierDetail {

    private String i_id;
    private String s_id;
    private String qty;
    private String description;

    public SupplierDetail(String i_id, String s_id, String qty, String description) {
        this.i_id = i_id;
        this.s_id = s_id;
        this.qty = qty;
        this.description = description;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SupplierDetail{" +
                "i_id='" + i_id + '\'' +
                ", s_id='" + s_id + '\'' +
                ", qty='" + qty + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

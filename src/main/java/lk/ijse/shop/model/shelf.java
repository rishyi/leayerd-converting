package lk.ijse.shop.model;

public class shelf {

    private String id;
    private String category;
    private String size;
    private String i_id;

    public shelf(String id, String category, String size, String i_id) {
        this.id = id;
        this.category = category;
        this.size = size;
        this.i_id = i_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    @Override
    public String toString() {
        return "shelf{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", i_id='" + i_id + '\'' +
                '}';
    }
}

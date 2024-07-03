package lk.ijse.shop.model;

public class Employee {

    private String id;
    private String name;
    private String address;
    private String o_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public Employee(String id, String name, String address, String o_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.o_id = o_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", o_id='" + o_id + '\'' +
                '}';
    }
}

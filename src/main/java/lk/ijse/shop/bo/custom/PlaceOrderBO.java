package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.model.PlaceOrder;
import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public  boolean placeOrder(PlaceOrder po) throws SQLException;
}

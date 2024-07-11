package lk.ijse.shop.dao.custom;

import lk.ijse.shop.dao.CrudDAO;
import lk.ijse.shop.model.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    public String getCurrentId() throws SQLException,ClassNotFoundException;
}

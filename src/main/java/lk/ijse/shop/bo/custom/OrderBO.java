package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.dto.OrderDTO;
import lk.ijse.shop.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    public boolean addOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    public boolean updateOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<Order> findAllOrders() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;
}

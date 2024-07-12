package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.OrderDAO;
import lk.ijse.shop.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrders = new ArrayList<>();
        ResultSet rst =  SQLUtil.execute("SELECT * FROM orders");
        while (rst.next()){
            Order order = new Order(rst.getString(1),rst.getString(2),rst.getDate(3),rst.getString(4));
            allOrders.add(order);
        }
        return allOrders;
    }

    @Override
    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?,?,?,?)",entity.getId(),entity.getDetails(),entity.getDate(),entity.getC_id());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE orders SET details = ? , date = ? , c_id =? WHERE o_id=?",entity.getDetails(),entity.getDate(),entity.getC_id(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM orders WHERE o_id = ?",id);
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT o_id FROM orders ORDER BY o_id DESC LIMIT 1");
        if (rst.next()){
            String orderId = rst.getString(1);
            return orderId;
        }
        return null;
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select count(*) as order_count from orders");
        if (rst.next()){
            return rst.getInt("order_count");
        }
        return 0;
    }
}

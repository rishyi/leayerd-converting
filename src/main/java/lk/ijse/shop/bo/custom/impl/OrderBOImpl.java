package lk.ijse.shop.bo.custom.impl;
import lk.ijse.shop.bo.custom.OrderBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.OrderDAO;
import lk.ijse.shop.dto.OrderDTO;
import lk.ijse.shop.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<Order> findAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrders = new ArrayList<>();
        ArrayList<Order> orders = orderDAO.findAll();
        for (Order o : orders){
            allOrders.add(new Order(o.getId(),o.getDetails(),o.getDate(),o.getC_id()));
        }
        return allOrders;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }

    @Override
    public boolean addOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.add(new Order(order.getId(),order.getDetails(),order.getDate(),order.getC_id()));
    }

    @Override
    public boolean updateOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(order.getId(),order.getDetails(),order.getDate(),order.getC_id()));
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }
}

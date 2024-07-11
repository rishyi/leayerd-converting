package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.OrderDetailDAO;
import lk.ijse.shop.model.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> findAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");    }

    @Override
    public boolean add(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetails (o_id,i_id,details,qty,unit_price) VALUES (?,?,?,?,?)",entity.getOrderID(),entity.getItemID(),entity.getDetails(),entity.getQty(),entity.getUnitPrice());
    }

    @Override
    public boolean save(List<OrderDetail> odList) throws SQLException,ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isSaved = add(od);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");    }
}

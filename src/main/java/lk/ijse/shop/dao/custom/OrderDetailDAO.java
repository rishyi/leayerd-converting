package lk.ijse.shop.dao.custom;

import lk.ijse.shop.dao.CrudDAO;
import lk.ijse.shop.model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    public boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;

}

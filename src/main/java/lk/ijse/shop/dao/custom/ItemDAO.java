package lk.ijse.shop.dao.custom;

import lk.ijse.shop.dao.CrudDAO;
import lk.ijse.shop.model.Item;
import lk.ijse.shop.model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    public  List<String> findAllItemIds() throws SQLException,ClassNotFoundException;
    public boolean qtyUpdate (String itemCode,int qty) throws SQLException,ClassNotFoundException;

}

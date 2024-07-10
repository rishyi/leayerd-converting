package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.dto.ItemDTO;
import lk.ijse.shop.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO entity) throws SQLException, ClassNotFoundException;

    Item searchItem(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException,ClassNotFoundException;

    public ArrayList<Item> findAll() throws SQLException, ClassNotFoundException;

    public List<String> findAllItemIds() throws SQLException, ClassNotFoundException;

}

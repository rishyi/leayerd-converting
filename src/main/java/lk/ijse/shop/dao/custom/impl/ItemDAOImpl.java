package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.ItemDAO;
import lk.ijse.shop.model.Customer;
import lk.ijse.shop.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",item.getId(),item.getItemName(),item.getQtyOnHand(),item.getDetails(),item.getUnitPrice());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET item_name = ?, qty_on_hand = ?, details = ? , unit_price = ? WHERE i_id = ?",entity.getItemName(),entity.getQtyOnHand(),entity.getDetails(),entity.getUnitPrice(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE i_id=?",id);
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE i_id=?", id + "");
        rst.next();
        return new Item(id + "", rst.getString("item_name"),rst.getString("qty_on_hand"),rst.getString("details"),rst.getDouble("unit_price"));
    }

    @Override
    public List<String> getIds() throws SQLException {
        return List.of();
    }
}

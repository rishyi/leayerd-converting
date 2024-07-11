package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.bo.custom.ItemBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.ItemDAO;
import lk.ijse.shop.dto.ItemDTO;
import lk.ijse.shop.model.Customer;
import lk.ijse.shop.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

   ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(item.getId(),item.getItemName(),item.getQtyOnHand(),item.getDetails(),item.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getId(),itemDTO.getItemName(),itemDTO.getQtyOnHand(),itemDTO.getDetails(),itemDTO.getUnitPrice()));
    }

    @Override
    public Item searchItem(String id) throws SQLException, ClassNotFoundException {
        Item i = itemDAO.search(id);
        return new Item(i.getId(),i.getItemName(),i.getQtyOnHand(),i.getDetails(),i.getUnitPrice());
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public ArrayList<Item> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem =  new ArrayList<>();
        ArrayList<Item> all = itemDAO.findAll();
        for (Item i : all){
            allItem.add(new Item(i.getId(),i.getItemName(),i.getQtyOnHand(),i.getDetails(),i.getUnitPrice()));
        }
        return allItem;
    }

    @Override
    public List<String> findAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.findAllItemIds();
    }


}

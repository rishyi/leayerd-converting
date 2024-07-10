package lk.ijse.shop.dao.custom;

import lk.ijse.shop.dao.CrudDAO;
import lk.ijse.shop.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    public List<String> getCustomerTelephone() throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;

}

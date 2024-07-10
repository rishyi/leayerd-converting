package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.dto.CustomerDTO;
import lk.ijse.shop.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public boolean addCustomer (CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public ArrayList<Customer> findAll() throws SQLException, ClassNotFoundException ;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;

    List<String> getCustomerTelephone() throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;
}

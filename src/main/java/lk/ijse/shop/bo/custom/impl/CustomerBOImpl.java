package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.bo.custom.CustomerBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.CustomerDAO;
import lk.ijse.shop.dto.CustomerDTO;
import lk.ijse.shop.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer (CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customer.getId(),customer.getName(),customer.getTelephone()));
    }

    @Override
    public ArrayList<Customer> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.findAll();
        for(Customer c: all){
            allCustomers.add(new Customer(c.getId(),c.getName(),c.getTelephone()));
        }
        return allCustomers;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getTelephone()));
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
       Customer c = customerDAO.search(id);
       return new Customer(c.getId(),c.getName(),c.getTelephone());

    }

    @Override
    public List<String> getCustomerTelephone() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerTelephone();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

}

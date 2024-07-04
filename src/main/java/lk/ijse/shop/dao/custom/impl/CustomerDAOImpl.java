package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl {

    public ArrayList<Customer> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("c_id"), rst.getString("name"), rst.getString("telephone"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    public boolean add(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("INSERT INTO customer (c_id,name,telephone) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getTelephone());
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("UPDATE customer SET name=?,telephone=? WHERE c_id=?", entity.getId(),entity.getName(),entity.getTelephone());
    }
    public boolean searchById(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM customer WHERE c_id=?",entity.getId(),entity.getName(),entity.getTelephone());
    }
}

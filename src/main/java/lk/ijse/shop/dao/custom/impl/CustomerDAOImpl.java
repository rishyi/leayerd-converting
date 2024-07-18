package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.CustomerDAO;
import lk.ijse.shop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO  {

    @Override
    public ArrayList<Customer> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("c_id"), rst.getString("name"), rst.getString("telephone"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("INSERT INTO customer (c_id,name,telephone) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getTelephone());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("UPDATE customer SET name=?,telephone=? WHERE c_id=?", entity.getName(),entity.getTelephone(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE c_id=?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("telephone"));
    }

    @Override
    public List<String> getCustomerTelephone() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT telephone FROM CUSTOMER");
        List<String> telephoneList = new ArrayList<>();
        while (rst.next()) {
            String telephone = rst.getString(1);
            telephoneList.add(telephone);
        }
        return telephoneList;
    }
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT c_id FROM CUSTOMER");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String c_id = resultSet.getString(1);
            idList.add(c_id);
        }
        return idList;
    }

//    For Home Page
    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("select count(*) as customer_count from customer");
        if (rs.next()){
            return rs.getInt("customer_count");
        }
        return 0;
    }
}

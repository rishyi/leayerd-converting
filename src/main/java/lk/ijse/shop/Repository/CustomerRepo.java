package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean addCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO customer VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,customer.getId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(3,customer.getTelephone());

        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name=?,telephone=? WHERE c_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,customer.getName());
        preparedStatement.setObject(2,customer.getTelephone());
        preparedStatement.setObject(3,customer.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    public static List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            String c_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String telephone = resultSet.getString(3);

            Customer customer = new Customer(c_id,name,telephone);
            customers.add(customer);
        }
        return customers;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT c_id FROM CUSTOMER";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
                String c_id = resultSet.getString(1);
                idList.add(c_id);
        }
        return idList;
    }

    public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE c_id=?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String c_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String telephone = resultSet.getString(3);

            Customer customer = new Customer(c_id,name,telephone);

            return customer;
        }
        return null;
    }

    public static List<String> getCustomerTelephone() throws SQLException {
        String sql = "SELECT telephone FROM CUSTOMER";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> telephoneList = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
                String telephone = resultSet.getString(1);
                telephoneList.add(telephone);
        }
        return telephoneList;
    }
}

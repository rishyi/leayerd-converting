package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.model.Customer;

import java.sql.SQLException;

public class CustomerDAOImpl {


    public boolean add(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("INSERT INTO customer (c_id,name,telephone) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getTelephone());
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("UPDATE customer SET name=?,telephone=? WHERE c_id=?", entity.getId(),entity.getName(),entity.getTelephone());
    }
}

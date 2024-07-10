package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.SupplierDAO;
import lk.ijse.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        while (rst.next()){
            Supplier supplier = new Supplier(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
            suppliers.add(supplier);
        }
        return suppliers;
    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier (s_id,name,telephone,description) VALUES (?,?,?,?)",entity.getId(),entity.getName(),entity.getTelephone(),entity.getDescription());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name=?,telephone=?,description=? WHERE s_id=?",entity.getName(),entity.getTelephone(),entity.getDescription(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE s_id=?",id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

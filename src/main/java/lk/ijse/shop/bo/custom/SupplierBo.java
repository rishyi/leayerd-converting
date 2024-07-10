package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.dto.SupplierDTO;
import lk.ijse.shop.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBO {
    public ArrayList<Supplier> findAll() throws SQLException, ClassNotFoundException;

    public boolean addSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
}

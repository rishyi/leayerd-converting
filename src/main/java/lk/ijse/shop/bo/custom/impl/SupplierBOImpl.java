package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.bo.custom.SupplierBo;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.SupplierDAO;
import lk.ijse.shop.dto.SupplierDTO;
import lk.ijse.shop.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBo {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<Supplier> findAll() throws SQLException, ClassNotFoundException {
      ArrayList<Supplier> allSuppliers = new ArrayList<>();
      ArrayList<Supplier> all = supplierDAO.findAll();
      for (Supplier s : all){
          allSuppliers.add(new Supplier(s.getId(),s.getName(),s.getTelephone(),s.getDescription()));
      }
      return allSuppliers;
    }

    @Override
    public boolean addSupplier(SupplierDTO sup) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(sup.getId(),sup.getName(),sup.getTelephone(),sup.getDescription()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO sup) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(sup.getId(),sup.getName(),sup.getTelephone(),sup.getDescription()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
}

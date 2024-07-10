package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;
import lk.ijse.shop.dto.PayementDTO;
import lk.ijse.shop.model.Payement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    public boolean addPayment(PayementDTO entity) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PayementDTO entity) throws SQLException, ClassNotFoundException;

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<Payement> findAllPayment() throws SQLException, ClassNotFoundException;
}

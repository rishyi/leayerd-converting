package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.bo.custom.PaymentBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.PaymentDAO;
import lk.ijse.shop.dto.PayementDTO;
import lk.ijse.shop.model.Payement;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean addPayment(PayementDTO pay) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payement(pay.getId(),pay.getPrice(),pay.getDate(),pay.getO_id()));
    }

    @Override
    public boolean updatePayment(PayementDTO pay) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payement(pay.getId(),pay.getPrice(),pay.getDate(),pay.getO_id()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public ArrayList<Payement> findAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payement> allPayments = new ArrayList<>();
        ArrayList<Payement> payments = paymentDAO.findAll();
        for (Payement p : payments){
            allPayments.add(new Payement(p.getId(),p.getPrice(),p.getDate(),p.getO_id()));
        }
        return allPayments;
    }
}

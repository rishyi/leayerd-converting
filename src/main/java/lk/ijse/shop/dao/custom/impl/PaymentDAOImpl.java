package lk.ijse.shop.dao.custom.impl;

import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.PaymentDAO;
import lk.ijse.shop.model.Payement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payement> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payement> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payement");
        while (rst.next()){
            Payement payement = new Payement(rst.getString(1),rst.getDouble(2),rst.getDate(3),rst.getNString(4));
            allPayments.add(payement);
        }
        return allPayments;
    }

    @Override
    public boolean add(Payement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payement (p_id,price,date,o_id) VALUES (?,?,?,?)",entity.getId(),entity.getPrice(),entity.getDate(),entity.getO_id());
    }

    @Override
    public boolean update(Payement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payement SET price=?,date=?,o_id=? WHERE p_id=?",entity.getPrice(),entity.getDate(),entity.getO_id(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payement WHERE p_id=?",id);
    }

    @Override
    public Payement search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.Payement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean save(Payement payement) throws SQLException {
        String sql = "INSERT INTO payement VALUES(?,?,?,?)";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ps.setObject(1,payement.getId());
        ps.setObject(2,payement.getPrice());
        ps.setObject(3,payement.getDate());
        ps.setObject(4,payement.getO_id());

        return ps.executeUpdate() > 0;
    }

    public static boolean update(Payement payement) throws SQLException {
        String sql = "UPDATE payement SET price=?,date=?,o_id=? WHERE p_id=?";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ps.setObject(1,payement.getPrice());
        ps.setObject(2,payement.getDate());
        ps.setObject(3,payement.getO_id());
        ps.setObject(4,payement.getId());

        return ps.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM payement WHERE p_id=?";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setObject(1,id);

        return ps.executeUpdate() > 0;
    }

    public static List<Payement> findAll() throws SQLException {
        String sql = "SELECT * FROM payement";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Payement> payments = new ArrayList<Payement>();

        while (rs.next()) {
            String p_id = rs.getString(1);
            double price = rs.getDouble(2);
            Date date = rs.getDate(3);
            String o_id = rs.getString(4);

            Payement payment = new Payement(p_id,price,date,o_id);
            payments.add(payment);
        }
        return payments;
    }
}

package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepo {
    public static boolean save(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isSaved = save(od);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetail od) throws SQLException {
        String sql = "INSERT INTO orderdetails VALUES(?,?,?,?,?)";
        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ps.setString(1,od.getOrderID());
        ps.setString(2,od.getItemID());
        ps.setString(3,od.getDetails());
        ps.setInt(4,od.getQty());
        ps.setDouble(5,od.getUnitPrice());

        return ps.executeUpdate() > 0;

    }

}

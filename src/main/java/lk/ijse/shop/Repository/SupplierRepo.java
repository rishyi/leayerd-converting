package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES (?,?,?,?)";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setObject(1,supplier.getId());
        ps.setObject(2,supplier.getName());
        ps.setObject(3,supplier.getTelephone());
        ps.setObject(4,supplier.getDescription());

        return ps.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET name=?,telephone=?,description=? WHERE s_id=?";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ps.setObject(1,supplier.getName());
        ps.setObject(2,supplier.getTelephone());
        ps.setObject(3,supplier.getDescription());
        ps.setObject(4,supplier.getId());

        return ps.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE s_id=?";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setObject(1,id);

        return ps.executeUpdate() > 0;
    }

    public static List<Supplier> findAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Supplier> suppliers = new ArrayList<>();

        while (rs.next()) {
            String supId = rs.getString(1);
            String supName = rs.getString(2);
            String supTelephone = rs.getString(3);
            String supDescription = rs.getString(4);

            Supplier supplier = new Supplier(supId,supName,supTelephone,supDescription);
            suppliers.add(supplier);

        }
        return suppliers;
    }

}

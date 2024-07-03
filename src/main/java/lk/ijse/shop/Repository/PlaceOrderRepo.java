package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.Save(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.update(po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
                    if (isOrderDetailSaved) {
                        conn.commit();
                        return true;
                    }
                }
            }
            conn.rollback();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }
}

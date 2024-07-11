package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.Repository.ItemRepo;
import lk.ijse.shop.bo.BOFactory;
import lk.ijse.shop.bo.custom.OrderBO;
import lk.ijse.shop.bo.custom.PlaceOrderBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.OrderDAO;
import lk.ijse.shop.dao.custom.OrderDetailDAO;
import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public  boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.add(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.update(po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = orderDetailDAO.save(po.getOdList());
                    if (isOrderDetailSaved) {
                        conn.commit();
                        return true;
                    }
                }
            }
            conn.rollback();
            return false;
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }
}

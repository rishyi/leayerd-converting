package lk.ijse.shop.dao.custom;

import lk.ijse.shop.dao.SuperDAO;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    public boolean checkCredintialUser(String userId, String password) throws SQLException, IOException, ClassNotFoundException;
}

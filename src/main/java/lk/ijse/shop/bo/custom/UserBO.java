package lk.ijse.shop.bo.custom;

import lk.ijse.shop.bo.SuperBO;

import java.io.IOException;
import java.sql.SQLException;

public interface UserBO extends SuperBO {

    public boolean checkCredintialUser(String userId, String password) throws SQLException, IOException, ClassNotFoundException;
}

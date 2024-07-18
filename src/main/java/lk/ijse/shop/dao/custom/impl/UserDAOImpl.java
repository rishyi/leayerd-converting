package lk.ijse.shop.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.shop.dao.SQLUtil;
import lk.ijse.shop.dao.custom.UserDAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean checkCredintialUser(String userId, String password) throws SQLException, IOException, ClassNotFoundException {
        String sql =  "SELECT u_id,password FROM users WHERE u_id = ?";

        try (ResultSet resultSet = SQLUtil.execute(sql,userId)){
            if (resultSet.next()){
                String pas = resultSet.getString("password");
                return pas.equals(password);
            }
        }
        return false;
    }
}

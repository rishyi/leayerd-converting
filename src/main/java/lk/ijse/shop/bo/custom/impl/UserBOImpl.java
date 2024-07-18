package lk.ijse.shop.bo.custom.impl;

import lk.ijse.shop.bo.custom.UserBO;
import lk.ijse.shop.dao.DAOFactory;
import lk.ijse.shop.dao.custom.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkCredintialUser(String userId, String password) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.checkCredintialUser(userId,password);
    }
}

package lk.ijse.shop.dao;

import lk.ijse.shop.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null ) ? daoFactory= new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER
    }

    public SuperDAO getDAO(DAOTypes Types) {

        return null;
    }

}

package lk.ijse.shop.dao;

import lk.ijse.shop.dao.custom.impl.*;

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
        switch (Types){
            case CUSTOMER :
               return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }

    }

}

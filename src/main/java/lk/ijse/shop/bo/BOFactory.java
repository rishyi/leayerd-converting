package lk.ijse.shop.bo;

import lk.ijse.shop.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM,SUPPLIER,PAYMENT,ORDER,ORDERDETAIL,USER
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDERDETAIL:
                return new PlaceOrderBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}

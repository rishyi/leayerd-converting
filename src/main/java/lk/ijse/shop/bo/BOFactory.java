package lk.ijse.shop.bo;

import lk.ijse.shop.bo.custom.impl.CustomerBOImpl;
import lk.ijse.shop.bo.custom.impl.ItemBOImpl;
import lk.ijse.shop.bo.custom.impl.PaymentBOImpl;
import lk.ijse.shop.bo.custom.impl.SupplierBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM,SUPPLIER,PAYMENT,ORDER
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
            default:
                return null;
        }
    }
}

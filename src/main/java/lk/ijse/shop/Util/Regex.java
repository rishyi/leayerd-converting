package lk.ijse.shop.Util;

import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean textFieldValidate(lk.ijse.shop.Util.TextField textField, String text){
        String field = "";

        switch(textField){
            case NAME:
                field = "[A-z|\\\\s]{3,}$";
                break;
            case ADDRESS:
                field =  "[A-Za-z0-9'\\.\\-\\s\\,]";
                break;
            case ID:
                field = "^([A-Z][0-9]{3})$";
                break;
            case TELEPHONE:
                field = "^[0-9]{10}$";
                break;
            case QTY:
                field = "[0-9]+$";
                break;
            case DATE:
                field = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
                break;
            case DETAILS:
                field = "[a-zA-Z_0-9]+";
                break;
            case PRICE:
                field = "[0-9]+$";
                break;
        }
        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if(text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(lk.ijse.shop.Util.TextField location,TextField textField ){
        if (Regex.textFieldValidate(location,textField.getText())){
                textField.setStyle("-fx-text-fill: green; -fx-border-color: green;");
                return true;
        }else {
            textField.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            return false;
        }
    }
}

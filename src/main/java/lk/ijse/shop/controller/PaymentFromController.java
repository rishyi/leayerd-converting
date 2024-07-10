package lk.ijse.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.Repository.PaymentRepo;
import lk.ijse.shop.Util.Regex;
import lk.ijse.shop.bo.BOFactory;
import lk.ijse.shop.bo.custom.PaymentBO;
import lk.ijse.shop.dto.PayementDTO;
import lk.ijse.shop.model.ItemTm.PaymentTm;
import lk.ijse.shop.model.Order;
import lk.ijse.shop.model.Payement;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PaymentFromController {
    public static  String netTotal = "";
    public static String orderid ="" ;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPrice;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() {
        setCellValuerFactory();
        loadAllPayments();
        setamountnoid();
    }

    private void setamountnoid() {
        txtOrderId.setText(orderid);
        txtPrice.setText(netTotal);
    }

    private void setCellValuerFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("o_id"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<Payement> payementList = paymentBO.findAllPayment();
            for (Payement payement: payementList) {
                PaymentTm paymentTm = new PaymentTm(
                        payement.getId(),
                        payement.getPrice(),
                        payement.getDate(),
                        payement.getO_id()
                );
                obList.add(paymentTm);
            }
            tblPayment.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddPayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        double price = Double.parseDouble(txtPrice.getText());
        Date date = Date.valueOf(txtDate.getText());
        String o_id = txtOrderId.getText();

        if (isValid()) {
            try {
                boolean isSaved = paymentBO.addPayment(new PayementDTO(paymentId,price,date,o_id));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Successfully Added").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }finally {
                loadAllPayments();
            }
            int i = Integer.parseInt(netTotal);
            System.out.println(i);
            if (i != 0) {
                Stage stage = (Stage) rootNode.getScene().getWindow();
                stage.close();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Insert Valid date").show();
        }

    }

    @FXML
    void btnClearFields(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtDate.clear();
        txtOrderId.clear();
        txtPaymentId.clear();
        txtPrice.clear();
    }

    @FXML
    void btnDeletePayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            boolean isDeleted = paymentBO.deletePayment(paymentId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully Deleted").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally {
            loadAllPayments();
        }
    }

    @FXML
    void btnUpdatePayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        double price = Double.parseDouble(txtPrice.getText());
        Date date = Date.valueOf(txtDate.getText());
        String o_id = txtOrderId.getText();

        if (paymentId.isEmpty() || o_id.isEmpty() ) {
            new Alert(Alert.AlertType.INFORMATION,"Payment Id or Order Id Empty").show();
            return;
        }
        if(Double.isNaN(price)) {
            new Alert(Alert.AlertType.INFORMATION,"Payment Price enter").show();
            return;
        }
        try {
            boolean isUpdated = paymentBO.updatePayment(new PayementDTO(paymentId,price,date,o_id));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully Updated").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally {
            loadAllPayments();
        }
    }

    @FXML
    void getAllDetails(MouseEvent event){
        tblPayment.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                txtPaymentId.setText(newSelection.getId());
                txtPrice.setText(String.valueOf(newSelection.getPrice()));
                txtDate.setText(String.valueOf(newSelection.getDate()));
                txtOrderId.setText(newSelection.getO_id());
            }
        });
    }

    @FXML
    void itemSearchOnAction(ActionEvent event) {

    }

    public void txtPayOrderIdOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtOrderId);
    }

    public void txtPayPriceOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.PRICE,txtPrice);
    }

    public void txtPayDateOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.DATE,txtDate);
    }

    public void txtPayIdOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtOrderId);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtOrderId)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtPaymentId)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.PRICE,txtPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.DATE,txtDate)) return false;

        return true;
    }
}

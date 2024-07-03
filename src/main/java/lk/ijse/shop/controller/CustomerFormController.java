package lk.ijse.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.Repository.CustomerRepo;
import lk.ijse.shop.Util.Regex;
import lk.ijse.shop.model.Customer;
import lk.ijse.shop.model.ItemTm.CustomerTm;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> ColID;


    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColNumber;

    @FXML
    private TextField txtCusID;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusTele;

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
    }

    private void setCellValueFactory() {
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadAllItems() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.findAll();
            for (Customer customer : customerList) {
                CustomerTm customerTm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getTelephone()
                );
                obList.add(customerTm);
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddCustomer(ActionEvent event) {
        String cusID = txtCusID.getText();
        String cusName = txtCusName.getText();
        String cusTele = txtCusTele.getText();

        if (cusID.isEmpty() || cusName.isEmpty()) {
        new Alert(Alert.AlertType.ERROR,"Customer ID and Name cannot be empty").show();
        return;
        }

        if (isValid()){
        Customer customer = new Customer(cusID, cusName, cusTele);

        try {
            boolean isAded = CustomerRepo.addCustomer(customer);
            if (isAded) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Added Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            loadAllItems();
        }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Not match insert types").show();
        }
    }

    @FXML
    void btnClearFields(ActionEvent event) {
        clearFields();
    }
    private void clearFields(){
        txtCusID.clear();
        txtCusName.clear();
        txtCusTele.clear();
    }

    @FXML
    void btnUpdateCustomer(ActionEvent event) {
        String cusID = txtCusID.getText();
        String cusName = txtCusName.getText();
        String cusTele = txtCusTele.getText();

        if (cusID.isEmpty() || cusName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Customer ID and Name cannot be empty").show();
        }
        if (cusTele.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Customer Telephone Number cannot be empty").show();
        }

        Customer customer = new Customer(cusID, cusName, cusTele);

        try {
            boolean isUpdated = CustomerRepo.updateCustomer(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            loadAllItems();
        }

    }

    public void getAllDetails(MouseEvent mouseEvent) {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtCusID.setText(newSelection.getId());
                txtCusName.setText(newSelection.getName());
                txtCusTele.setText(newSelection.getPhone());
            }
        });
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtCusID);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtCusName);
    }

    public void txtCustomerTeleOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.TELEPHONE,txtCusTele);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtCusID)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtCusName)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.TELEPHONE,txtCusTele)) return false;

        return true;
    }
}

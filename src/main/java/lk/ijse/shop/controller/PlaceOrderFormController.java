package lk.ijse.shop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.shop.Repository.CustomerRepo;
import lk.ijse.shop.Repository.ItemRepo;
import lk.ijse.shop.Repository.OrderRepo;
import lk.ijse.shop.Repository.PlaceOrderRepo;
import lk.ijse.shop.model.*;
import lk.ijse.shop.model.ItemTm.CartTm;
import lk.ijse.shop.model.ItemTm.CustomerTm;
import lombok.SneakyThrows;
import org.controlsfx.control.textfield.TextFields;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFormController {

    @FXML
    private TableColumn<?, ?> ColQtyOnHand;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<String> cmbCustomerID;


    @FXML
    private TextField customerContactfield;

    @FXML
    private JFXComboBox<String> cmbItemID;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDetail;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;


    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDetails;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTotal;


    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane rootNode;


    @FXML
    private TableView<CartTm> tblOrderCart;


    @FXML
    private TextField txtQty;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerId();
        getItemId();
        setCellValueFactory();
        loadCustomerAllTel();
    }

    private void loadCustomerAllTel(){
        try {
            List<String> cusTel = CustomerRepo.getCustomerTelephone();
            String[] posibleName =cusTel.toArray(new String[0]);

            TextFields.bindAutoCompletion(customerContactfield,posibleName);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDetail.setCellValueFactory(new PropertyValueFactory<>("details"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ItemRepo.findAllItemId();
            for (String id : idList) {
                obList.add(id);
            }
            cmbItemID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getIds();

            for (String id : idList) {
                obList.add(id);
            }
            cmbCustomerID.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentID = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentID);
            lblOrderID.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("");
            int idNum = Integer.parseInt(split[3]);
            String x = String.valueOf(idNum + 1);
            split[3] = x;
            String join = String.join("",split);
            return join ;
        }
        return "O00";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddCartToOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();
        String name = lblItemName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String details = lblDetails.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colItemCode.getCellData(i))){

                CartTm tm = obList.get(i);
                qty += tm.getQtyOnHand();
                total = qty * unitPrice;

                tm.setQtyOnHand(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();

                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code,name,qty,details,unitPrice,total,btnRemove);
        obList.add(tm);

        for (int i = 0; i < obList.size(); i++) {
            int x = i ;
            obList.get(i).getBtnRemove().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            obList.get(i).getBtnRemove().setTextFill(Color.WHITE);
            obList.get(i).getBtnRemove().setOnAction(actionEvent -> {
                obList.remove(x);
                calculateNetTotal();
                tblOrderCart.refresh();
            });
        }

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

    private void  calculateNetTotal(){
        int netTotal = 0;
        for (int i=0; i<tblOrderCart.getItems().size(); i++){
            netTotal += (double)colTotal.getCellData(i);
        }
        lblTotal.setText(String.valueOf(netTotal));
    }

    @SneakyThrows
    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        String orderID = lblOrderID.getText();
        String  orderDetails = lblDetails.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        String cusId = lblCustomerID.getText();

        ArrayList<OrderDetail> arrayList = new ArrayList<>();
        Order order = new Order(orderID,orderDetails,date,cusId);

        for (int i = 0; i < obList.size(); i++) {
            int qty = (int) obList.get(i).getQtyOnHand();
            arrayList.add(new OrderDetail(orderID,obList.get(i).getItemCode(),obList.get(i).getDetails(),qty,obList.get(i).getUnitPrice()));
        }

        PlaceOrder placeOrder = new PlaceOrder(order,arrayList);
        boolean b = PlaceOrderRepo.placeOrder(placeOrder);
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Saved").show();
            Stage stage =new Stage();
            PaymentFromController.netTotal = lblTotal.getText();
            PaymentFromController.orderid = lblOrderID.getText();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/payement_form.fxml"))));
            stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Save failed").show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerID.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);

            lblCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();

        try {
            Item item = ItemRepo.searchById(code);
            if (item != null){
                lblItemName.setText(item.getItemName());
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                lblDetails.setText(item.getDetails());
                lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            }
            txtQty.requestFocus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @SneakyThrows
    @FXML
    void onCustomerContactTyping(KeyEvent event) {
        String text = customerContactfield.getText();
        List<Customer> all = CustomerRepo.findAll();
        for (Customer customer : all) {
            if (customer.getTelephone().equals(text)){
                lblCustomerName.setText(customer.getName());
                lblCustomerID.setText(customer.getId());
            }
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddCartToOnAction(event);
    }

}

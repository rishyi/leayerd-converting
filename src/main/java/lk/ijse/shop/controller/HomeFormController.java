package lk.ijse.shop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.bo.BOFactory;
import lk.ijse.shop.bo.custom.CustomerBO;
import lk.ijse.shop.bo.custom.ItemBO;
import lk.ijse.shop.bo.custom.OrderBO;
import lk.ijse.shop.db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {
    public AnchorPane rootNode;

    public AnchorPane mainRootNod;
    public Label lblItemCount;
    public Label lblCustomerCount;
    public Label lblOrderCount;
    public Label dateAndTime;
    private int customerCount;
    private int itemCount;
    private int orderCount;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerCount = customerBO.getCustomerCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            itemCount = itemBO.getItemCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            orderCount = orderBO.getOrderCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(customerCount);
        setItemCount(itemCount);
        setOrderCount(orderCount);

        runTime();
    }

    public void runTime(){
        new Thread(){
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        dateAndTime.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }


    private void setItemCount(int itemCount) {
        lblItemCount.setText(String.valueOf(itemCount));
    }

    private  void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    public void btnOnBackToLogin(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.centerOnScreen();

    }

    public void btnItemManage(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(rootNode);
    }

    public void btnCustomerManage(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form .fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);

    }

    public void btnDashBoard(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnHome(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(anchorPane);
    }

    public void btnSupplierManage(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnPayement(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payement_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnOrders(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/orders_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/reports_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }
}

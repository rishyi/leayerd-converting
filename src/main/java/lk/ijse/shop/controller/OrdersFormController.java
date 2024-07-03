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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shop.Repository.OrderRepo;
import lk.ijse.shop.model.ItemTm.OrderTm;
import lk.ijse.shop.model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class OrdersFormController {

    public TextField txtOrderID;
    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderDetail;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    void getAllDetails(MouseEvent event) {
        tblOrders.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtOrderID.setText(newSelection.getId());
            }
        });
    }

    public void initialize() {
        setCellValuerFactory();
        loadAllOrders();

    }

    private void setCellValuerFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderDetail.setCellValueFactory(new PropertyValueFactory<>("details"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("c_id"));
    }

    private void loadAllOrders() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<Order> orderList = OrderRepo.GetAll();
            for (Order order : orderList) {
                OrderTm orderTm = new OrderTm(
                        order.getId(),
                        order.getDetails(),
                        order.getDate(),
                        order.getC_id()
                );
                obList.add(orderTm);
            }
            tblOrders.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOrders(ActionEvent actionEvent) {
            String OrderId = txtOrderID.getText();

            try {
                boolean isDeleted = OrderRepo.delete(OrderId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Order Deleted").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }finally {
                loadAllOrders();
            }
    }

}

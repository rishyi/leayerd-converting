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
import lk.ijse.shop.Repository.ItemRepo;
import lk.ijse.shop.Util.Regex;
import lk.ijse.shop.model.Item;
import lk.ijse.shop.model.ItemTm.ItemTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    public TableColumn<?,?> colUnitPrice;

    @FXML
    public TextField txtUnitPrice;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colDetails;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    public void initialize(){
        setCellValuerFactory();
        loadAllItems();
    }

    private void setCellValuerFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<Item> itemList = ItemRepo.findAll();
            for (Item item : itemList) {
                ItemTm itemTm = new ItemTm(
                        item.getId(),
                        item.getItemName(),
                        item.getQtyOnHand(),
                        item.getDetails(),
                        item.getUnitPrice()
                );
                obList.add(itemTm);
            }
            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddItem(ActionEvent event) {
        String itemCode = txtCode.getText();
        String itemName = txtName.getText();
        String itemQty = txtQty.getText();
        String itemDescription = txtDescription.getText();
        double itemUnitPrice = Double.parseDouble(txtUnitPrice.getText());

        if (itemCode.isEmpty() || itemName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Item code and name are required").show();
            return;
        }
        if (itemQty.isEmpty() || itemDescription.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Quantity and description are required").show();
            return;
        }

        if (isValid()) {

            Item item = new Item(itemCode, itemName, itemQty, itemDescription, itemUnitPrice);

            try {
                boolean isSaved = ItemRepo.save(item);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item added successfully").show();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                loadAllItems();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Insert valid data").show();
        }
    }

    @FXML
    void itemSearchOnAction(ActionEvent event) throws SQLException {
        String itemCode = txtCode.getText();

        Item item = ItemRepo.searchById(itemCode);
        if (item != null) {
            txtCode.setText(item.getId());
            txtName.setText(item.getItemName());
            txtQty.setText(item.getQtyOnHand());
            txtDescription.setText(item.getDetails());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        }else {
                new Alert(Alert.AlertType.INFORMATION, "Item not found").show();
        }

    }

    @FXML
    void btnUpdateItem(ActionEvent event) {
        String itemCode = txtCode.getText();
        String itemName = txtName.getText();
        String itemQty = txtQty.getText();
        String itemDescription = txtDescription.getText();
        double itemUnitPrice = Double.parseDouble(txtUnitPrice.getText());

        if (itemCode.isEmpty() || itemName.isEmpty() || itemQty.isEmpty() || itemDescription.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Pleas Fill The Blanks").show();
            return;
        }

        Item item = new Item(itemCode, itemName, itemQty, itemDescription, itemUnitPrice);

        try {
            boolean isUpdated = ItemRepo.update(item);
            if (isUpdated) {
                tblItem.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }finally {
            loadAllItems();
        }
    }

    @FXML
    void btnDeleteItem(ActionEvent event) {
        String itemCode =txtCode.getText();

        try {
            boolean isDeleted = ItemRepo.delete(itemCode);
            if (isDeleted) {

                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllItems();
        }

    }

    private void clearFields(){
        txtCode.clear();
        txtName.clear();
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
    }

    public void btnClearFields(ActionEvent actionEvent) {
        clearFields();
    }

    public void getAllDetails(MouseEvent mouseEvent) {
        tblItem.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtCode.setText(newSelection.getId());
                txtName.setText(newSelection.getItemName());
                txtQty.setText(newSelection.getQtyOnHand());
                txtDescription.setText(newSelection.getDetails());
                txtUnitPrice.setText(String.valueOf(newSelection.getUnitPrice()));
            }
        });
    }

    public void txtItemPriceOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.PRICE,txtUnitPrice);
    }

    public void txtItemNameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtName);
    }

    public void txtItemQtyOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.QTY,txtQty);
    }

    public void txtItemDetailOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.DETAILS,txtDescription);
    }

    public void txtItemCodeOnkey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtCode);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtCode)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.QTY,txtQty)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.DETAILS,txtDescription)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.PRICE,txtUnitPrice)) return false;

        return true;

    }
}

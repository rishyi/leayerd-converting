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
import lk.ijse.shop.Util.Regex;
import lk.ijse.shop.bo.BOFactory;
import lk.ijse.shop.bo.custom.CustomerBO;
import lk.ijse.shop.bo.custom.SupplierBo;
import lk.ijse.shop.dto.SupplierDTO;
import lk.ijse.shop.model.ItemTm.SupplierTm;
import lk.ijse.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierFormControll {

    @FXML
    private TableColumn<?, ?> colDetails;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtSupMobile;

    SupplierBo supplierBo = (SupplierBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize() {
        setCellValuerFactory();
        loadAllSupplier();
    }

    private void setCellValuerFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = supplierBo.findAll();
            for (Supplier supplier : supplierList) {
                SupplierTm supplierTm = new SupplierTm(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getTelephone(),
                        supplier.getDescription()
                );
                obList.add(supplierTm);
            }
            tblSupplier.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddSupplier(ActionEvent event) {
        String supplierId = txtSupId.getText();
        String supplierName = txtName.getText();
        String supplierMobile = txtSupMobile.getText();
        String supplierDescription = txtDescription.getText();

        if (supplierId.isEmpty() || supplierName.isEmpty() ){
            new Alert(Alert.AlertType.INFORMATION,"Supplier ID and Supplier Name Required").show();
            return;
        }
        if (supplierMobile.isEmpty() || supplierDescription.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Mobile Number Required").show();
        }

        if (isValid()) {
          try {
                boolean isSaved =  supplierBo.addSupplier(new SupplierDTO(supplierId,supplierName,supplierMobile,supplierDescription));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Successfully").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                loadAllSupplier();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Insert Valid Data").show();
        }
    }

    private void clearFields(){
        txtDescription.clear();
        txtName.clear();
        txtSupId.clear();
        txtSupMobile.clear();
    }

    @FXML
    void btnClearFields(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteSup(ActionEvent event) {
        String supId = txtSupId.getText();

        try {
            boolean isDeleted = supplierBo.deleteSupplier(supId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted Successfully").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally {
            loadAllSupplier();
        }
    }

    @FXML
    void btnUpdateSup(ActionEvent event) {
        String supId = txtSupId.getText();
        String supName = txtName.getText();
        String supMobile = txtSupMobile.getText();
        String supDescription = txtDescription.getText();

        if (supId.isEmpty() || supName.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Name or Supplier Mobile is Required").show();
            return;
        }
        if (supMobile.isEmpty() || supDescription.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Name or Supplier Mobile is Required").show();
            return;
        }
        try {
            boolean isUpdated  = supplierBo.updateSupplier(new SupplierDTO(supId,supName,supMobile,supDescription));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated Successfully").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally {
            loadAllSupplier();
        }

    }

    @FXML
    void getAllDetails(MouseEvent event) {
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null){
                txtName.setText(newSelection.getName());
                txtSupId.setText(newSelection.getId());
                txtDescription.setText(newSelection.getDescription());
                txtSupMobile.setText(newSelection.getTelephone());
            }
        });
    }

    @FXML
    void itemSearchOnAction(ActionEvent event) {

    }

    public void txtSupNameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtName);
    }

    public void txtSupDescriptionOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.DETAILS,txtDescription);
    }

    public void txtSupTeleOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.TELEPHONE,txtSupMobile);
    }

    public void txtSupIdOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtSupId);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.ID,txtSupId)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.DETAILS,txtDescription)) return false;
        if (!Regex.setTextColor(lk.ijse.shop.Util.TextField.TELEPHONE,txtSupMobile)) return false;

        return true;
    }
}

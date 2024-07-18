package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.bo.BOFactory;
import lk.ijse.shop.bo.custom.UserBO;
import lk.ijse.shop.bo.custom.impl.UserBOImpl;
import lk.ijse.shop.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    public TextField txtUserId;
    public TextField txtPassword;
    public AnchorPane rootNode;
    public PasswordField txtPasswordField;
    public CheckBox loginCheckBox;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void linkRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    public void btnOnLoginAction(ActionEvent actionEvent) throws IOException, SQLException {
        String userId = txtUserId.getText();
        String password = txtPassword.getText();

        if (!(loginCheckBox.isSelected())){
            password = txtPasswordField.getText();
        }else {
            password = txtPassword.getText();
        }

      try {
          checkCredintial(userId,password);
      }catch (SQLException e){
          new Alert(Alert.AlertType.ERROR,e.getMessage());
      } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
      }
    }

    private void checkCredintial(String userId, String password) throws SQLException, IOException, ClassNotFoundException {
        boolean isValidLogin = userBO.checkCredintialUser(userId,password);
        if (isValidLogin){
            navigateToHomePage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Credential");
        }
    }

    public void navigateToHomePage() throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/home_form.fxml"));
        Scene scene = new Scene(rootnode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();
    }

    public void btnForgetPassword(ActionEvent actionEvent) {

    }

    public void loginShowPassword(ActionEvent actionEvent) {
        if (loginCheckBox.isSelected()) {
            txtPassword.setText(txtPasswordField.getText());
            txtPassword.setVisible(true);
            txtPasswordField.setVisible(false);
        }else {
            txtPasswordField.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtPasswordField.setVisible(true);

        }
    }
}

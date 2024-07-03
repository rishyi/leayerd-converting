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
      }
    }

    private void checkCredintial(String userId, String password) throws SQLException, IOException {
        String sql = "SELECT u_id,password FROM users WHERE u_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String dbpwd = rs.getString("password");
            if (password.equals(dbpwd)) {
                navigateToHomePage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Enter user ID and Password").show();
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

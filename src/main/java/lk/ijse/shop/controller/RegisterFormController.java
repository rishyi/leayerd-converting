package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.db.DbConnection;

import java.awt.image.DataBuffer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {
    public TextField txtUserID;
    public TextField txtName;
    public TextField txtPassword;
    public AnchorPane rootNode;

    public void btnRegisterUser(ActionEvent actionEvent) {
        String userID = txtUserID.getText();
        String Name = txtName.getText();
        String password = txtPassword.getText();

        if (userID.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a User ID").show();
            return;
        }
        if (Name.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a Name").show();
            return;
        }
        if (password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter password").show();
            return;
        }

        try {
            boolean isSaved = saveUser(userID,Name,password);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved").show();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean saveUser(String userID, String Name, String password) throws SQLException {
        String sql = "INSERT INTO users VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userID);
        preparedStatement.setObject(2, Name);
        preparedStatement.setObject(3, password);

        return preparedStatement.executeUpdate() > 0;
    }

    public void btnBackToLogin(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }
}

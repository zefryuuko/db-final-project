package ui.authentication;

import database.Auth;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;

public class login_pageController {
    public Button signIn;
    public TextField idField;
    public PasswordField passwordField;

    public void clickSignIn() throws IOException {
        try {
            Integer.parseInt(idField.getText());
            if (Auth.authenticate(Integer.parseInt(idField.getText()), passwordField.getText())) {
                Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
                Stage stage = (Stage) signIn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect password").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Staff ID must be numeric").showAndWait();
        }
    }
}

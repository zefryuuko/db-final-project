package ui.authentication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class login_pageController {
    public Button signIn;

    public void clickSignIn() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Stage stage = (Stage) signIn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}

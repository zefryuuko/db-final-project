package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.staff.staffController;

import java.io.IOException;

public class logout_modalController {
    public Button cancel;
    public Button logout;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("authentication/login_page.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }
}

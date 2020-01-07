package ui.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class add_staffController {
    public Button cancel;
    public Button addStaff;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickAddStaff() throws IOException {
        // Do mysql stuff here.
        Stage stage = (Stage) addStaff.getScene().getWindow();
        stage.close();
    }
}

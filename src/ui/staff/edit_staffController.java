package ui.staff;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class edit_staffController {
    public Button cancel;
    public Button done;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        // Do mysql stuff here.
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

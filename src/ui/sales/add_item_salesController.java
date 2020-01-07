package ui.sales;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class add_item_salesController {
    private salesController sC;

    public void setSC(salesController sC) {
        this.sC = sC;
    }

    public Button cancel;
    public Button done;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        // Do mysql stuff here.
        sC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public void refreshBox() {

    }
}

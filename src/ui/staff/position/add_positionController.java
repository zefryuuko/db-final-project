package ui.staff.position;

import database.Purchase;
import database.Staff;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Main;
import ui.inventory.inventoryController;
import ui.staff.position.positionController;

import java.io.IOException;

public class add_positionController {
    private positionController pC;

    public TextField positionName;

    public Button cancel;
    public Button addPosition;

    public void setPC(positionController pC) {
        this.pC = pC;
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickAddPosition() throws IOException {
        Staff.addStaffPosition(positionName.getText());
        pC.refreshTable();
        Stage stage = (Stage) addPosition.getScene().getWindow();
        stage.close();
    }
}

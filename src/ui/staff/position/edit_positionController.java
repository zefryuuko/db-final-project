package ui.staff.position;

import database.Staff;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.staff.position.positionController;

import java.io.IOException;

public class edit_positionController {
    private positionController pC;
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int id) {
        this.id = id;
    }

    public TextField positionName;

    public Button cancel;
    public Button done;

    public void refreshBox() {
        positionName.setText(name);
    }

    public void setPC(positionController pC) {
        this.pC = pC;
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        Staff.updateStaffPosition(id, positionName.getText());
        pC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

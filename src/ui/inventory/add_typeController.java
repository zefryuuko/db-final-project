package ui.inventory;

import database.Inventory;
import database.Purchase;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Main;

import java.io.IOException;

public class add_typeController {
    private inventoryController iC;

    public TextField typeName;

    public Button cancel;
    public Button addType;

    public void setIC(inventoryController iC) {
        this.iC = iC;
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickAddType() throws IOException {
        Inventory.addItemType(typeName.getText());
        iC.refreshTable();
        Stage stage = (Stage) addType.getScene().getWindow();
        stage.close();
    }
}

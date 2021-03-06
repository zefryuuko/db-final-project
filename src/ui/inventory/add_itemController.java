package ui.inventory;

import database.Inventory;
import database.Purchase;
import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Main;
import ui.staff.add_staffController;

import java.io.IOException;
import java.util.HashMap;

public class add_itemController {
    private int id;
    private inventoryController iC;
    private String name2;

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public TextField name;
    public TextField purchaseCost;
    public TextField buyCount;

    public Button cancel;
    public Button done;

    public void setIC(inventoryController iC) {
        this.iC = iC;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void refreshBox() {
        name.setText(name2);
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        Purchase.addExistingItemPurchase(id, Main.staffID, Integer.parseInt(buyCount.getText()), Integer.parseInt(purchaseCost.getText()));
        iC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

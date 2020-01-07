package ui.inventory;

import database.Inventory;
import database.Purchase;
import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Main;
import ui.staff.add_staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class new_itemController {
    private int id;
    private inventoryController iC;

    public ComboBox<Box> type;
    public TextField name;
    public TextField stored;
    public TextField vendor;
    public TextField purchaseCost;
    public TextField buyCount;
    public CheckBox sellable;

    public Button cancel;
    public Button done;

    public void setIC(inventoryController iC) {
        this.iC = iC;
    }

    public void setID(int id) {
        this.id = id;
    }

    public static class Box {
        private int id;
        private String name;

        public Box(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String toString() {
            return name;
        }
    }

    public void refreshBox() {
        HashMap<Integer, String> list = Inventory.getItemTypes();
        ObservableList<Box> obs = FXCollections.observableArrayList();
        for (int key : list.keySet()) {
            obs.add(new Box(key, list.get(key)));
        }
        type.setItems(obs);
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

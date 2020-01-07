package ui.sales;

import database.Sales;
import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.staff.add_staffController;

import java.io.IOException;
import java.util.HashMap;

public class add_salesController {
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
        Sales.addSales(Integer.parseInt(staffID.getText()), customerName.getText(), salesType.getSelectionModel().getSelectedItem().getId());
        sC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public TextField staffID, customerName, totalSales;
    public ComboBox<Box> salesType;

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
        HashMap<Integer, String> posList = Sales.getSalesTypes();
        ObservableList<Box> obs = FXCollections.observableArrayList();
        for (int key : posList.keySet()) {
            obs.add(new Box(key, posList.get(key)));
        }
        salesType.setItems(obs);
    }
}

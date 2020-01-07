package ui.sales;

import database.Sales;
import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class edit_salesController {
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
        try {
            Sales.updateSales(Integer.parseInt(staffID.getText()), customerName.getText(), salesType.getSelectionModel().getSelectedItem().getId());
            sC.refreshTable();
            Stage stage = (Stage) done.getScene().getWindow();
            stage.close();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Sales type must not be empty").showAndWait();
        }
    }

    public TextField staffID, customerName;
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
        HashMap<Integer, String> list = Sales.getSalesTypes();
        ObservableList<Box> obs = FXCollections.observableArrayList();
        for (int key : list.keySet()) {
            obs.add(new Box(key, list.get(key)));
        }
        salesType.setItems(obs);
        staffID.setText(String.valueOf(staffIDtext));
        customerName.setText(customerNametext);
//        for (int i = 0; i < obs.size(); i++) {
//            salesType.getSelectionModel().select(i);
//            if (salesType.getSelectionModel().getSelectedItem().getId() == Sales.getSalesDetails(id)) {
//                break;
//            }
//        }
    }

    public void setStaffIDtext(String staffIDtext) {
        this.staffIDtext = staffIDtext;
    }

    public void setCustomerNametext(String customerNametext) {
        this.customerNametext = customerNametext;
    }

    private String customerNametext, staffIDtext;

    public void setSalesTypetext(String salesTypetext) {
        this.salesTypetext = salesTypetext;
    }

    private String salesTypetext;

    private int id;

    public void setId(int id) {
        this.id = id;
    }
}

package ui.logistics;

import database.Logistics;
import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Main;
import ui.staff.add_staffController;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class add_logisticsController {
    public TextField customerAddress;
    public TextField trackingNumber;
    public ComboBox<Box> bill;
    public ComboBox<Box> logisticsProvider;
    public Button cancel;
    public Button done;
    private logisticsController lC;

    public void setLC(logisticsController lC) {
        this.lC = lC;
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
        ArrayList<HashMap<String, String>> billList = Logistics.getBillsWithoutLinkedLogistics();
        ObservableList<Box> billObs = FXCollections.observableArrayList();
        for (int i = 0; i < billList.size(); i++) {
            String id = null, info = null;
            int row = 0;
            for (String value : billList.get(i).values()) {
                switch (row) {
                    case 0:
                        id = value;
                        break;
                    case 1:
                        info = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            billObs.add(new Box(Integer.parseInt(id), info));
        }
        bill.setItems(billObs);
        HashMap<Integer, String> logList = Logistics.getLogisticsProviders();
        ObservableList<Box> logObs = FXCollections.observableArrayList();
        for (int key : logList.keySet()) {
            logObs.add(new Box(key, logList.get(key)));
        }
        logisticsProvider.setItems(logObs);
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        Logistics.createLogisticsEntry(Main.staffID, bill.getSelectionModel().getSelectedItem().id, customerAddress.getText(), logisticsProvider.getSelectionModel().getSelectedItem().id, Integer.parseInt(trackingNumber.getText()));
        lC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

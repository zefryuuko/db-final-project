package ui.logistics;

import database.Logistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;
import ui.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class edit_logisticsController {
    public TextField staffID;
    public TextField customerAddress;
    public TextField trackingNumber;
    public ComboBox<Box> logisticsProvider;

    public Button cancel;
    public Button done;

    private int id;

    public void setID(int id) {
        this.id = id;
    }

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
        HashMap<Integer, String> logList = Logistics.getLogisticsProviders();
        ObservableList<Box> logObs = FXCollections.observableArrayList();
        for (int key : logList.keySet()) {
            logObs.add(new Box(key, logList.get(key)));
        }
        logisticsProvider.setItems(logObs);
        HashMap<String, String> query = Logistics.getLogisticsDetailsById(id);
        System.out.println(query);
        int row = 0;
        for (String value: query.values()) {
            switch (row) {
                case 0:
                    customerAddress.setText(value);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    trackingNumber.setText(value);
                    break;
                case 5:
                    staffID.setText(value);
                    break;
                case 6:
                    break;
            }
            row++;
        }
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        Logistics.updateLogisticsEntry(id, Main.staffID, customerAddress.getText(), logisticsProvider.getSelectionModel().getSelectedItem().id, Integer.parseInt(trackingNumber.getText()));
        lC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

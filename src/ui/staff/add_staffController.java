package ui.staff;

import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class add_staffController {
    public TextField firstName;
    public TextField surname;
    public TextField salary;
    public ComboBox<Box> status;
    public ComboBox<Box> position;

    public Button cancel;
    public Button addStaff;

    public class Box {
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
        HashMap<Integer, String> posList = Staff.getStaffPositionDetails();
        ObservableList<Box> posObs = FXCollections.observableArrayList();
        for (int key : posList.keySet()) {
            posObs.add(new Box(key, posList.get(key)));
        }
        position.setItems(posObs);
        HashMap<Integer, String> statList = Staff.getStaffStatusDetails();
        ObservableList<Box> statObs = FXCollections.observableArrayList();
        for (int key : statList.keySet()) {
            statObs.add(new Box(key, statList.get(key)));
        }
        status.setItems(statObs);
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickAddStaff() throws IOException {
        Staff.addStaff(firstName.getText(), surname.getText(), Integer.parseInt(salary.getText()), position.getSelectionModel().getSelectedItem().getId(), status.getSelectionModel().getSelectedItem().getId());
        Stage stage = (Stage) addStaff.getScene().getWindow();
        stage.close();
    }
}

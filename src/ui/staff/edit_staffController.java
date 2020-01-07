package ui.staff;

import database.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class edit_staffController {
    private int id;
    private staffController sC;

    public TextField firstName;
    public TextField surname;
    public TextField salary;
    public ComboBox<add_staffController.Box> status;
    public ComboBox<add_staffController.Box> position;

    public Button cancel;
    public Button done;

    public void setSC(staffController sC) {
        this.sC = sC;
    }

    public void setID(int id) {
        this.id = id;
    }

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
        ObservableList<add_staffController.Box> posObs = FXCollections.observableArrayList();
        for (int key : posList.keySet()) {
            posObs.add(new add_staffController.Box(key, posList.get(key)));
        }
        position.setItems(posObs);
        HashMap<Integer, String> statList = Staff.getStaffStatusDetails();
        ObservableList<add_staffController.Box> statObs = FXCollections.observableArrayList();
        for (int key : statList.keySet()) {
            statObs.add(new add_staffController.Box(key, statList.get(key)));
        }
        status.setItems(statObs);
        HashMap<String, String> query = Staff.getStaff(id);
        int row = 0;
        for (String value : query.values()) {
            switch (row) {
                case 0:
                    firstName.setText(value);
                    break;
                case 1:
                    surname.setText(value);
                    break;
                case 2:
                    break;
                case 3:
                    for (int i = 0; i < posObs.size(); i++) {
                        position.getSelectionModel().select(i);
                        if (position.getSelectionModel().getSelectedItem().getId() == Staff.getStaffPosition(id)) {
                            break;
                        }
                    }
                    break;
                case 4:
                    salary.setText(value);
                    break;
                case 5:
                    for (int i = 0; i < statObs.size(); i++) {
                        status.getSelectionModel().select(i);
                        if (status.getSelectionModel().getSelectedItem().getId() == Staff.getStaffStatus(id)) {
                            break;
                        }
                    }
                    break;
                default:
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
        Staff.updateStaff(id, firstName.getText(), surname.getText(), Integer.parseInt(salary.getText()), position.getSelectionModel().getSelectedItem().getId(), status.getSelectionModel().getSelectedItem().getId());
        sC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}

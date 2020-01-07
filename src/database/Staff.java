package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class Staff extends DBConnect {
    public static ArrayList<HashMap<String, String>> getStaffs() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT s.staff_id, s.staff_fname, s.staff_lname, s.staff_salary, p.position_name, t.status_name FROM Staff s LEFT JOIN StaffPosition p ON s.staff_position_id = p.position_id LEFT JOIN StaffStatus t ON s.staff_status_id = t.status_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("staff_id")));
                row.put("firstName", rs.getString("staff_fname"));
                row.put("lastName", rs.getString("staff_lname"));
                row.put("salary", Integer.toString(rs.getInt("staff_salary")));
                row.put("position", rs.getString("position_name"));
                row.put("status", rs.getString("status_name"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static HashMap<Integer, String> getStaffPositionDetails() {
        HashMap<Integer, String> result = new HashMap<>();

        try {
            String query = "SELECT * FROM StaffLevels";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                result.put(rs.getInt("position_id"), rs.getString("position_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static String getStaffPosition(int staffId) {
        String result = "";

        try {
            String query = "SELECT staff_position_id FROM Staff WHERE staff_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result = rs.getString("staff_position_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static HashMap<Integer, String> getStaffStatusDetails() {
        HashMap<Integer, String> result = new HashMap<>();

        try {
            String query = "SELECT * FROM StaffStatus";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                result.put(rs.getInt("status_id"), rs.getString("status_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static String getStaffStatus(int staffId) {
        String result = "";

        try {
            String query = "SELECT staff_status_id FROM Staff WHERE staff_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result = rs.getString("staff_status_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static boolean addStaff(String firstName, String lastName, int salary, int positionId, int statusId) {
        try {
            String query = "INSERT INTO Staff (staff_fname, staff_lname, staff_salary, staff_position_id, staff_status_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setInt(3, salary);
            pst.setInt(4, positionId);
            pst.setInt(5, statusId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean addStaffPosition(String positionName) {
        try {
            String query = "INSERT INTO StaffPosition (position_name) VALUES (?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, positionName);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean deleteStaff(int staffId) {
        try {
            String query = "DELETE FROM Staff WHERE staff_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean deletePosition(int positionId) {
        try {
            String query = "DELETE FROM StaffPosition WHERE staff_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, positionId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

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
}

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Logistics extends DBConnect {
    // CREATE
    public static boolean createLogisticsEntry(int staffId, int salesId, String custAddress, int providerId, int trackingNumber) {
        try {
            String query = "INSERT INTO Logistics (staff_id, sales_id, cust_address, provider_id, trackingNumber, date_sent) VALUES (?, ?, ?, ?, ?, NOW())";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            pst.setInt(2, salesId);
            pst.setString(3, custAddress);
            pst.setInt(4, providerId);
            pst.setInt(5, trackingNumber);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // READ
    public static ArrayList<HashMap<String, String>> getLogisticsDetails() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT l.logistic_id, l.staff_id, s.cust_name, l.cust_address, p.provider_name, l.tracking_number, l.date_sent FROM Logistics l LEFT JOIN Sales s ON s.sales_id = l.sales_id LEFT JOIN LogisticsProvider p ON p.provider_id = l.logistic_provider;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("logistic_id")));
                row.put("staffId", Integer.toString(rs.getInt("staff_id")));
                row.put("custName", rs.getString("cust_name"));
                row.put("custAddress", rs.getString("cust_address"));
                row.put("providerName", rs.getString("provider_name"));
                row.put("trackingNumber", Integer.toString(rs.getInt("tracking_number")));
                row.put("dateSent", rs.getDate("date_sent").toString());
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // UPDATE
    public static boolean updateLogisticsEntry(int logisticsId, int staffId, int salesId, String custAddress, int providerId, int trackingNumber) {
        try {
            String query = "UPDATE Logistics SET staff_id = ?, sales_id = ?, cust_address = ?, provider_id = ?, trackingNumber = ? WHERE logistics_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            pst.setInt(2, salesId);
            pst.setString(3, custAddress);
            pst.setInt(4, providerId);
            pst.setInt(5, trackingNumber);
            pst.setInt(6, logisticsId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // DELETE
    public static boolean deleteLogisticsEntry(int logisticsId) {
        try {
            String query = "DELETE FROM Logistics WHERE logistic_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, logisticsId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

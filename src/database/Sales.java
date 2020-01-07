package database;

import com.sun.org.apache.bcel.internal.generic.LDIV;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Sales extends DBConnect{
    // CREATE

    // READ
    public static ArrayList<HashMap<String, String>> getSales() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT s.sales_id, s.sales_datetime, s.staff_id, s.cust_name, i.staff_fname, i.staff_lname, t.type_name FROM Sales s LEFT JOIN Staff i ON s.staff_id = i.staff_id LEFT JOIN SalesType t ON s.sales_type = t.type_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("sales_id")));
                row.put("date", rs.getDate("sales_datetime").toString());
                row.put("staff", Integer.toString(rs.getInt("staff_id")) + " - " + rs.getString("staff_fname") + " " + rs.getString("staff_lname"));
                row.put("custName", rs.getString("cust_name"));
                row.put("type", rs.getString("type_name"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static ArrayList<HashMap<String, String>> getSalesDetails(int salesId) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT d.item_id, i.item_name, i.item_price FROM SalesDetails d LEFT JOIN Item i ON i.item_id = d.item_id WHERE d.sales_id = ?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, salesId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("item_id")));
                row.put("itemName", rs.getString("item_name"));
                row.put("itemPrice", Integer.toString(rs.getInt("item_price")));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // UPDATE

    // DELETE

}

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Auth extends DBConnect{
    public static boolean authenticate(int staff_id, String password) {
        try {
            String query = "SELECT * FROM Auth WHERE staff_id = ? AND auth_password = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staff_id);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
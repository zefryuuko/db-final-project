package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    DBConnect() {
        try {  //in-capsulate the function into a try and catch

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://dbta.1ez.xyz", "DAV7304", "lpl95q3o");
            st = con.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

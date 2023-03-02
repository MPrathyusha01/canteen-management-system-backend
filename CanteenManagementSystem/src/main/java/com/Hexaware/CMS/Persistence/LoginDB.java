package com.Hexaware.CMS.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

import com.Hexaware.CMS.Model.Login;

import java.sql.ResultSet;

public class LoginDB {
    private static final String USER = "prathyusham";
    private static final String PASS = "prathyusha123";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/canteen_management_78150?allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static int registerUser(String user, String pass) {
        String sql = "INSERT INTO LOGIN (USERNAME, PASSWORD, USERTYPE) VALUES (?, ?, 'customer')";
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            res = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static Login retrieveUser(String user) {
        String sql = "SELECT * FROM LOGIN WHERE USERNAME = ?";

        Login l = new Login();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                l.setUsername(rs.getString("USERNAME"));
                l.setPassword(rs.getString("PASSWORD"));
                l.setUsertype(rs.getString("USERTYPE"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException ea){
            System.out.println(ea.getMessage());
        } catch (IllegalThreadStateException eb){
            System.out.println(eb.getMessage());
        }


        return l;
    }

    public static int updatePassword(String user, String pass) {
        String sql = "UPDATE LOGIN SET PASSWORD = ? WHERE USERNAME = ?";

        int res = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, user);

            res = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}

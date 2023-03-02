package com.Hexaware.CMS.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Hexaware.CMS.Model.Menu;

import java.sql.ResultSet;
public class MenuDB {
    private static final String USER = "prathyusham";
    private static final String PASS = "prathyusha123";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/canteen_management_78150?allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static int getLastFood_id() {
        int id = 0;

        String sql = "SELECT FOOD_ID FROM MENU ORDER BY FOOD_ID DESC LIMIT 1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("FOOD_ID");
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    public static int addNewFood(String name, double price, int vendor_id, String cat) {
        String sql = "INSERT INTO MENU VALUES (?, ?, ?, ?)";
        int res = 0;

        int id = getLastFood_id();

        if(id == 0) {
            id = 3001;
        } else {
            ++id;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, vendor_id);
            
            res = ps.executeUpdate();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static List<Menu> retrieveAllMenu() {
        List<Menu> list = new ArrayList<Menu>();

        String sql = "SELECT * FROM MENU";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Menu f = new Menu();
                f.setFood_id(rs.getInt("FOOD_ID"));
                f.setFood_name(rs.getString("FOOD_NAME"));
                f.setFood_price(rs.getInt("FOOD_PRICE"));
                f.setVendor_id(rs.getInt("VENDOR_ID"));

                list.add(f);
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static int updatePrice(int id, double price) {
        String sql = "UPDATE MENU SET FOOD_PRICE = ? WHERE FOOD_ID = ?";
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, id);

            res = ps.executeUpdate();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static Menu retrieveMenubyID(int id) {
        Menu f = new Menu();

        String sql = "SELECT * FROM MENU WHERE FOOD_ID = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                f.setFood_id(rs.getInt("FOOD_ID"));
                f.setFood_name(rs.getString("FOOD_NAME"));
                f.setFood_price(rs.getInt("FOOD_PRICE"));
                f.setVendor_id(rs.getInt("VENDOR_ID"));

            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return f;
    }

    public static List<Menu> retrieveByVendor(int id) {
        List<Menu> list = new ArrayList<Menu>();

        String sql = "SELECT * FROM Menu WHERE VENDOR_ID = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu f = new Menu();
                f.setFood_id(rs.getInt("FOOD_ID"));
                f.setFood_name(rs.getString("FOOD_NAME"));
                f.setFood_price(rs.getInt("FOOD_PRICE"));
                f.setVendor_id(rs.getInt("VENDOR_ID"));

                list.add(f);
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static List<Menu> retriveBySpec(String cat) {
        List<Menu> list = new ArrayList<Menu>();

        String sql = "select * from Menu where vendor_id in (select vendor_id from vendor where vendor_spec = ? )";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cat);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu f = new Menu();
                f.setFood_id(rs.getInt("FOOD_ID"));
                f.setFood_name(rs.getString("FOOD_NAME"));
                f.setFood_price(rs.getInt("FOOD_PRICE"));
                f.setVendor_id(rs.getInt("VENDOR_ID"));

                list.add(f);
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;    }

    
}

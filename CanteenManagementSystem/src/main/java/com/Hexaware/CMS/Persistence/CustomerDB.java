package com.Hexaware.CMS.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.Hexaware.CMS.Model.Customer;

import java.sql.ResultSet;

public class CustomerDB {

    private static final String USER = "prathyusham";
    private static final String PASS = "prathyusha123";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/canteen_management_78150?allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static int getLastCustomer_id() {                      
        int id = 0;

        String sql = "SELECT CUSTOMER_ID FROM CUSTOMER ORDER BY CUSTOMER_ID DESC LIMIT 1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("CUSTOMER_ID");
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
    public static int registerCustomer( String name, String phone,String email, Integer walbal, String password, String coupon) {
        String sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_EMAIL,  CUSTOMER_WALLETBAL, CUSTOMER_PASSWORD, CUSTOMER_COUPON) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        int id1 = getLastCustomer_id(); // id changed to id1
        int res = 0;

        if (id1 == 0) {
            id1 = 101;
        } else {
            ++id1;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setInt(5, walbal);
            ps.setString(6, password);
            ps.setString(7, coupon);

            res = ps.executeUpdate();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static Customer retrieveCustomerByName(String name) {
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME = ?" ;
        Customer b = new Customer();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b.setCustomer_id(rs.getInt("CUSTOMER_ID")); 
                b.setCustomer_name(rs.getString("CUSTOMER_NAME"));
                b.setCustomer_phone(rs.getString("CUSTOMER_PHONE"));
                b.setCustomer_email(rs.getString("CUSTOMER_EMAIL"));
                b.setCustomer_walletbal(rs.getInt("CUSTOMER_WALLETBAL"));
                b.setCustomer_password(rs.getString("CUSTOMER_PASSWORD"));
                b.setCustomer_coupon(rs.getString("CUSTOMER_COUPON"));
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  b;
    }

    public static Customer retrieveCustomerById(int id) {
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?" ;
        Customer b = new Customer();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b.setCustomer_id(rs.getInt("CUSTOMER_ID")); 
                b.setCustomer_name(rs.getString("CUSTOMER_NAME"));
                b.setCustomer_phone(rs.getString("CUSTOMER_PHONE"));
                b.setCustomer_email(rs.getString("CUSTOMER_EMAIL"));
                b.setCustomer_walletbal(rs.getInt("CUSTOMER_WALLETBAL"));
                b.setCustomer_password(rs.getString("CUSTOMER_PASSWORD"));
                b.setCustomer_coupon(rs.getString("CUSTOMER_COUPON"));
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  b;
    }

    public static int updatePhone(int id, String ph) {
        String sql = "UPDATE CUSTOMER SET CUSTOMER_PHONE = ? WHERE CUSTOMER_ID = ?" ;
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ph);
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

    public static int updateName(int id, String name) {
        String sql = "UPDATE CUSTOMER SET CUSTOMER_NAME = ? WHERE CUSTOMER_ID = ?" ;
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
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

    public static int updateWalletAmount(int i, double bal) {
        String sql = "UPDATE CUSTOMER SET CUSTOMER_WALLETBAL = ? WHERE CUSTOMER_ID = ?" ;
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, bal);
            ps.setLong(2, i);

            res = ps.executeUpdate();

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static int updateCoupon(int i, String coupon) {
        String sql = "UPDATE CUSTOMER SET CUSTOMER_COUPON = ? WHERE CUSTOMER_ID = ?" ;
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coupon);
            ps.setLong(2, i);

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

    



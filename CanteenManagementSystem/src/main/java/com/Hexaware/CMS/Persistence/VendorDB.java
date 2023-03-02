package com.Hexaware.CMS.Persistence;
import java.sql.Connection;
// import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Hexaware.CMS.Model.Vendor;

public class VendorDB {
    private static final String USER = "prathyusham";
    private static final String PASS = "prathyusha123";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/canteen_management_78150?allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Vendor getVendorById(int id) {
        String sql = "SELECT * FROM VENDOR WHERE VENDOR_ID = ?";

        Vendor s = new Vendor();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER,PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                s.setVendor_id(rs.getInt("VENDOR_ID"));
                s.setVendor_name(rs.getString("VENDOR_NAME"));
                s.setVendor_shop(rs.getString("VENDOR_SHOP"));
                s.setVendor_phone(rs.getString("VENDOR_PHONE"));
                s.setVendor_spec(rs.getString("VENDOR_SPEC"));
            }
            con.close();
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }

    public static Vendor getVendorByName(String name) {
        String sql = "SELECT * FROM VENDOR WHERE VENDOR_NAME = ?";

        Vendor s = new Vendor();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER,PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                s.setVendor_id(rs.getInt("VENDOR_ID"));
                s.setVendor_name(rs.getString("VENDOR_NAME"));
                s.setVendor_shop(rs.getString("VENDOR_SHOP"));
                s.setVendor_phone(rs.getString("VENDOR_PHONE"));
                s.setVendor_spec(rs.getString("VENDOR_SPEC"));
            }
            con.close();
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }

    public static Vendor retrieveVendorByShop(String em) {
        String sql = "SELECT * FROM VENDOR WHERE VENDOR_SHOP = ?" ;
        Vendor s = new Vendor();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, em);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s.setVendor_id(rs.getInt("VENDOR_ID"));
                s.setVendor_name(rs.getString("VENDOR_NAME"));
                s.setVendor_shop(rs.getString("VENDOR_SHOP"));
                s.setVendor_phone(rs.getString("VENDOR_PHONE"));
                s.setVendor_spec(rs.getString("VENDOR_SPEC"));
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }
    
    public static int updatePhoneNo(int id, String ph) {
        String sql = "UPDATE VENDOR SET VENDOR_PHONE = ? WHERE VENDOR_ID = ?" ;
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

    public static int updateSpec(int id, String spec) {
        String sql = "UPDATE VENDOR SET VENDOR_SPEC = ? WHERE VENDOR_ID = ?" ;
        int res = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, spec);
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
}

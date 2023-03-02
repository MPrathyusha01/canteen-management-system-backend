package com.Hexaware.CMS.Persistence;

import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.OrderStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;



public class OrderDetailsDB {
    private static final String USER = "prathyusham";
    private static final String PASS = "prathyusha123";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/canteen_management_78150?allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static int getLastOrderId() {
        int id = 0;

        String sql = "SELECT ORDER_NO FROM ORDERDETAILS ORDER BY ORDER_NO DESC LIMIT 1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("ORDER_NO");
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    /**
     * to upload new order details.
     * @param date order_date
     * @param amt order_value
     * @param stat order_status
     * @param qty quantity
     * @param string customer_id
     * @param ven vendor_id
     * @param food food_id
     * @return int
     */
    public static int uploadNewOrder(Date date, double amt, String stat, int qty, int i, int ven, int food) {
        int res = 0;

        String sql = "INSERT INTO ORDERDETAILS (ORDER_NO, ORDER_DATE, ORDER_VALUE, QUANTITY, CUSTOMER_ID, FOOD_ID, VENDOR_ID, ORDER_STATUS) "
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        int id = getLastOrderId();

        if(id == 0) {
            id = 4001;
        } else {
            ++id;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setDate(2, date);
            ps.setDouble(3, amt);
            ps.setInt(4, qty);
            ps.setInt(5, i);
            ps.setInt(6, food);
            ps.setInt(7, ven);
            ps.setString(8, stat);

            res = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * to list customer orders history.
     * @param i customer id
     * @return list of orders
     */
    public static List<OrderDetails> customerHistory(int i) {
        String sql = "SELECT * FROM ORDERDETAILS WHERE CUSTOMER_ID = ?";

        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, i);
      
            ResultSet rs = ps.executeQuery();
      
            while(rs.next()){
                OrderDetails o = new OrderDetails();
                o.setOrder_no(rs.getInt("ORDER_NO"));
                
                Date day = rs.getDate("ORDER_DATE");
                o.setOrder_date(day.toLocalDate());
                
                o.setFood_id(rs.getInt("FOOD_ID"));
                o.setVendor_id(rs.getInt("VENDOR_ID"));
                o.setCustomer_id(rs.getInt("CUSTOMER_ID"));
                o.setQuantity(rs.getInt("QUANTITY"));
                o.setOrder_value(rs.getInt("ORDER_VALUE"));
        
                String stat = rs.getString("ORDER_STATUS");
                o.setOrder_status(OrderStatus.valueOf(stat));
        
                orderDetails.add(o);
            }
            System.out.println(orderDetails);
            con.close();
          } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          }

        return orderDetails;
    }

    /**
     * to list vendor order history.
     * @param id vendor id
     * @return list of orders
     */
    public static List<OrderDetails> vendorHistory(int id) {
        String sql = "SELECT * FROM ORDERDETAILS WHERE VENDOR_ID = ?";

        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
      
            ResultSet rs = ps.executeQuery();
      
            while(rs.next()){
              OrderDetails o = new OrderDetails();
              o.setOrder_no(rs.getInt("ORDER_NO"));
              
              Date day = rs.getDate("ORDER_DATE");
              o.setOrder_date(day.toLocalDate());
              
              o.setFood_id(rs.getInt("FOOD_ID"));
              o.setVendor_id(rs.getInt("VENDOR_ID"));
              o.setCustomer_id(rs.getInt("CUSTOMER_ID"));
              o.setQuantity(rs.getInt("QUANTITY"));
              o.setOrder_value(rs.getInt("ORDER_VALUE"));
      
              String stat = rs.getString("ORDER_STATUS");
              o.setOrder_status(OrderStatus.valueOf(stat));
      
              orderDetails.add(o);
            }
      
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return orderDetails;
    }

    /**
     * update order status.
     * @param id customer id
     * @param status order status
     * @return int
     */
    public static int updateOrderStatus(int id, String status) {
        int res = 0;
        String sql = "UPDATE ORDERDETAILS SET ORDER_STATUS = ? WHERE ORDER_NO = ?";
        
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, status);
          ps.setInt(2, id);
    
          res = ps.executeUpdate();
    
          con.close();
        } catch (ClassNotFoundException e) {
          System.out.println(e.getMessage());
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
    
        return res;
    }

    /**
     * to list pending orders for today.
     * @param id customer id
     * @return list
     */
    public static List<OrderDetails> listCurrentPendingOrdersB(int id) {
        String sql = "SELECT * FROM ORDERDETAILS WHERE CUSTOMER_ID = ? AND ORDER_DATE = CURRENT_DATE() "
            + " AND ORDER_STATUS = 'PENDING'";

        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
      
            ResultSet rs = ps.executeQuery();
      
            while(rs.next()){
                OrderDetails o = new OrderDetails();
                o.setOrder_no(rs.getInt("ORDER_NO"));
                
                Date day = rs.getDate("ORDER_DATE");
                o.setOrder_date(day.toLocalDate());
                
                o.setFood_id(rs.getInt("FOOD_ID"));
                o.setVendor_id(rs.getInt("VENDOR_ID"));
                o.setCustomer_id(rs.getInt("CUSTOMER_ID"));
                o.setQuantity(rs.getInt("QUANTITY"));
                o.setOrder_value(rs.getInt("ORDER_VALUE"));
        
                String stat = rs.getString("ORDER_STATUS");
                o.setOrder_status(OrderStatus.valueOf(stat));
        
                orderDetails.add(o);
            }
      
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return orderDetails;
    }

    /**
     * to list pending orders for today (vendor)
     * @param id vendor id
     * @return list
     */
    public static List<OrderDetails> listCurrentPendingOrdersS(int id) {
        String sql = "SELECT * FROM ORDERDETAILS WHERE VENDOR_ID = ? AND ORDER_DATE = CURRENT_DATE() "
            + " AND ORDER_STATUS = 'PENDING'";

        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
      
            ResultSet rs = ps.executeQuery();
      
            while(rs.next()){
                OrderDetails o = new OrderDetails();
                o.setOrder_no(rs.getInt("ORDER_NO"));
                
                Date day = rs.getDate("ORDER_DATE");
                o.setOrder_date(day.toLocalDate());
                
                o.setFood_id(rs.getInt("FOOD_ID"));
                o.setVendor_id(rs.getInt("VENDOR_ID"));
                o.setCustomer_id(rs.getInt("CUSTOMER_ID"));
                o.setQuantity(rs.getInt("QUANTITY"));
                o.setOrder_value(rs.getInt("ORDER_VALUEE"));
        
                String stat = rs.getString("ORDER_STATUS");
                o.setOrder_status(OrderStatus.valueOf(stat));
        
                orderDetails.add(o);
            }
      
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return orderDetails;
    }

    /**
     * to retrieve order details.
     * @param id order_no int
     * @return orderDetails
     */
    public static OrderDetails retrieveOrderDetails(int id) {
        String sql = "SELECT * FROM ORDERDETAILS WHERE ORDER_NO = ?";

        OrderDetails o = new OrderDetails();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
      
            ResultSet rs = ps.executeQuery();
      
            while(rs.next()){
                o.setOrder_no(rs.getInt("ORDER_NO"));
              
                Date day = rs.getDate("ORDER_DATE");
                o.setOrder_date(day.toLocalDate());
                
                o.setFood_id(rs.getInt("FOOD_ID"));
                o.setVendor_id(rs.getInt("VENDOR_ID"));
                o.setCustomer_id(rs.getInt("CUSTOMER_ID"));
                o.setQuantity(rs.getInt("QUANTITY"));
                o.setOrder_value(rs.getInt("ORDER_VALUE"));
        
                String stat = rs.getString("ORDER_STATUS");
                o.setOrder_status(OrderStatus.valueOf(stat));
            }
      
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return o;
    }
}

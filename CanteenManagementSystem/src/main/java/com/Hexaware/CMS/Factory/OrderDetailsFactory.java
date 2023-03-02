package com.Hexaware.CMS.Factory;

import java.sql.Date;
import java.util.List;

import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Persistence.OrderDetailsDB;

public class OrderDetailsFactory {
    /**
     * to list customer history.
     * @param i customer_id int
     * @return OrderDetails array
     */
    public static OrderDetails[] customerHistory(int i) {
        List<OrderDetails> orderDetails = OrderDetailsDB.customerHistory(i);
        OrderDetails[] oList = orderDetails.toArray(new OrderDetails[orderDetails.size()]);
        return oList;
    }

    /**
     * to list vendor history.
     * @param id vendor_id int
     * @return OrderDetails array
     */
    public static OrderDetails[] vendorHistory(int id) {
        List<OrderDetails> orderDetails =OrderDetailsDB.vendorHistory(id);
        OrderDetails[] oList = orderDetails.toArray(new OrderDetails[orderDetails.size()]);
        return oList;
    }

    /**
     * to list current pending orders (customer)
     * @param id customer_id int
     * @return OrderDetails array
     */
    public static OrderDetails[] listPendingOrdersB(int id) {
        List<OrderDetails> orderDetails = OrderDetailsDB.listCurrentPendingOrdersB(id);
        OrderDetails[] oList = orderDetails.toArray(new OrderDetails[orderDetails.size()]);
        return oList;
    }

    /**
     * to list current pending orders (seller)
     * @param id sellerId int
     * @return Orders array
     */
    public static OrderDetails[] listPendingOrdersS(int id) {
        List<OrderDetails> orderDetails = OrderDetailsDB.listCurrentPendingOrdersS(id);
        OrderDetails[] oList = orderDetails.toArray(new OrderDetails[orderDetails.size()]);
        return oList;
    }

    /**
     * to get order details.
     * @param id orderId int
     * @return Orders object
     */
    public static OrderDetails getOrderDetails(int id) {
        OrderDetails orderDetails = OrderDetailsDB.retrieveOrderDetails(id);
        return orderDetails;
    }

    /**
     * to upload new order details.
     * @param date order_date
     * @param amt order_value
     * @param stat order_status
     * @param qty quantity
     * @param cus customer_id
     * @param ven vendor_id
     * @param food food_id
     * @return int
     */
    public static int uploadNewOrder(Date date, double amt, String stat, int qty, int i, int ven, int food) {
        int res = OrderDetailsDB.uploadNewOrder(date, amt, stat, qty, i, ven, food);
        return res;
    }

    /**
     * to update orderStatus.
     * @param id orderId
     * @param status orderStatus
     * @return int
     */
    public static int updateStatus(int id, String status) {
        int res = OrderDetailsDB.updateOrderStatus(id, status);
        return res;
    } 
}
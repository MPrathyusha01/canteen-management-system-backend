package com.Hexaware.CMS.Model;

import java.time.LocalDate;
// import java.util.Date;

public class OrderDetails {
   private int order_no;
   private int vendor_id;
   private Integer customer_id;
   private int food_id;
   private int quantity;   
   private int  order_value;
   private OrderStatus order_status;
   private LocalDate order_date;

   public int getOrder_no() {
    return order_no;
  }

  public void setOrder_no(int order_no) {
    this.order_no = order_no;
  }

  public int getVendor_id() {
    return vendor_id;
  }

  public void setVendor_id(int vendor_id) {
    this.vendor_id = vendor_id;
  }

  public Integer getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
  }

  public int getFood_id() {
    return food_id;
  }

  public void setFood_id(int food_id) {
    this.food_id = food_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getOrder_value() {
    return order_value;
  }

  public void setOrder_value(int order_value) {
    this.order_value = order_value;
  }

  public OrderStatus getOrder_status() {
    return order_status;
  }

  public void setOrder_status(OrderStatus orderStatus) {
    this.order_status = orderStatus;
  }

  public LocalDate getOrder_date() {
    return order_date;
  }

  public void setOrder_date(LocalDate localDate) {
    this.order_date = localDate;
  }

  @Override
   public String toString() {
     return "OrderDetails [customerId=" + customer_id + ", date of order =" + order_date +  ", foodId=" + food_id
         + ", orderNo=" + order_no + ", orderStatus=" + order_status + ", orderValue=" + order_value + ", quantity="
         + quantity + ", vendorId=" + vendor_id + "]";
   }

   

  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password
}
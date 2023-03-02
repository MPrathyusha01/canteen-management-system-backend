package com.Hexaware.CMS.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.MenuFactory;
import com.Hexaware.CMS.Factory.OrderDetailsFactory;

public class Customer {

  private int customer_id;
  private String customer_name;
  private String customer_phone;
  private String customer_email;
  private int customer_walletbal;
  private String customer_coupon;
  private String customer_password;

  
  
 

  public int getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
  }

  public String getCustomer_name() {
    return customer_name;
  }

  public void setCustomer_name(String customer_name) {
    this.customer_name = customer_name;
  }

  public String getCustomer_phone() {
    return customer_phone;
  }

  public void setCustomer_phone(String customer_phone) {
    this.customer_phone = customer_phone;
  }

  public String getCustomer_email() {
    return customer_email;
  }

  public void setCustomer_email(String customer_email) {
    this.customer_email = customer_email;
  }

  public int getCustomer_walletbal() {
    return customer_walletbal;
  }

  public void setCustomer_walletbal(int customer_walletbal) {
    this.customer_walletbal = customer_walletbal;
  }

  public String getCustomer_coupon() {
    return customer_coupon;
  }

  public void setCustomer_coupon(String customer_coupon) {
    this.customer_coupon = customer_coupon;
  }

  public String getCustomer_password() {
    return customer_password;
  }

  public void setCustomer_password(String customer_password) {
    this.customer_password = customer_password;
  }
  
   

  public Customer() {
  }

  public Customer( String customer_name, String customer_phone, String customer_email,
      int customer_walletbal, String customer_coupon, String customer_password) {
    // this.customer_id = customer_id;
    this.customer_name = customer_name;
    this.customer_phone = customer_phone;
    this.customer_email = customer_email;
    this.customer_walletbal = customer_walletbal;
    this.customer_coupon = customer_coupon;
    this.customer_password = customer_password;
  }

  


  @Override
  public String toString() {
    return "Your details Email=" + customer_email + ", Customer ID=" + customer_id + ", Customer Name="
        + customer_name +  ", Customer Phone=" + customer_phone + ", Customer Wallet Balance="
        + customer_walletbal + ", Customer Coupon=" +customer_coupon+" ";
  }
  public String registerCustomer(String name, String pass, String em, String ph, int bal, String coupon) {
    String str = "Unable to register. Try later";

    int bal1 = 45000;

    int res = CustomerFactory.registerCustomer(name, pass,  em, ph, bal1, coupon);
    if (res > 0) {
        int r = LoginFactory.registerUser(em, ph);

        if (r > 0) {
            str = "Registration Successful";
        }
    }

    return str;
}

public String updatePhone(int id, String ph) {
    String str = "Unable to update. Try later";

    int res = CustomerFactory.updatePhone(id, ph);

    if (res > 0) {
        str = "Phone number updated successfully";
    }

    return str;
}

public String updateName(int id, String name) {
    String str = "Unable to update. Try later";

    int res = CustomerFactory.updateName(id, name);

    if (res > 0) {
        str = "Name updated successfully";
    }

    return str;
}

public String placeOrder(Customer b, int qty, int food) {
    String str = "Unable to place order. Please try later";

    Random random = new Random();
    int val = random.nextInt(900) + 100;
    String coupon = "FIRST" + val;

    Menu f = MenuFactory.getById(food);

    if (f != null) {
        double amt = f.getFood_price() * qty;

        OrderDetails[] orderDetails = OrderDetailsFactory.customerHistory(b.getCustomer_id());

        boolean flag = false;

        if (orderDetails.length == 0) {
            amt = amt - 1500;
            flag = true;
        }

        LocalDate today = LocalDate.now();
        Date oDate = Date.valueOf(today);

        String status = OrderStatus.PENDING.name();

        int curBal = b.getCustomer_walletbal();

        if (curBal > amt) {
            int res = OrderDetailsFactory.uploadNewOrder(oDate, amt, status, qty, b.getCustomer_id(), f.getVendor_id(), food);

            if (res > 0) {
                curBal = (int) (curBal - amt);
                int r = CustomerFactory.updateWallet(b.getCustomer_id(), curBal);

                if (r > 0) {
                    str = "Order Placed. You will receive an update on your registered phone number";
                    if (flag) {
                        int x = CustomerFactory.updateCoupon(b.getCustomer_id(), coupon);
                        if (x > 0) {
                            System.out.println("Coupon for 1500.00 applied as this is your first order");
                        }
                    }
                }
            }
        }else {
          System.out.println("Insufficient Balance. Please update your wallet");
      }
    } else {
        System.out.println("This item is not in our menu. Please try again.");
    }

    return str;
}

public String cancelOrder(Customer b, int id) {
    String msg = "Cancellation unsuccessful. Please try again";

    double curBal = b.getCustomer_walletbal();

    String status = OrderStatus.CANCELLED.name();

    OrderDetails order = OrderDetailsFactory.getOrderDetails(id);

    if (order != null) {
        OrderStatus stat = order.getOrder_status();
        
        LocalDate today = LocalDate.now();
        LocalDate oDate = order.getOrder_date();

        if (!stat.equals(OrderStatus.PENDING) && !today.equals(oDate)) {
            System.out.println("Confirmed or Rejected Orders cannot be cancelled");
        } else {
            int res = OrderDetailsFactory.updateStatus(id, status);

            if (res > 0) {
                curBal = curBal + order.getOrder_value();

                int r = CustomerFactory.updateWallet(b.getCustomer_id(), curBal);
                if (r > 0) {
                    System.out.println(order.getOrder_value() + " is credited to your account");
                    msg = "Your order is cancelled successfully. Please visit us again";
                }
            }
        }
    } else {
        System.out.println("Please check your Order number and try again");
    }
    return msg;
}
}
  

package com.Hexaware.CMS.Model;

import java.time.LocalDate;

import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.MenuFactory;
import com.Hexaware.CMS.Factory.OrderDetailsFactory;
import com.Hexaware.CMS.Factory.VendorFactory;

public class Vendor {
  private int vendor_id;  // Vendor.vendor_id  rs.getInt("vendor_id")
  private String vendor_name;   // rs.getString("vendor_name")
  private String vendor_phone;  // rs.getString("vendor_phone")
  private String vendor_spec;  // rs.getString("vendor_specs")
  private String vendor_shop;  

  
  public int getVendor_id() {
    return vendor_id;
  }

  public void setVendor_id(int vendor_id) {
    this.vendor_id = vendor_id;
  }

  public String getVendor_name() {
    return vendor_name;
  }

  public void setVendor_name(String vendor_name) {
    this.vendor_name = vendor_name;
  }

  public String getVendor_phone() {
    return vendor_phone;
  }

  public void setVendor_phone(String vendor_phone) {
    this.vendor_phone = vendor_phone;
  }

  public String getVendor_spec() {
    return vendor_spec;
  }

  public void setVendor_spec(String vendor_spec) {
    this.vendor_spec = vendor_spec;
  }
  
  
  public String getVendor_shop() {
    return vendor_shop;
  }

  public void setVendor_shop(String vendor_shop) {
    this.vendor_shop = vendor_shop;
  }

  public Vendor() {
  }
  
  public Vendor(int vendor_id, String vendor_name, String vendor_phone, String vendor_spec, String vendor_shop) {
    this.vendor_id = vendor_id;
    this.vendor_name = vendor_name;
    this.vendor_phone = vendor_phone;
    this.vendor_spec = vendor_spec;
    this.vendor_shop = vendor_shop;
  }

  

  @Override
  public String toString() {
    return "Vendor [vendor id=" + vendor_id + ", vendor name=" + vendor_name 
            + ", vendor phone=" + vendor_phone + ", vendor spec=" + vendor_spec + ", vendor shop="+ vendor_shop+"]";
  }

  public String acceptRejectOrder(int id, String status){
    String msg = "Unable to Accept or Reject Order. Please try again";
    OrderDetails order = OrderDetailsFactory.getOrderDetails(id);

    if (order != null) {
        Customer b = CustomerFactory.retrieveById(order.getCustomer_id());
        OrderStatus stat = order.getOrder_status();
        LocalDate today = LocalDate.now();
        
        if (!stat.equals(OrderStatus.PENDING) && !today.equals(order.getOrder_date())) {
            System.out.println("This Order is not a Pending Order. Status cannot be updated");
        } else {
            int res = OrderDetailsFactory.updateStatus(id, status);

            if(res > 0) {
                if(status.equals(OrderStatus.ACCEPTED.name())) {
                    msg = "Order has been Confirmed.";
                }
                if(status.equals(OrderStatus.DENIED.name())) {
                    double curBal = b.getCustomer_walletbal();
                    curBal = curBal + order.getOrder_value();

                    int r = CustomerFactory.updateWallet(b.getCustomer_id(), curBal);

                    if (r > 0) {
                        System.out.println(order.getOrder_value() + " has been reverted back to the customer's account");
                        msg = "Order has been rejected!";
                    }
                }
            }
        }
    }
    
    return msg;
}

public String updatePhone(int id, String ph) {
    String str = "Unable to update. Try later";

    int res = VendorFactory.updatePhone(id, ph);

    if (res > 0) {
        str = "Phone number updated successfully";
    }

    return str;
}

public String updatespec(int id, String addr) {
    String str = "Unable to update. Try later";

    int res = VendorFactory.updateSpec(id, addr);

    if (res > 0) {
        str = "Specilization is updated successfully";
    }

    return str;
}

public String addNewFood(Vendor s, String name, double amt, String cat) {
    String str = "Unable to add new food. Please try later";

    int res = MenuFactory.addNewFood(name,  amt, s.getVendor_id(), cat);
    if(res > 0) {
        str = "Food details uploaded successfully.";
    }
    
    return str;
}

  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password

}
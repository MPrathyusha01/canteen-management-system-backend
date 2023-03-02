package com.Hexaware.CMS.Cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.MenuFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.OrderDetailsFactory;
import com.Hexaware.CMS.Factory.VendorFactory;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

public class VendorUtil {
    private Scanner op1 = new Scanner(System.in);
    public void vendorMenu(String user) {
        Vendor s = VendorFactory.findByName(user);

        System.out.println("Welcome To The Vendor Portal");
        System.out.println("1. View My Food Uploads");
        System.out.println("2. View Menu Details");
        System.out.println("3. Accept/Reject Order");
        System.out.println("4. Order History");
        System.out.println("5. Profile");
        System.out.println("6. Add or Edit Item");
        System.out.println("7. Exit");

        System.out.println("Your choice? ");
        int ch = op1.nextInt();

        vendorSubmenu(ch, s);
    }

    private void vendorSubmenu(int ch, Vendor s) {
        switch(ch) {
            case 1:
                Menu[] list = MenuFactory.retrieveByVendor(s.getVendor_id());
                if(list.length > 0) {
                    for(Menu f: list) {
                        System.out.println("Id: " + f.getFood_id() + ", Name: " + f.getFood_name()
                            + ", Food ID: " + f.getFood_id() + ", Unit price: " + f.getFood_price());
                    }
                } else {
                    System.out.println("Are you new here? Please upload the food details");
                }
                System.out.println();
                break;
            case 2:
                System.out.println("Enter the food id: ");
                int id = op1.nextInt();
                Menu f1 = MenuFactory.getById(id);
                System.out.println("Id: " + f1.getFood_id() + ", Name: " + f1.getFood_name()
                            + ", Food ID: " + f1.getFood_id() + ", Unit price: " + f1.getFood_price());
                System.out.println();
                break;
            case 3:
                OrderDetails[] orders = OrderDetailsFactory.listPendingOrdersS(s.getVendor_id());
                if(orders.length > 0) {
                    for (OrderDetails od: orders) {
                        Menu f2 = MenuFactory.getById(od.getFood_id());
                        Customer b = CustomerFactory.retrieveById(od.getCustomer_id());
                        System.out.println("Id: " + od.getOrder_no() + ", Date: " + od.getOrder_date()
                            + ", Furniture: " + f2.getFood_id() + ", Buyer: " + b.getCustomer_name()
                            + ", Quantity: " + od.getQuantity() + ", Amount: " + od.getOrder_value());
                        System.out.println("---------------------------------------------");
                    }
                    System.out.println("Please enter the order id: ");
                    int furn = op1.nextInt();
                    System.out.println("Do you want to \n1. Accept Order\n2. Reject Order");
                    int opt = op1.nextInt();
                    String status = "DENIED";
                    if (opt == 1) {
                        status = "ACCEPTED";
                    }

                    String msg = s.acceptRejectOrder(furn, status);
                    System.out.println(msg);
                } else {
                    System.out.println("No Pending Orders to fill");
                }
                System.out.println();
                break;
            case 4:
                System.out.println();
                System.out.println("My Previous Orders: ");
                OrderDetails[] oList = OrderDetailsFactory.vendorHistory(s.getVendor_id());
                if (oList.length > 0) {
                    for(OrderDetails o: oList) {
                        Menu f2 = MenuFactory.getById(o.getFood_id());
                        Customer b = CustomerFactory.retrieveById(o.getCustomer_id());
                        System.out.println("Id: " + o.getOrder_no() + ", Date: " + o.getOrder_date()
                            + ", Food: " + f2.getFood_name() + ", Customer: " + b.getCustomer_name()
                            + ", Quantity: " + o.getQuantity() + ", Amount: " + o.getOrder_value()
                            +", Order Status: " + o.getOrder_status().name());
                        System.out.println("---------------------------------------------");
                    }
                } else {
                    System.out.println("No Orders.");
                }
                System.out.println();
                break;
            case 5:
                showProfileDetails(s);
                break;
            case 6:
                addEditFood(s);
                break;
            case 7:
                Runtime.getRuntime().exit(0);
            default:
                System.out.println("Wrong option. Please choose again");
                break;
        }

        vendorMenu(s.getVendor_name());
    }

    private void showProfileDetails(Vendor s) {
        System.out.println("1. View Profile");
        System.out.println("2. Update Phone Number");
        System.out.println("3. Update Specilization");
        System.out.println("4. Update Password");
        System.out.println("5. Back to Main menu");
        System.out.print("Your option? ");
        int ch = op1.nextInt();

        switch(ch) {
            case 1:
                System.out.println(s.toString());
                break;
            case 2:
                System.out.println("Please enter new phone number: ");
                String ph = op1.next();
                String msg = s.updatePhone(s.getVendor_id(), ph);
                System.out.println(msg);
                break;
            case 3:
                System.out.println("Please enter Specilization: ");
                String spec = op1.next();
                String str = s.updatespec(s.getVendor_id(), spec);
                System.out.println(str);
                break;
            case 4:
                System.out.println("Enter new password: ");
                String pass = op1.next();
                System.out.println("Confirm password: ");
                String rep = op1.next();

                if (pass.equals(rep)) {
                    int r = LoginFactory.updatePassword(s.getVendor_name(), pass);

                    if (r > 0) {
                        System.out.println("Password updated successfully");
                    } else {
                        System.out.println("Password update unsuccessful");
                    }
                }
            default:
                break;
        }
        vendorMenu(s.getVendor_name());
    }

    public void addEditFood(Vendor s) {
        System.out.println();
        System.out.println("1. Add New Food\n2. Edit Price\nYour option? ");
        int ch = op1.nextInt();

        switch(ch) {
            case 1:
                System.out.println("Enter Food Name: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                try {
                    String name = br.readLine();
                    System.out.println("Enter Price: ");
                    double amt = op1.nextDouble();
                    System.out.println("Enter Specilization: ");
                    String cat = br.readLine();

                    String msg = s.addNewFood(s, name, amt, cat);
                    System.out.println(msg);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                Menu[] list = MenuFactory.retrieveByVendor(s.getVendor_id());
                if(list.length > 0) {
                    for(Menu f: list) {
                        System.out.println("Id: " + f.getFood_id() + ", Name: " + f.getFood_name()
                            + ", Unit price: " + f.getFood_price());
                    }
                }

                System.out.println("Enter the id: ");
                int id = op1.nextInt();
                System.out.println("Enter the new price: ");
                double price = op1.nextDouble();

                int res = MenuFactory.updatePrice(id, price);
                if(res > 0) {
                    System.out.println("Price updated");
                } else {
                    System.out.println("Unable to update price. Please try later");
                }
                break;
            default:
                break;
        }
    }
}
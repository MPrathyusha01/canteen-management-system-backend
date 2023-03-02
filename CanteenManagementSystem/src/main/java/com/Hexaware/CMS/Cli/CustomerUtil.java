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




public class CustomerUtil {
    private Scanner op = new Scanner(System.in);
    public void customerMenu(String name) {
        Customer b = CustomerFactory.retrieveCustomerByName(name);

        System.out.println("Welcome ");
        System.out.println("1. View  Menu");
        System.out.println("2. View Food  Details");
        System.out.println("3. Place Order");
        System.out.println("4. Profile Details");
        System.out.println("5. Search");
        System.out.println("6. Wallet Details");
        System.out.println("7. Purchase History");
        System.out.println("8. Cancel Order");
        System.out.println("9. Exit");

        System.out.print("Your choice: ");
        int ch = op.nextInt();

        customerSubmenu(ch, b.getCustomer_name());
    }

    public void customerSubmenu(int ch, String name) {
        Customer b = CustomerFactory.retrieveCustomerByName(name);
        int id = 0;
        switch(ch) {
            case 1:
                Menu[] list = MenuFactory.getAllMenu();
                for(Menu f: list) {
                    System.out.println(f.toString());
                }
                System.out.println();
                break;
            case 2:
                System.out.println("Enter the id: ");
                id = op.nextInt();
                Menu f1 = MenuFactory.getById(id);
                System.out.println(f1.toString());
                break;
            case 3:
                Menu[] list1 = MenuFactory.getAllMenu();
                for(Menu f: list1) {
                    System.out.println(f.toString());
                }
                System.out.print("Enter the Food Id: ");
                int food = op.nextInt();
                System.out.println("Enter the quantity: ");
                int qty = op.nextInt();

                String msg = b.placeOrder(b, qty, food);
                System.out.println(msg);
                System.out.println();
                break;
            case 4:
                showProfileDetails(name);
                break;
            case 5:
                searchFood(name);
                break;
            case 6:
                showWalletDetails(b.getCustomer_id());
                break;
            case 7:
                System.out.println();
                System.out.println("My Orders: ");
                OrderDetails[] orderDetails = OrderDetailsFactory.customerHistory(b.getCustomer_id());
                if (orderDetails.length > 0) {
                    for(OrderDetails o: orderDetails) {
                        Menu f2 = MenuFactory.getById(o.getFood_id());
                        Vendor s = VendorFactory.findById(o.getVendor_id());
                        System.out.println("Order Id: " + o.getOrder_no() + ", Order Date: " + o.getOrder_date()
                            + ",  Vendor: " + s.getVendor_name()+",  Food Name: "+ f2.getFood_name()
                            + ", Quantity: " + o.getQuantity() + ", Amount: " + o.getOrder_value());
                        System.out.println("______________________________________________________________________________________________");
                    }
                } else {
                    System.out.println("No Orders.");
                }
                System.out.println();
                // System.out.println(b.getCustomer_id());
                break;
            case 8:
                System.out.println();
                OrderDetails[] oList = OrderDetailsFactory.listPendingOrdersB(b.getCustomer_id());

                if (oList.length > 0) {
                    for (OrderDetails o: oList) {
                        Menu f2 = MenuFactory.getById(o.getFood_id());
                        Vendor s = VendorFactory.findById(o.getVendor_id());
                        System.out.println("Id: " + o.getOrder_no() + ", Date: " + o.getOrder_date()
                            + ",  Vendor: " + s.getVendor_name()+",  Food Name: "+ f2.getFood_name()
                            + ", Quantity: " + o.getQuantity() + ", Amount: " + o.getOrder_value());
                        System.out.println("_________________________________________________________________________");
                    }
                    System.out.println("Please enter the order id: ");
                    id = op.nextInt();
                    String str = b.cancelOrder(b, id);
                    System.out.println(str);
                    System.out.println();
                } else {
                    System.out.println("No Pending Orders to Cancel");
                }
                break;
            case 9:
                Runtime.getRuntime().exit(0);
            default:
                System.out.println("Wrong option. Please choose again");
                break;
        }
        customerMenu(name);
    }

    private void showProfileDetails(String name) {
        System.out.println("1. View Profile");
        System.out.println("2. Update Phone Number");
        System.out.println("3. Update Name");
        System.out.println("4. Update Password");
        System.out.println("5. Back to Main menu");
        System.out.print("Your option? ");
        int ch = op.nextInt();

        Customer b = CustomerFactory.retrieveCustomerByName(name);
        switch(ch) {
            case 1:
                System.out.println(b.toString());
                break;
            case 2:
                System.out.println("Please enter new phone number: ");
                String ph = op.next();
                String msg = b.updatePhone(b.getCustomer_id(), ph);
                System.out.println(msg);
                break;
            case 3:
                System.out.println("Please enter new Name: ");
                String name1 = op.next();
                String str = b.updateName(b.getCustomer_id(), name1);
                System.out.println(str);
                break;
            case 4:
                System.out.println("Enter new password: ");
                String pass = op.next();
                System.out.println("Confirm password: ");
                String rep = op.next();

                if (pass.equals(rep)) {
                    int r = LoginFactory.updatePassword(b.getCustomer_email(), pass);

                    if (r > 0) {
                        System.out.println("Password updated successfully");
                    } else {
                        System.out.println("Password update unsuccessful");
                    }
                }
            default:
                break;
        }
        customerMenu(name);
    }

    private void showWalletDetails(int id) {
        Customer b = CustomerFactory.retrieveById(id);
        System.out.println();
        System.out.println("1. Show Current Balance");
        System.out.println("2. Update Balance");
        System.out.println("3. Back to Main Menu");
        System.out.print("Your choice? ");
        int ch = op.nextInt();
        
        switch(ch) {
            case 1:
                System.out.println("Current Balance: " + b.getCustomer_walletbal());
                System.out.println("_______________________________________________");

                break;
            case 2:
                System.out.println("Add an Amount: ");
                double bal = op.nextDouble();
                double curBal = b.getCustomer_walletbal() + bal;
                int res = CustomerFactory.updateWallet(id, curBal);
                if(res > 0) {
                    System.out.println("Wallet Updated.");
                } else {
                    System.out.println("Unable to update. Please try later");
                }
                break;
            default:
                break;
        }

        customerMenu(b.getCustomer_email());
    }

    private void searchFood(String em) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. Search By Vendor");
        System.out.println("2. Search By Specilization");
        System.out.println("3. Back to Main Menu");
        System.out.print("Your choice: ");
        int ch = op.nextInt();

        switch(ch) {
            case 1:
                System.out.println("Enter the Vendor Name: ");
                try {
                    String name = br.readLine();
                    Vendor s = VendorFactory.findByName(name);
                    if (s != null) {
                        Menu[] list = MenuFactory.retrieveByVendor(s.getVendor_id());
                        for(Menu f: list) {
                            System.out.println(f.toString());
                        }
                    }
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                String cat = "Coffee and pastery";
                System.out.println("List of Specilization: ");
                System.out.println("1. Vegetarian\n2. Non-Vegetarian\n3. Coffee and pastery\n");
                System.out.println("4. Back to Main menu");
                System.out.print("Your option? ");
                int option = op.nextInt();
                switch(option) {
                    case 1:
                        cat = "Vegetarian";
                        break;
                    case 2:
                        cat = "Non-Vegetarian";
                        break;
                    case 3:
                        cat = "Coffee and Pastery";
                        break;
                    default:
                        break;
                }

                Menu[] menu = MenuFactory.retrieveBySpec(cat);
                for(Menu f1: menu) {
                    Vendor s1 = VendorFactory.findById(f1.getVendor_id());
                    System.out.println("_________________________________________________________________________");

                    System.out.println("Food Id: " + f1.getFood_id() + ", Name: " + f1.getFood_name()
                        + ", Price: " + f1.getFood_price() + ", Vendor: " + s1.getVendor_name());

                }
                break;
            default:
                break;
        }
        customerMenu(em);
    }
}
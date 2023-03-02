package com.Hexaware.CMS.Cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.Hexaware.CMS.Factory.MenuFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.Login;
// import com.Hexaware.CMS.Cli.CustomerUtil;
// import com.Hexaware.CMS.Cli.VendorUtil;

public class CliMain {
    private Scanner sc = new Scanner(System.in);

    private void mainMenu() {
        System.out.println("Welcome to 5-Star Canteen.");
        System.out.println("----HOME PAGE----");
        System.out.println("1. View Menu");
        System.out.println("2. Login as Customer");
        System.out.println("3. Register as Customer");
        System.out.println("4. Login as Vendor");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int ch = sc.nextInt();
        subMenu(ch);
    }

    private void subMenu(int ch) {
        switch(ch) {
            case 1:
                Menu[] list = MenuFactory.getAllMenu();
                for(Menu f: list) {
                    System.out.println(f.toString());
                }
                System.out.println("Please register or login to get the food  \n");
                break;
            case 2:
                signin();
                //System.out.println("Customer");
                break;
            case 3:
                System.out.println("Enter Name: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String name = br.readLine();

                    
                    System.out.println("Enter Email: ");
                    String em = br.readLine();

                    System.out.println("Enter Phone: ");
                    String ph = br.readLine();

                    System.out.println("Enter password: ");
                    String pass = br.readLine();

                    System.out.println("Confirm password: ");
                    String repass = br.readLine();
                    
                    System.out.println("Wallet Balance:  ");
                    Integer bal = br.read();

                    System.out.println("coupon code:  ");
                    String coupon = br.readLine();

                    if (pass.equals(repass)) {
                        Customer b = new Customer();
                        String msg = b.registerCustomer(name, ph , em, pass, bal, coupon );
                        System.out.println(msg);
                    } else {
                        System.out.println("Password Mismatch. Please re - enter details");
                    }


                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 4:
                signin();
                break;
            case 5:
                Runtime.getRuntime().exit(0);
            default:
                System.out.println("Wrong Choice");
                break;
        }
        mainMenu();
    }

    public static void main(String[] args) {
        CliMain obj = new CliMain();
        obj.mainMenu();
    }

    private void signin() {
        System.out.print("UserName Please: ");
        String user = sc.next();
        System.out.print("Password Please: ");
        String pass = sc.next();

        Login l = LoginFactory.retrieveUser(user);
        try {
            
        

        if (l.getPassword().equals(pass)) {
            String type = l.getUsertype();
            
            switch(type) {
                case "customer":
                    CustomerUtil bu = new CustomerUtil();
                    bu.customerMenu(l.getUsername());
                    break;
                case "vendor":
                    VendorUtil su = new VendorUtil();
                    su.vendorMenu(l.getUsername());
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("If you are a Customer. Please register");
            System.out.println("If you are Vendor, please contact the admin. There might be a problem");
        }
    } catch (Exception e) {
        System.out.println("Something went wrong.. Try again later");
    }

        // if(user.equals("user") && pass.equals("pass123")) {
        //     flag = true;
        // }

        // return flag;
    }
}
package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Persistence.CustomerDB;

public class CustomerFactory {
    public static int registerCustomer(String name, String pass,  String em, String ph, int bal, String coupon) {
        int res = CustomerDB.registerCustomer(name, pass,  em, bal, ph, coupon);
        return res;
    }

    public static Customer retrieveCustomerByName(String name) {
        Customer b = CustomerDB.retrieveCustomerByName(name);
        return b;
    }

    public static Customer retrieveById(Integer id) {
        Customer b = CustomerDB.retrieveCustomerById(id);
        return b;
    }

    public static int updatePhone(int id, String ph) {
        int res = CustomerDB.updatePhone(id, ph);
        return res;
    }

    public static int updateName(int id, String name) {
        int res = CustomerDB.updateName(id, name);
        return res;
    }

    public static int updateWallet(int i, double bal) {
        int res = CustomerDB.updateWalletAmount(i, bal);
        return res;
    }

    public static int updateCoupon(int i, String coupon) {
        int res = CustomerDB.updateCoupon(i, coupon);
        return res;
    }
}
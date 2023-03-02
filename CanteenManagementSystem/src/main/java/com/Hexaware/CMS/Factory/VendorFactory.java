package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Vendor;
import com.Hexaware.CMS.Persistence.VendorDB;

public class VendorFactory {
    public static Vendor findById(int id) {
        Vendor s = VendorDB.getVendorById(id);
        return s;
    }

    public static Vendor findByName(String name) {
        Vendor s =VendorDB.getVendorByName(name);
        return s;
    }

    public static Vendor findByShop(String sh) {
        Vendor s = VendorDB.retrieveVendorByShop(sh);
        return s;
    }

    public static int updatePhone(int id, String ph) {
        int s = VendorDB.updatePhoneNo(id, ph);
        return s;
    }

    public static int updateSpec(int id, String spec) {
        int s = VendorDB.updateSpec(id, spec);
        return s;
    }
}
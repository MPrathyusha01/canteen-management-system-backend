package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Login;
import com.Hexaware.CMS.Persistence.LoginDB;

public class LoginFactory {
    public static int registerUser(String user, String pass) {
        int res = LoginDB.registerUser(user, pass);
        return res;
    }

    public static Login retrieveUser(String user) {
        Login l = LoginDB.retrieveUser(user);
        return l;
    }

    public static int updatePassword(String user, String pass) {
        int res = LoginDB.updatePassword(user, pass);
        return res;
    }
}
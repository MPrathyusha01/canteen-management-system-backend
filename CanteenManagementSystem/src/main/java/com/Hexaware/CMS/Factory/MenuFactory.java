package com.Hexaware.CMS.Factory;

import java.util.List;

import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Persistence.MenuDB;

public class MenuFactory {
    public static Menu[] getAllMenu() {
        List<Menu> list = MenuDB.retrieveAllMenu();
        Menu[] menu = list.toArray(new Menu[list.size()]);
        return menu;
    }

    public static Menu getById(int id) {
        Menu f = MenuDB.retrieveMenubyID(id);
        return f;
    }

    public static int addNewFood(String name, double amt, int ven, String cat) {
        int res = MenuDB.addNewFood(name, amt, ven, cat);
        return res;
    }

    public static int updatePrice(int id, double price) {
        int res = MenuDB.updatePrice(id, price);
        return res;
    }

    public static Menu[] retrieveByVendor(int id) {
        List<Menu> list = MenuDB.retrieveByVendor(id);
        Menu[] menu = list.toArray(new Menu[list.size()]);
        return menu;
    }

    public static Menu[] retrieveBySpec(String cat) {
        List<Menu> list = MenuDB.retriveBySpec(cat);
        Menu[] menu = list.toArray(new Menu[list.size()]);

        return menu;
    }

    
}
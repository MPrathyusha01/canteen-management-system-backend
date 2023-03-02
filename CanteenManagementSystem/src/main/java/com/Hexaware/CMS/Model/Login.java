package com.Hexaware.CMS.Model;

public class Login {
    private String username;
    private String password;
    private String usertype;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    public Login() {
    }
    public Login(String username, String password, String usertype) {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    
    @Override
    public String toString(){
        return "Login [User name = " +username+" , Password = "+password+ "User type = " + usertype +"]";
    }

}

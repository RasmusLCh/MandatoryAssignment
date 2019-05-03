package com.example.demo.Model;

import javax.servlet.http.HttpSession;

public class User {

    private String name;
    private String pass;
    private int type;

    public User(String Username, String Password, int userType){
        name = Username;
        pass = Password;
        type = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static int Type(HttpSession session){
        //0 = Not logged in
        //1 = Normal User
        //2 = Admin
        Object user = session.getAttribute("user");
        if(user instanceof User){
            return ((User)user).getType();
        }
        return 0;
    }
}

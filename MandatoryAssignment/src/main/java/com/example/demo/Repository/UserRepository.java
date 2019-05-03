package com.example.demo.Repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {

    PreparedStatement pstmt;
    private Connection con;
    public UserRepository() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String user = "mandatorylogin";
        String pwd = "Ks279oM-5?4A";
        String server = "den1.mysql5.gear.host";
        String db = "mandatorylogin";
        String port = "3306";
        con = DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+db, user, pwd);
    }

    public ResultSet ValidateUser(String Username, String Password) throws Exception {
        pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = md5(?)");
        pstmt.setString(1, Username);
        pstmt.setString(2, Password);
        return pstmt.executeQuery();
    }

    public void Insert (String name, String pw, int type) throws Exception {
        pstmt = con.prepareStatement("INSERT INTO users (UserName, Password, Type) VALUES (?, md5(?), ?)");
        pstmt.setString(1, name);
        pstmt.setString(2, pw);
        pstmt.setInt(3, type);
        pstmt.execute();
        pstmt.close();
    }

    public ResultSet Read (String username) throws Exception{
        pstmt = con.prepareStatement("SELECT * FROM mandatorylogin.users WHERE UserName=?");
        pstmt.setString(1, username);
        return pstmt.executeQuery();
    }

    public ResultSet Read (int ID) throws Exception{
        pstmt = con.prepareStatement("SELECT * FROM mandatorylogin.users WHERE id=?");
        pstmt.setInt(1, ID);
        return pstmt.executeQuery();
    }
    public void Update (String name, String password, int type) throws Exception{
        pstmt = con.prepareStatement("UPDATE users SET Password=md5(?), Type=? WHERE UserName=?");
        pstmt.setString(1, password);
        pstmt.setInt(2, type);
        pstmt.setString(3, name);
        pstmt.execute();
        pstmt.close();
    }

    public void Delete(String name) throws Exception {
        pstmt = con.prepareStatement("DELETE FROM users WHERE UserName=?");
        pstmt.setString(1, name);
        pstmt.execute();
        pstmt.close();
    }

    public void Delete(int ID) throws Exception {
        pstmt = con.prepareStatement("DELETE FROM users WHERE id=?");
        pstmt.setInt(1, ID);
        pstmt.execute();
        pstmt.close();
    }
}

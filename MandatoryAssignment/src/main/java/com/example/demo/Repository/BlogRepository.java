package com.example.demo.Repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class BlogRepository {

    PreparedStatement pstmt;
    private Connection con;
    public BlogRepository() throws Exception {
        //Lets load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //GearHost
        String user = "mandatorylogin";
        String pwd = "Ks279oM-5?4A";
        String server = "den1.mysql5.gear.host";
        String db = "mandatorylogin";
        String port = "3306";
        con = DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+db, user, pwd);
    }

    public void Insert (String header, String text, Date date) throws Exception {
        pstmt = con.prepareStatement("INSERT INTO mandatorylogin.blogs (text, date, header) VALUES (?, ?, ?)");
        pstmt.setString(1, text);
        pstmt.setDate(2, date);
        pstmt.setString(3, header);
        pstmt.execute();
        pstmt.close();
    }

    public ResultSet Read (int id) throws Exception{
        pstmt = con.prepareStatement("SELECT * FROM mandatorylogin.blogs WHERE id=?");
        pstmt.setInt(1, id);
        return pstmt.executeQuery();
    }

    public ResultSet ReadAll () throws Exception{
        pstmt = con.prepareStatement("SELECT * FROM mandatorylogin.blogs ORDER BY id DESC");
        return pstmt.executeQuery();
    }

    public void Update (int id, String text, Date date, String header) throws Exception{
        pstmt = con.prepareStatement("UPDATE mandatorylogin.blogs SET text=?, date=?, header=? WHERE id=?");
        pstmt.setString(1, text);
        pstmt.setDate(2, date);
        pstmt.setString(3, header);
        pstmt.setInt(4, id);
        pstmt.execute();
        pstmt.close();
    }

    public void Delete(int id) throws Exception {
        pstmt = con.prepareStatement("DELETE FROM mandatorylogin.blogs WHERE id=?");
        pstmt.setInt(1, id);
        pstmt.execute();
        pstmt.close();
    }

}

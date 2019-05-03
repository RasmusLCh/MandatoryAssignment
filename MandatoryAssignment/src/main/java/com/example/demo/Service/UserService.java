package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository UR;

    public User getUser(int ID) throws Exception {
        ResultSet rs = UR.Read(ID);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("UserName"), "", rs.getInt("Type"));
        }
        return user;
    }

    public User validateUser(String name, String password) throws Exception {
        ResultSet rs = UR.ValidateUser(name, password);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("UserName"), "", rs.getInt("Type"));
        }
        return user;
    }

    public void createUser(User User) throws Exception {
        UR.Insert(User.getName(), User.getPass(), User.getType());
    }

    public void editUser(User User) throws Exception {
        UR.Update(User.getName(), User.getPass(), User.getType());
    }

    public void deleteUser(int ID) throws Exception {
        UR.Delete(ID);
    }

}

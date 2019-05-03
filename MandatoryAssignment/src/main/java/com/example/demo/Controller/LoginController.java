package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService US;

    @GetMapping("/")
    public String Root(HttpSession session, Model model) {
        if(User.Type(session) != 0){
            return "redirect:/blog";
        }
        return "login";
    }

    @GetMapping("/login")
    public String Login(HttpSession session, Model model) {
        if(User.Type(session) != 0){
            return "redirect:/blog";
        }
        return "login";
    }

    @PostMapping("/login")
    public String Validate(@RequestParam(value="username") String username, @RequestParam(value="password") String password, Model model, HttpSession session)  {
        try {
            session.setAttribute("user", US.validateUser(username, password));
            if(User.Type(session) == 0){
                return "login";
            }
            model.addAttribute("session", session);
            model.addAttribute("userType", User.Type(session));
            return "redirect:/blog";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/createuser")
    public String Create(HttpSession session) {
        if(User.Type(session) != 0){
            return "blog";
        }
        return "createuser";
    }

    @PostMapping("/createuser")
    public String CreateUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password, Model model, HttpSession session)  {
        try {
            US.createUser(new User(username, password, 1));
            return "/login";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "/login";
    }

}

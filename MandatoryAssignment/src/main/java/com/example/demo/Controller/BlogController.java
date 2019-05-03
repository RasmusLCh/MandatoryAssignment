package com.example.demo.Controller;

import com.example.demo.Model.Blog;
import com.example.demo.Model.User;
import com.example.demo.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class BlogController {

    @Autowired
    private BlogService BS;

    @GetMapping("/blog")
    public String BlogLanding(HttpSession session, Model model) {
        if(User.Type(session) != 0){
            try {
                model.addAttribute("blogs", BS.getAllBlogs());
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("userType", User.Type(session));
            return "blog";
        }
        return "login";
    }

    @GetMapping("/createBlog")
    public String create(Model model, HttpSession session) {
        if(User.Type(session) != 2) {
            return "redirect:/error";
        }
        return "createBlog";
    }

    @PostMapping("/createBlog")
    public String createBlog(@RequestParam(value="header") String header,
                             @RequestParam(value="text") String text,
                             @RequestParam(value="date") String date,
                             HttpSession session){
        if(User.Type(session) != 2) {
            return "redirect:/error";
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
            format.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            try {
                Date dateformatted = new Date(format.parse(date).getTime()+1000*60*60*8);
                BS.createBlog(new Blog(header, text, dateformatted));
            } catch (Exception e) {
                return "redirect:error";
            }
            return "redirect:/blog";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }
}

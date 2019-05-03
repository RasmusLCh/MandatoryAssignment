package com.example.demo.Service;

import com.example.demo.Model.Blog;
import com.example.demo.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository BR;

    public Blog getBlog(int ID) throws Exception {
        ResultSet rs = BR.Read(ID);
        Blog blog = null;
        if (rs.next()) {
            blog = new Blog(rs.getString("header"), rs.getString("text"), rs.getDate("date"));
        }
        return blog;
    }

    public List<Blog> getAllBlogs() throws Exception {
        ResultSet rs = BR.ReadAll();
        List<Blog> blogs = new ArrayList<>();
        while (rs.next()){
            blogs.add(new Blog(rs.getString("header"), rs.getString("text"), rs.getDate("date")));
        }
        return blogs;
    }

    public void createBlog(Blog blog) throws Exception {
        BR.Insert(blog.getHeader(), blog.getText(), blog.getDate());
    }

    public void editBlog(Blog blog) throws Exception {
        BR.Update(0, blog.getText(), blog.getDate(), blog.getHeader());
    }

    public void deleteBlog(int ID) throws Exception {
        BR.Delete(ID);
    }

}
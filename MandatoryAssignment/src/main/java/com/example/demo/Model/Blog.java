package com.example.demo.Model;

import java.sql.Date;

public class Blog {
    private String header;
    private String text;
    private Date date;

    public Blog(String Header, String Text, Date date){
        header = Header;
        text = Text;
        this.date = date;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.example.myapplication.model;

import java.util.Date;

public class Comment implements Activity {

    private Date createAt;

    public Comment(Date createAt){
        this.createAt = createAt;
    }

    @Override
    public Date getCreatedAt() {
        return createAt;
    }
}

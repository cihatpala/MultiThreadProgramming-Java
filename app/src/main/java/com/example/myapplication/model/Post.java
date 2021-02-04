package com.example.myapplication.model;

import java.util.Date;

public class Post implements Activity {

    private Date createAt;

    public Post(Date createAt){
        this.createAt = createAt;
    }

    @Override
    public Date getCreatedAt() {
        return createAt;
    }
}

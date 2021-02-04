package com.example.myapplication.model;

import java.util.Date;

public class Friend implements Activity {

    private Date createAt;

    public Friend(Date createAt){
        this.createAt = createAt;
    }

    @Override
    public Date getCreatedAt() {
        return createAt;
    }
}

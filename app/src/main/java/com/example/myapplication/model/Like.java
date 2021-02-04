package com.example.myapplication.model;

import java.util.Date;

public class Like implements Activity {

    private Date createAt;

    public Like(Date createAt){
        this.createAt = createAt;
    }

    @Override
    public Date getCreatedAt() {
        return createAt;
    }
}

package com.example.myapplication;

import com.example.myapplication.model.Activity;

import java.util.List;

public interface ResultCallback {

    void onResult(List<Activity> activityList);
}

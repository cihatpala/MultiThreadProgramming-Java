package com.example.myapplication;
import com.example.myapplication.model.Activity;
import com.example.myapplication.model.Comment;
import com.example.myapplication.model.Friend;
import com.example.myapplication.model.Like;
import com.example.myapplication.model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class RemoteService {

    private static int cores = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService = Executors.newFixedThreadPool(cores+1);

    public void stop(){
        executorService.shutdown();
    }

    public void getUserRecentActivities(ResultCallback callback){
        executorService.execute(() ->{
            List<Like> likes = new ArrayList<>();
            List<Post> posts = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();
            List<Friend> friends = new ArrayList<>();

            try {
                Future<List<Like>> futureLikes =  executorService.submit(getLikes("https://dummy.com/likes"));
                likes = futureLikes.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                Future<List<Comment>> futureComments =  executorService.submit(getComments("https://dummy.com/comments"));
                comments = futureComments.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                Future<List<Post>> futurePosts =  executorService.submit(getPosts("https://dummy.com/posts"));
                posts = futurePosts.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                Future<List<Friend>> futureFriends =  executorService.submit(getFriends("https://dummy.com/friends"));
                friends = futureFriends.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            List<Activity> activities = new ArrayList<>();
            activities.addAll(likes);
            activities.addAll(comments);
            activities.addAll(posts);
            activities.addAll(friends);

            Collections.sort(activities, (activity1, activity2) -> activity1.getCreatedAt().compareTo(activity2.getCreatedAt()));

            callback.onResult(activities);

        });
    }

    private Callable<List<Like>> getLikes(String url) throws InterruptedException {
        return () -> {
            System.out.println("getLikes");
            Thread.sleep(2000);
            return Arrays.asList(new Like(new Date(1612892239730L)), new Like(new Date(1612892235530L)));
        };
    }

    private Callable<List<Post>> getPosts(String url) throws InterruptedException {
        return () -> {
            System.out.println("getPost");
            Thread.sleep(1000);
            return Arrays.asList(new Post(new Date(1612892243730L)), new Post(new Date(1612892222530L)));
        };
    }

    private Callable<List<Comment>> getComments(String url) throws InterruptedException {
        return () -> {
            System.out.println("getPost");
            Thread.sleep(2500);
            return Arrays.asList(new Comment(new Date(1612892243730L)), new Comment(new Date(1612892222530L)));
        };
    }

    private Callable<List<Friend>> getFriends(String url) throws InterruptedException {
        return () -> {
            System.out.println("getPost");
            Thread.sleep(800);
            return Arrays.asList(new Friend(new Date(1612863244230L)), new Friend(new Date(1612892762530L)));
        };
    }
}

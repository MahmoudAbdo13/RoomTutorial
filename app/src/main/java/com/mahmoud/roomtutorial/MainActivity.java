package com.mahmoud.roomtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostsDatabase database = PostsDatabase.getInstance(this);
        database.postsDao().inserPost(new Post(2, "Mahmoud", "Room Database Studying"));

    }
}
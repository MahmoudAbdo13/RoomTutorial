package com.mahmoud.roomtutorial;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostsDao {

    @Insert
    void inserPost(Post post);

    @Query("select * from post_table")
    List<Post> getPosts();
}

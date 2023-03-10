package com.mahmoud.roomtutorial;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PostsDao {

    @Insert
    Completable insertPost(Post post);

    @Query("select * from post_table")
    Single<List<Post>> getPosts();
}

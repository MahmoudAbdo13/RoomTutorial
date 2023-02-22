package com.mahmoud.roomtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mahmoud.roomtutorial.databinding.ActivityMainBinding;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PostsAdapter adapter = new PostsAdapter();
        binding.postsRecyclerView.setAdapter(adapter);

        final PostsDatabase database = PostsDatabase.getInstance(this);

        binding.insertButton.setOnClickListener(view -> {
            String title, body;
            title = binding.editTexttitle.getText().toString();
            body = binding.editTextBody.getText().toString();

            if (title.isEmpty()){
                binding.editTexttitle.setError("Please enter post title");
                binding.editTexttitle.setFocusable(true);
            }else if (body.isEmpty()){
                binding.editTexttitle.setError("Please enter post title");
                binding.editTexttitle.setFocusable(true);
            }else {
                database.postsDao().insertPost(new Post(3, title, body))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG, "Post has been added successfully");
                                Toast.makeText(MainActivity.this, "Post has been added successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        
        binding.getButton.setOnClickListener(view -> {
            database.postsDao().getPosts()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Post>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull List<Post> posts) {
                            adapter.setList(posts);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        });


    }
}
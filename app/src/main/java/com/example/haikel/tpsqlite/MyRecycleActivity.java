package com.example.haikel.tpsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.haikel.tpsqlite.model.Post;
import com.example.haikel.tpsqlite.service.ContactService;
import com.example.haikel.tpsqlite.service.IContactApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);

        recyclerView = findViewById(R.id.myRecy);

        Call<List<Post>> listCall = ContactService.getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MyRecycleActivity.this, "An error "+response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                generatedAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MyRecycleActivity.this, "An error "+t.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.d("Error ", t.getMessage());
            }
        });
    }

    private void generatedAdapter(List<Post> list) {
        postAdapter = new MyAdapter(MyRecycleActivity.this, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecycleActivity.this));
        recyclerView.setAdapter(postAdapter);
    }
}

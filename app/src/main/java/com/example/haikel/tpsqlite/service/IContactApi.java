package com.example.haikel.tpsqlite.service;

import com.example.haikel.tpsqlite.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IContactApi {

    @GET("posts")
    Call<List<Post>> getPosts();



}

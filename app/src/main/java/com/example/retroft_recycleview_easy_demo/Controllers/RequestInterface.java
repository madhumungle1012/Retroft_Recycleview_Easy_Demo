package com.example.retroft_recycleview_easy_demo.Controllers;

import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.AndroidVersionJsonResponce;
import com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<AndroidVersionJsonResponce> getJSON();


    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);


}

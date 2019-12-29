package com.example.retroft_recycleview_easy_demo.Model.AndroidVersions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AndroidVersionJsonResponce {

    @SerializedName("android")
    @Expose
    private List<Android_getter_setter> androidGettersetter = null;

    public List<Android_getter_setter> getAndroidGettersetter() {
        return androidGettersetter;
    }

    public void setAndroidGettersetter(List<Android_getter_setter> androidGettersetter) {
        this.androidGettersetter = androidGettersetter;
    }

}
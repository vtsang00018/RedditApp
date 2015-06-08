package com.example.vincent.redditapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent on 6/8/2015.
 */
public class Children {

    @SerializedName("data")
    private Post post;

    public Post getPost(){
        return post;
    }
}


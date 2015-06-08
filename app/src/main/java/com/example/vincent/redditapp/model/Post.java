package com.example.vincent.redditapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent on 6/8/2015.
 */
public class Post {

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    public String getPermalink() {
        return permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}

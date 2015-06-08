package com.example.vincent.redditapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vincent on 6/8/2015.
 */
public class ChildrenArray {

    @SerializedName("children")

    private List<Children> mChildrenList;
}

package com.example.vincent.redditapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 6/8/2015.
 */
public class Listing {

    //Each listing has a children array -> which has array of children -> which has a post

    @SerializedName("data")
    private ChildrenArray mChildrenArray;

    public List<Post> getPostList(){
        List<Post> postList = new ArrayList<Post>();

        for(Children children : mChildrenArray.getChildrenList()){
            postList.add(children.getPost());
        }
        return postList;
    }
}

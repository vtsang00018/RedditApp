package com.example.vincent.redditapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * This Class enables a Singleton Pattern for the Request Queue
 * We only want one queue used throughout the application
 * Created by Vincent on 6/8/2015.
 */
public class ConnectionManager {
    // obtain post information from url
    private static RequestQueue queue;
    // download image information from any part of the application
    private static ImageLoader sImageLoader;

    public static RequestQueue getInstance(Context context){
        // if queue doesn't exist yet, create one
        if(queue == null)
            queue = Volley.newRequestQueue(context);
        // if queue exists, return original queue
        return queue;
    }

}

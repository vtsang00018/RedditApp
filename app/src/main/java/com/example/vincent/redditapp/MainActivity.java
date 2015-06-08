package com.example.vincent.redditapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vincent.redditapp.model.Listing;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity {

    public final String REDDIT_URL = "http://www.reddit.com/r/all.json?limit=5";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerListView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Instantiate the RequestQueue.
        RequestQueue queue = ConnectionManager.getInstance(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REDDIT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Gets the post list from the listing
                        Listing listing = new Gson().fromJson(response, Listing.class);
                        // create an adapter -> pass in the post list
                        RedditAdapter adapter = new RedditAdapter(listing.getPostList());
                        // sets the adapter to the RecyclerView
                        mRecyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

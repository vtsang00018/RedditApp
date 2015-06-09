package com.example.vincent.redditapp;

import android.content.Context;
import android.content.Intent;
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
import com.example.vincent.redditapp.database.RedditDAO;
import com.example.vincent.redditapp.model.Listing;
import com.example.vincent.redditapp.model.Post;
import com.google.gson.Gson;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends ActionBarActivity implements RedditAdapter.MyListItemClickListener {

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

                        List<Post> mPostList = listing.getPostList();

                        // create an adapter -> pass in the post list
                        RedditAdapter adapter = new RedditAdapter(mPostList, MainActivity.this, MainActivity.this);

                        // Store the post list to the database
                        RedditDAO.getInstance().storePosts(MainActivity.this, mPostList);

                        // sets the adapter to the RecyclerView
                        mRecyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Offline mode
                // get the posts from the database
                List<Post> mPostList = RedditDAO.getInstance().getPostsFromDB(MainActivity.this);
                // create an adapter -> pass in the post list
                RedditAdapter adapter = new RedditAdapter(mPostList, MainActivity.this, MainActivity.this);
                // sets the adapter to the RecyclerView
                mRecyclerView.setAdapter(adapter);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(Post itemClicked) {
        Intent webIntent = new Intent(MainActivity.this, WebActivity.class);
        webIntent.putExtra("URL", itemClicked.getPermalink());
        startActivity(webIntent);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

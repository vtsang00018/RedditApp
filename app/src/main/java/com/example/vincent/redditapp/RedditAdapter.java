package com.example.vincent.redditapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vincent.redditapp.model.Post;

import java.util.List;

/**
 * Created by Vincent on 6/8/2015.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {

    List<Post> mPostList;

    // The adapter will get the post list with a constructor
    public RedditAdapter(List<Post> postList) {
        mPostList = postList;
    }

    // Inflates the row_post for every element in the list
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_post, viewGroup, false);
        return new MyViewHolder(view);
    }

    // sets the title of every row_post according to the elements of the list
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mTextViewPostName.setText(mPostList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    // Creates a ViewHolder - contains information about the view...
    // In this case, gets the textView of the row_post.xml
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewPostName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewPostName = (TextView)itemView.findViewById(R.id.rowTextViewName);
        }
    }
}

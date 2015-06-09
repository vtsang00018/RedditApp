package com.example.vincent.redditapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.vincent.redditapp.model.Post;

import java.util.List;

/**
 * Created by Vincent on 6/8/2015.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {

    List<Post> mPostList;
    Context mContext;
    // keeps track of the context that is listening for the click
    MyListItemClickListener mListener;

    // The adapter will get the post list with a constructor
    public RedditAdapter(List<Post> postList, Context context, MyListItemClickListener listener) {
        mPostList = postList;
        mContext = context;
        mListener = listener;
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
        Post current = mPostList.get(i);
        myViewHolder.mTextViewPostName.setText(current.getTitle());

        if (!TextUtils.isEmpty(current.getThumbnail())) {
            ((View)myViewHolder.mNetworkImageView.getParent()).setVisibility(View.VISIBLE);
            myViewHolder.mNetworkImageView.setImageUrl(current.getThumbnail(),
                    ConnectionManager.getImageLoader(mContext));
        } else{
            ((View)myViewHolder.mNetworkImageView.getParent()).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    // Creates a ViewHolder - contains information about the view...
    // In this case, gets the textView and imageView of the row_post.xml
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextViewPostName;
        public NetworkImageView mNetworkImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewPostName = (TextView)itemView.findViewById(R.id.rowTextViewName);
            mNetworkImageView = (NetworkImageView)itemView.findViewById(R.id.rowNetworkImage);
            itemView.setOnClickListener(this);
        }

        // if click is found, run the onItemClick method (definied in MainActivity) on the post
        @Override
        public void onClick(View v) {
            mListener.onItemClick(mPostList.get(getPosition()));
        }
    }

    // create an interface to customize the onItemClick method
    // Since, our parameter is a Post, we need to customize because the Android's default onClick()
    // method uses a View as a parameter
    public static interface MyListItemClickListener{
        public void onItemClick(Post itemClicked);
    }
}

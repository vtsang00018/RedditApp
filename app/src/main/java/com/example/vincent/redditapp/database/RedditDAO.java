package com.example.vincent.redditapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vincent.redditapp.model.Post;

import java.util.ArrayList;
import java.util.List;

/** Database Access Object
 * Created by Vincent on 6/8/2015.
 */
public class RedditDAO {

    /**
     * Singleton pattern
     */
    private static RedditDAO sInstance = null;

    public static RedditDAO getInstance(){
        if(sInstance == null){
            sInstance = new RedditDAO();
        }
        return sInstance;
    }

    // Stores the Post information in the database
    public boolean storePosts(Context context, List<Post> postList) {

        List<Post> dbPosts = RedditDAO.getInstance().getPostsFromDB(context);

        // gets the database
        SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
        // opens the database for writing
        db.beginTransaction();

        try {

            // for the posts in postList, put the data into the data table
            for (Post post : postList) {
                boolean isInDB = false;
                // check for duplicate posts
                for (Post storedPosts : dbPosts){
                    if(storedPosts.getTitle().equals(post.getTitle())){
                        isInDB = true;
                    }
                }
                // if post isnt duplicate, add it to the database
                if(!isInDB) {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseContract.PostTable.TITLE, post.getTitle());
                    cv.put(DatabaseContract.PostTable.LINK, post.getPermalink());
                    cv.put(DatabaseContract.PostTable.IMAGELINK, post.getThumbnail());

                    db.insert(DatabaseContract.PostTable.TABLE_NAME, null, cv);
                }
            }
            // close the database
            db.setTransactionSuccessful();
            db.endTransaction();

            db.close();

        } catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Post> getPostsFromDB(Context context){
        SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();

        Cursor cursor = db.query(DatabaseContract.PostTable.TABLE_NAME, null,null,null,null,null,null);

        cursor.moveToFirst();

        List<Post> postList = new ArrayList<>();

        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.TITLE));
            String link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.LINK));
            String imageLink = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.IMAGELINK));

            Post post = new Post(link, imageLink, title);
            postList.add(post);
        }
        cursor.close();
        db.close();

        return postList;
    }
}

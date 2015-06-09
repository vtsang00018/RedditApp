package com.example.vincent.redditapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.vincent.redditapp.model.Post;

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

        // gets the database
        SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
        // opens the database for writing
        db.beginTransaction();

        try {
            // for the posts in postList, put the data into the data table
            for (Post post : postList) {
                ContentValues cv = new ContentValues();
                cv.put(DatabaseContract.PostTable.TITLE, post.getTitle());
                cv.put(DatabaseContract.PostTable.LINK, post.getPermalink());
                cv.put(DatabaseContract.PostTable.IMAGELINK, post.getThumbnail());

                db.insert(DatabaseContract.PostTable.TABLE_NAME, null, cv);
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
}

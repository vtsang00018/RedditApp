package com.example.vincent.redditapp.database;

/** Class will have structure of the database
 * Created by Vincent on 6/8/2015.
 */
public class DatabaseContract {

    public static final String DB_NAME = "reddit.database.db";

    // Structure: One table, three columns
    public abstract class PostTable{
        // table name
        public static final String TABLE_NAME = "post_table";
        // table properties (Columns)
        public static final String TITLE = "title";
        public static final String LINK = "link";
        public static final String IMAGELINK = "imageLink";
    }
}


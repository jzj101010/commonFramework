package com.jjz.frameworkdemo.data.db;

import android.content.Context;

import androidx.room.Room;




public class DBManager {

    private final String DB_NAME_FORMAT = "user_%s";

    private static DBManager instance = null;

    private BLDatabase database;

    /**
     * 当前用户id
     */
    private String currentUserId;

    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    public void init(Context mContext, String userId) {
        currentUserId = userId;
        database = Room.databaseBuilder(mContext, BLDatabase.class, String.format(DB_NAME_FORMAT, currentUserId))
                .allowMainThreadQueries()//允许在主线程中查询
                .build();

//        Room.databaseBuilder(mContext, BLDatabase.class, String.format(DB_NAME_FORMAT, currentUserId)).build();
    }

    public BLDatabase  getDataBase(){
        return  database;
    }


    public void closeDb() {
        if (database != null) {
            database.close();
        }
        currentUserId = "";
    }



}

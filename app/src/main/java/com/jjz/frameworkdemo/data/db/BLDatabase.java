package com.jjz.frameworkdemo.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jjz.frameworkdemo.data.bean.TestBean;
import com.jjz.frameworkdemo.data.db.dao.TestDao;

/**
 * @author Administrator
 */
@Database(entities = {TestBean.class}, version = 1)
public abstract class BLDatabase extends RoomDatabase {

    public abstract TestDao testDao();

}

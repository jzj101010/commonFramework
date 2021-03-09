package com.jjz.frameworkdemo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jjz.frameworkdemo.data.bean.TestBean;

import java.util.List;


@Dao
public interface TestDao {


    @Query("SELECT * FROM test")
    List<TestBean> query();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFriends(List<TestBean> TestBeans);
//
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateFriend(TestBean TestBean);


    @Delete
    void delete(TestBean TestBean);

    @Delete
    void deletes(TestBean... users);

//
//
//    @Query("SELECT * FROM test WHERE id LIKE :friendId")
//    TestBean queryFriendByID(String friendId);
//
//
//    //根据名字删除
//    @Query("DELETE  FROM test WHERE id=:friendId")
//    void deleteFormFriendId(String friendId);




}

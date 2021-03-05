package com.jjz.frameworkdemo

import android.app.Application
import com.jjz.common.CommonUtils
import com.jjz.frameworkdemo.data.db.DBManager

class MyApplication :Application() {


    override fun onCreate() {
        super.onCreate()
        CommonUtils.init(this)
        DBManager.getInstance().init(this,"AccountUtils.getUser().getId()")
    }



}
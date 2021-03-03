package com.jjz.frameworkdemo

import android.app.Application
import com.jjz.common.CommonUtils

class MyApplication :Application() {


    override fun onCreate() {
        super.onCreate()
        CommonUtils.init(this)
    }



}
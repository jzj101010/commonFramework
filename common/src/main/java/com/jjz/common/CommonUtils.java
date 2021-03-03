package com.jjz.common;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.Utils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.tencent.mmkv.MMKV;


public class CommonUtils {

    /**
     * 请在Application.create()中调用此方法初始化对应库
     * @param application
     */
    static public void init(Application application){
        MMKV.initialize(application);
        Utils.init(application);
    }
}

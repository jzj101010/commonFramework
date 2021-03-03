package com.jjz.frameworkdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.http.bean.TestBean
import com.jjz.frameworkdemo.http.repository.HttpRepository

class HttpRequestViewModel : BaseViewModel() {


    var testLiveValue=MutableLiveData<String>()



    fun getData(){
        var mObserver = HttpRepository.getHttpData("", 1, 0)
       request<Array<TestBean>>(mObserver){
           //结果
           testLiveValue.value=GsonUtils.toJson(it)

       }
    }

    fun getListData(){
        requestList<TestBean>(HttpRepository.getHttpData("", 1, 0)){
            //列表结果

            testLiveValue.value=GsonUtils.toJson(it.content)
        }
    }






}
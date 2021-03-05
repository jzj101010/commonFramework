package com.jjz.frameworkdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.data.http.repository.HttpRepository

class ViewModelViewModel : BaseViewModel() {


    var testLiveValue=MutableLiveData<String>()



    fun getData(){
        var mObserver = HttpRepository.getHttpData("", 1, 0)
       request<EmptyBean>(mObserver){
           //结果


       }
    }

    fun getListData(){
        requestList<EmptyBean>(HttpRepository.getHttpData("", 1, 0)){
            //列表结果


        }
    }






}
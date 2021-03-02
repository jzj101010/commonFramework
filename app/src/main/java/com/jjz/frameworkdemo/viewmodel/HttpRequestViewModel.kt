package com.jjz.frameworkdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjz.common.http.HttpResultUtils
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.repository.HttpRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HttpRequestViewModel : BaseViewModel() {


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
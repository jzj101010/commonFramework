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
        HttpRepository.getHttpData("",1,0)
            .map(HttpResultUtils.HandleResult<EmptyBean>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<EmptyBean> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: EmptyBean) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }








}
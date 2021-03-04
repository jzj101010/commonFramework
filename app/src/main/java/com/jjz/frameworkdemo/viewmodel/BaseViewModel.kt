package com.jjz.frameworkdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ToastUtils
import com.jjz.common.http.HttpResultUtils
import com.jjz.common.http.result.HttpResult
import com.jjz.common.http.result.ListData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {



    var isRequest = MutableLiveData<Boolean>()

    /**
     * 通知 是否显示loading
     */
    var isShowLoadingUI = MutableLiveData<Boolean>()

    /**
     * 通知 关闭页面
     */
    var finishPage = MutableLiveData<Boolean>()

    /**
     * 仅针对与单次网络请求的封装，若在链式多次请求场景下，则还需要自行拼接链式请求
     */
    inline fun <reified T> request(
        observable: Observable<HttpResult>,
        crossinline callBack: (T?) -> Unit
    ) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(HttpResultUtils.HandleResult<T>())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    isRequest.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    isRequest.value = true
                }

                override fun onNext(t: T) {
                    callBack.invoke(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("netError", e.message ?: "")
                    callBack.invoke(null)
                    ToastUtils.showShort(e?.message)
                    isRequest.value = false
                }

            })
    }

    inline fun <reified T> requestList(
        observable: Observable<HttpResult>,
        crossinline callBack: (ListData<T>?) -> Unit
    ) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(HttpResultUtils.HandleResultList<T>())
            .subscribe(object : Observer<ListData<T>> {
                override fun onComplete() {
                    isRequest.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    isRequest.value = true
                }

                override fun onNext(t: ListData<T>) {
                    callBack.invoke(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("netError", e.message ?: "")
                    callBack.invoke(null)
                    ToastUtils.showShort(e?.message)
                    isRequest.value = false
                }

            })
    }

    fun setViewBehaviorObserver(owner: LifecycleOwner, viewBehavior: ViewBehavior) {
        this.isShowLoadingUI.observe(owner, androidx.lifecycle.Observer {
            viewBehavior.showLoadingUI(it)
        })
        this.finishPage.observe(owner, androidx.lifecycle.Observer {
            viewBehavior.finishPage(it)
        })
    }

}
package com.jjz.frameworkdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.jjz.common.SpUtils
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.http.bean.TestBean
import com.jjz.frameworkdemo.http.repository.HttpRepository

class HttpRequestViewModel : BaseViewModel() {

    var testLiveValue=MutableLiveData<String>()

    fun getData(){
        isShowLoadingUI.value=true
        var mObserver = HttpRepository.getHttpData("", 1, 0)
       request<Array<TestBean>>(mObserver){
           isShowLoadingUI.value=false
           it?.let {
               //结果 请求成功
               testLiveValue.value=GsonUtils.toJson(it)
           }

       }
    }

    fun getListData() {
        isShowLoadingUI.value=true
        requestList<TestBean>(HttpRepository.getHttpData("", 1, 0)) {
//            isShowLoadingUI.value=false
            it?.let {
                //列表结果
                SpUtils.encode("test", it?.content)
                testLiveValue.value = GsonUtils.toJson(it?.content)
            }
        }
    }
}
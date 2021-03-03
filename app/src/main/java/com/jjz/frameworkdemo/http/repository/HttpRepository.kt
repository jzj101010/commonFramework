package com.jjz.frameworkdemo.http.repository

import com.jjz.common.http.BaseRepository
import com.jjz.common.http.RetrofitParam
import com.jjz.common.http.result.HttpResult
import io.reactivex.Observable

object HttpRepository : BaseRepository() {

    override fun getMethodUrl(url: String): String {
        return url
    }


    fun getHttpData(param: String,page :Int,size :Int): Observable<HttpResult> {
        var param = RetrofitParam().newBuilder()
            .addPage(page)
            .addSize(size)
            .addParam("param", param).build()

        return apiService.getData(getMethodUrl("article/top/json"),param)
    }

}
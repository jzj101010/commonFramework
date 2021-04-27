package com.jjz.common.http


abstract class BaseRepository {

     var apiService = RetrofitManager.defaultRetrofit.create(ApiService::class.java)

    /**
     * 地址
     *
     * @param url 路由地址
     * @return
     */
    abstract fun getMethodUrl(url: String): String


}

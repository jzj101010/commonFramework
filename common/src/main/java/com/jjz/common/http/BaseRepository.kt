package com.jjz.common.http

import retrofit2.Retrofit


abstract class BaseRepository {

    public var apiService = BaseRetrofit.getRetrofit().create(ApiService::class.java)

    /**
     * 地址
     *
     * @param url 路由地址
     * @return
     */
    abstract fun getMethodUrl(url: String): String


}

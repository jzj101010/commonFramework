package com.jjz.common.http

import android.content.pm.ApplicationInfo
import android.os.Build
import com.blankj.utilcode.util.Utils
import com.jjz.common.BuildConfig
import com.jjz.common.Constant
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    val Tag = "BaseRetrofit"

    /**
     * OkHttpClient 文档
     * 创建一个OkHttpClient实例并将其用于所有HTTP调用时，OkHttp的性能最好。
     * 相反，为每个请求创建一个客户机会浪费空闲池上的资源。
     */
    var defaultRetrofit: Retrofit = getRetrofit()

    private fun getOkHttpClient(timeOut: Long): OkHttpClient {
        var builder = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            //Debug 模式下 信任所有证书,方便抓包
            builder.hostnameVerifier { hostname, session ->
                return@hostnameVerifier true
            }
            val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)
            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)

        }
        return builder
            .callTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .authenticator(object : Authenticator {
                override fun authenticate(route: Route?, response: Response): Request? {
                    if (response.code() == 401) {
                        TokenManage.setToken(null)
                    }
                    return response.request()
                }
            })
            .addInterceptor {
                val headBuilder = Headers.Builder()
                if (TokenManage.isLoginSuccess() && !"oauth/token".endsWith(it.request().url().toString())) {
                    val session = TokenManage.getToken()
                    headBuilder.add("Authorization", "Bearer $session")
                } else {
                    // 如果没登录情况获取 token 之类
                    val credential = Credentials.basic(
                            ApiConfig.CLIENT_USERNAME,
                            ApiConfig.CLIENT_PASSWORD
                    )
                    headBuilder.add("Authorization", credential)
                }
                val headers = headBuilder.add("os", "android")
                    .add("sdk", Build.VERSION.SDK_INT.toString())
                    .add("model", Build.MODEL)
                    .add("brand", Build.BRAND)
                    .add("device", Build.DEVICE)
                    .add("Content-Type", "application/json;charset=UTF-8")
                    .build()
                val newRequest = it.request().newBuilder().headers(headers).build()

                it.proceed(newRequest)
            }
//            .addInterceptor(OkHttpInterceptor.getHttpLoggingInterceptor(true))
            .build()

    }



     fun getBaseUrl(): String {

        val isDebug =BuildConfig.DEBUG
//            Utils.getApp().applicationInfo != null && Utils.getApp().applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
        return if (isDebug) {
            Constant.APP_HOST_DEBUG
        } else {
              Constant.APP_HOST_RELEASE
        }
    }

    fun getRetrofitUpFile(): Retrofit {
        val isDebug =BuildConfig.DEBUG
//            Utils.getApp().applicationInfo != null && Utils.getApp().applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
        val baseUrl = if (isDebug) {
            Constant.APP_HOST_DEBUG
        } else {
              Constant.APP_HOST_RELEASE
        }
        return getRetrofit(baseUrl, 60)
    }

    /**
     * 默认服务器，常用
     */
   private fun getRetrofit(): Retrofit {
        val baseUrl = getBaseUrl()
        return getRetrofit(baseUrl, 15)
    }

    /**
     * 自定义 服务器
     */
    fun getRetrofit(baseUrl: String, timeOut: Long): Retrofit {
        return getRetrofit(baseUrl, getOkHttpClient(timeOut))
    }



    fun getRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

}
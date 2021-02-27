package com.jjz.common.http

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jjz.common.http.result.AccessTokenBean
import com.jjz.common.http.result.HttpResult
import com.jjz.common.http.result.ListData
import io.reactivex.functions.Function


object HttpResultUtils {

    inline fun <reified T> HandleTokenResult(): Function<AccessTokenBean, AccessTokenBean> {
        return Function {
            if (it.access_token != null) {
                TokenManage.setToken(it.access_token)
                //FigoTokenManage.INSTANCE.setRefreshToken(bean.refresh_token);
            }
            it
        }
    }

    inline fun <reified T> HandleResult(): Function<HttpResult, T> {
        return Function {
            if (it.isSuccess) {
                if (it.data == null || it.data!!.isJsonNull) {
                    val clz = T::class.java
                    val mCreate = clz.getDeclaredConstructor()
                    mCreate. isAccessible = true
                    mCreate. newInstance()
                }
                if (it.data!!.isJsonPrimitive) {
                    /*data：false | “string” | 66.4  */
                     Gson().fromJson(
                        String.format("\"%s\"", it.data!!.asString),
                        T::class.java
                    )
                }
                Gson().fromJson(it.data, T::class.java)
            }else {
                throw Exception(it.message)
            }
        }
    }


    inline fun <reified T> HandleResultList(): Function<HttpResult, ListData<T>> {
        return Function {
            if (it.isSuccess && !it.data!!.isJsonNull)  {
                val listData = ListData<T>()
                val data = it.data as JsonObject
                if(data.has("totalElements")){
                    listData.totalElements = data.get("totalElements").asInt
                }

                if(data.has("totalPages")){
                    listData.totalPages = data.getAsJsonPrimitive("totalPages").asInt
                }

                if(data.has("last")){
                    listData.last = data.getAsJsonPrimitive("last").asBoolean
                }

                if(data.has("first")){
                    listData.first = data.getAsJsonPrimitive("first").asBoolean
                }

                if(data.has("numberOfElements")){
                    listData.numberOfElements = data.getAsJsonPrimitive("numberOfElements").asInt
                }

                if(data.has("size")){
                    listData.size = data.getAsJsonPrimitive("size").asInt
                }

                if(data.has("number")){
                    listData.number = data.getAsJsonPrimitive("number").asInt
                }

                val jsonArray = data.getAsJsonArray("content")
                if (listData.content == null) {
                    listData.content = arrayListOf()
                }
                jsonArray?.forEach {
                    listData.content.add(Gson().fromJson(it, T::class.java))
                }
                listData
            }  else {
                throw Exception(it.message)
            }
        }
    }


}
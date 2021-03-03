package com.jjz.common.http.result

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

//返回值的统一处理基类
class HttpResult {
    @SerializedName("errorCode")
    var code: Int = 200
    @SerializedName("errorMsg")
    var message: String = ""
    var data: JsonElement?=null


    val isSuccess: Boolean
        get() = code == 0

}
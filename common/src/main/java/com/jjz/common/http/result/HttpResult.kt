package com.jjz.common.http.result

import com.google.gson.JsonElement
import com.google.gson.JsonObject

//返回值的统一处理基类
class HttpResult {

    var code: Int = 200
    var message: String = ""
    var data: JsonElement?=null


    val isSuccess: Boolean
        get() = code == 200

}
package com.jjz.common.http

import android.text.TextUtils
import com.jjz.common.SpUtils

object TokenManage {
    private var isLoginSuccess: Boolean? = null
    private val tokenKey="token"
    private var token: String? = null


    fun setToken(token: String?) {
        SpUtils.encode(tokenKey,token)
        isLoginSuccess = !TextUtils.isEmpty(token)
        TokenManage.token = token
    }

    fun getToken(): String? {
        if (token != null) {
            return token
        }
        val s = SpUtils.decodeString(tokenKey)
        return if (TextUtils.isEmpty(s)) {
            isLoginSuccess = false
            null
        } else {
            isLoginSuccess = true
            s
        }
    }

    fun isLoginSuccess(): Boolean {
        if (isLoginSuccess == null || token == null) {
            getToken()
        }
        return isLoginSuccess ?: false
    }

}
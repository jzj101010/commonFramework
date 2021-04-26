package com.jjz.frameworkdemo

import com.jjz.common.http.BaseRetrofit
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testHttpRetrofit() {
        assertEquals(4, 2 + 2)
    }


}
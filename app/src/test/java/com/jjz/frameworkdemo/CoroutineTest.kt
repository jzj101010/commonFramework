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
class CoroutineTest {


    @Test
    fun runLunch() {
        // GlobalScope
        GlobalScope.launch {
         var name=   withContext(Dispatchers.IO){
                println("3.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                "33333"
            }
            println(name)
            println("1.执行task1.... [当前线程为：${Thread.currentThread().name}]")

        }
        println("2.执行task1.... [当前线程为：${Thread.currentThread().name}]")
        Thread.sleep(2000)
    }
    @Test
    fun runBlocking() {
        runBlocking{
           println("1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
        }
        println("2.执行task1.... [当前线程为：${Thread.currentThread().name}]")
    }

    @Test
    fun test_Coroutine6() {
        /**
         *  一个线程 按顺序请求50个接口，，全部成功后返回  耗时17S
         */
        var time = System.currentTimeMillis()

        CoroutineScope(Dispatchers.Default).launch {
            var list = mutableListOf<Deferred<String>>()
            for (i in 0..50) {
                var await = getHttpResult()
                println("$i---$await")
            }
            var sss=System.currentTimeMillis() - time
            print(sss/1000)
            println("S")
        }
        Thread.sleep(500000)
    }

    @Test
    fun test_Coroutine5() {
        /**
         *  一个线程 开启多个线程同时请求50个接口  ，，全部成功后返回 耗时3S
         */
        var time = System.currentTimeMillis()

        CoroutineScope(Dispatchers.Default).launch {
            var list = mutableListOf<Deferred<String>>()
            for (i in 0..50) {
                var async1 = GlobalScope.async {
                    getHttpResult()
                }
                list.add(async1)
            }
            list.forEachIndexed { index, deferred ->
                var await = deferred.await()
                println("$index---$await")
            }
            var sss=System.currentTimeMillis() - time
            print(sss/1000)
            println("S")
        }
        Thread.sleep(500000)
    }


    @Test
    fun test_Coroutine4() {
        var time = System.currentTimeMillis()

        CoroutineScope(Dispatchers.Default).launch {
            var async1 = GlobalScope.async {
                getHttpResult()
            }
            var async2 = GlobalScope.async {
                getHttpResult()
            }
            var result1 = async1.await()
            var result2 = async2.await()


            println(result1.length)
            println(result2.length)
            println(result1.length + result2.length)

            print(System.currentTimeMillis() - time)
            println("S")
        }
        Thread.sleep(5000)
    }


    private suspend fun getHttpResult(): String {
        try {
            val timeout: Long = 10
            val client = OkHttpClient()
                .newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .build()
            val request =
                Request.Builder().url("https://wanandroid.com/article/listproject/0/json").build()
            val response = client.newCall(request).execute()
            val result = response.body()!!.string()
            return result
        } catch (e: Exception) {
            println(e.message)
        }
        return ""
    }


    @Test
    fun test_Coroutine3() {
        var time = System.currentTimeMillis()

        var threadDefault: Thread? = null
        CoroutineScope(Dispatchers.Default).launch {
            threadDefault = Thread.currentThread()
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.Default)")
            println("Thread.currentThread()==threadDefault:" + (Thread.currentThread() == threadDefault))
        }
//        CoroutineScope(Dispatchers.Unconfined).launch {
//            println("Unconfined "+(System.currentTimeMillis()-time))
//            Thread.sleep(1000)
//            println("Unconfined "+(System.currentTimeMillis()-time))
//        }

        println("Unconfined " + (System.currentTimeMillis() - time))
        Thread.sleep(1000)
        println("Unconfined " + (System.currentTimeMillis() - time))


        println(System.currentTimeMillis() - time)
        Thread.sleep(1000)
        println(System.currentTimeMillis() - time)
    }

    @Test
    fun test_Coroutine2() {
        var threadMain = Thread.currentThread()
        var threadDefault: Thread? = null
        var threadIO: Thread? = null
        println(Thread.currentThread().name)
        CoroutineScope(Dispatchers.Unconfined).launch {
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.Unconfined)")
            println("Thread.currentThread()==threadMain:" + (Thread.currentThread() == threadMain))
        }
        CoroutineScope(Dispatchers.Default).launch {
            threadDefault = Thread.currentThread()
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.Default)")
            println("Thread.currentThread()==threadMain:" + (Thread.currentThread() == threadMain))
            println("Thread.currentThread()==threadDefault:" + (Thread.currentThread() == threadDefault))
        }
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.IO)")
        }

        Thread.sleep(1000)
    }

    @Test
    fun test_Coroutine() {
        println(Thread.currentThread())


        CoroutineScope(Dispatchers.Default).launch {
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.Default)")
        }
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.IO)")
        }
//        CoroutineScope(Dispatchers.Main).launch {
//            println(Thread.currentThread().name)
//        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            println(Thread.currentThread().name + "--CoroutineScope(Dispatchers.Unconfined)")
        }
        GlobalScope.async {
            println(Thread.currentThread().name + "--GlobalScope.async")
        }

        GlobalScope.launch {
            println(Thread.currentThread().name + "--GlobalScope.launch")
            val result1 = GlobalScope.async {
                println(Thread.currentThread().name + "--GlobalScope.launch-GlobalScope.async1")
                delay(2000)
                1
            }
            val result2 = GlobalScope.async {
                println(Thread.currentThread().name + "--GlobalScope.launch-GlobalScope.async2")
                getResult2()
            }
            val result = result1.await() + result2.await()
            println(result)
        }

//        Thread.sleep(5000)
    }

    private suspend fun getResult1(): Int {
        delay(2000)
        return 1
    }

    private suspend fun getResult2(): Int {
        delay(4000)
        return 2
    }

}
package com.jjz.frameworkdemo

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_Coroutine2() {
        var threadMain=Thread.currentThread()
        var threadDefault:Thread?=null
        var threadIO:Thread?=null
        println(Thread.currentThread().name)
        CoroutineScope(Dispatchers.Unconfined).launch {
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.Unconfined)")
            println("Thread.currentThread()==threadMain:"+(Thread.currentThread()==threadMain))

        }
        CoroutineScope(Dispatchers.Default).launch {
            threadDefault= Thread.currentThread()
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.Default)")
            println("Thread.currentThread()==threadMain:"+(Thread.currentThread()==threadMain))
            println("Thread.currentThread()==threadDefault:"+(Thread.currentThread()==threadDefault))
        }
        CoroutineScope(Dispatchers.IO).launch {
            threadDefault= Thread.currentThread()
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.IO)")
        }


        Thread.sleep(1000)
    }

    @Test
    fun test_Coroutine() {
        println(Thread.currentThread())


        CoroutineScope(Dispatchers.Default).launch {
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.Default)")
        }
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.IO)")
        }
//        CoroutineScope(Dispatchers.Main).launch {
//            println(Thread.currentThread().name)
//        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            println(Thread.currentThread().name+"--CoroutineScope(Dispatchers.Unconfined)")
        }
        GlobalScope.async {
            println(Thread.currentThread().name+"--GlobalScope.async")
        }

        GlobalScope.launch {
            println(Thread.currentThread().name+"--GlobalScope.launch")
            val result1 = GlobalScope.async {
                println(Thread.currentThread().name+"--GlobalScope.launch-GlobalScope.async1")
                delay(2000)
                1
            }
            val result2 = GlobalScope.async {
                println(Thread.currentThread().name+"--GlobalScope.launch-GlobalScope.async2")
                getResult2()
            }
            val result = result1.await() + result2.await()
            println(result)
        }

        Thread.sleep(5000)
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
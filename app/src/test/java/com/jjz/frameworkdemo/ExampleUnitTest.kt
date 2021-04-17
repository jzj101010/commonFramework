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
    fun test_Coroutine() {
        println(Thread.currentThread().name)
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name)
        }
        GlobalScope.launch {
            println(Thread.currentThread().name)
            val result1 = GlobalScope.async {
                delay(2000)
                1
            }
            val result2 = GlobalScope.async {
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
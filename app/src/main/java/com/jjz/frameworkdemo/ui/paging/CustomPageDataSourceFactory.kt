package com.jjz.frameworkdemo.ui.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jjz.common.http.HttpResultUtils
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.data.bean.TestBean
import com.jjz.frameworkdemo.data.http.repository.HttpRepository
import io.reactivex.Scheduler

class CustomPageDataSourceFactory : DataSource.Factory<Int, EmptyBean>() {
    override fun create(): DataSource<Int, EmptyBean> {
        return CustomPageDataSource()
    }

    class CustomPageDataSource() : PageKeyedDataSource<Int, EmptyBean>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, EmptyBean>) {
            callback.onResult(listOf(EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean()), null, 2)
        }
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EmptyBean>) {
            callback.onResult(listOf(EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean()), params.key + 1)
        }
        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EmptyBean>) {
                callback.onResult(listOf(EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean(),EmptyBean()), params.key - 1)
        }

    }
}


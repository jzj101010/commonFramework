package com.jjz.frameworkdemo.ui.paging

import android.os.Bundle
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jjz.common.http.result.EmptyBean
import com.jjz.frameworkdemo.databinding.ActivityRecyclerViewMoreBinding
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel
import java.util.*

/**
 * @author Administrator
 */
class RecyclerViewMoreActivity :
    BaseVMActivity<ActivityRecyclerViewMoreBinding?, HttpRequestViewModel?>() {
    private val list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = PagingAdapter()
        binding!!.recyclerView.adapter = adapter

        val data = LivePagedListBuilder(
            CustomPageDataSourceFactory(),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()
        data.observe(this, androidx.lifecycle.Observer<PagedList<EmptyBean>> {
            adapter?.submitList(it)
        }
        )


    }


}
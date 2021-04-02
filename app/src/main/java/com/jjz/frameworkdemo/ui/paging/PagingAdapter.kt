package com.jjz.frameworkdemo.ui.paging

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jjz.common.http.result.EmptyBean
class PagingAdapter : PagedListAdapter<EmptyBean, PagingAdapter.CustomViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EmptyBean>() {
            override fun areItemsTheSame(oldConcert: EmptyBean,
                                         newConcert: EmptyBean): Boolean =
                oldConcert == newConcert

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldConcert: EmptyBean,
                                            newConcert: EmptyBean): Boolean =
                oldConcert == newConcert

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = TextView(parent.context)
        itemView.text = "test"
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
    }

    class CustomViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        fun bindTo() {

        }
    }
}
package com.jjz.frameworkdemo.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jjz.frameworkdemo.R
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

class HttpRequestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_request)

        var viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)
        viewModel.getListData()

    }
}
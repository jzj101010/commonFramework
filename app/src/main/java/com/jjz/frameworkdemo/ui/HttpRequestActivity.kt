package com.jjz.frameworkdemo.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.jjz.frameworkdemo.R
import com.jjz.frameworkdemo.databinding.ActivityHttpRequestBinding
import com.jjz.frameworkdemo.databinding.ActivityViewModelKtBinding
import com.jjz.frameworkdemo.inflate
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel

class HttpRequestActivity : BaseActivity() {

    private  val binding: ActivityHttpRequestBinding by inflate()
    private  lateinit var  viewModel: HttpRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)
        viewModel.testLiveValue.observe(this){
            binding.tvCenter.text=it
        }
        viewModel.getListData()

    }
}
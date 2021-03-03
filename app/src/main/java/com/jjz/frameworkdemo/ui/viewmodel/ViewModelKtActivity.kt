package com.jjz.frameworkdemo.ui.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jjz.frameworkdemo.databinding.ActivityViewModelKtBinding
import com.jjz.frameworkdemo.inflate
import com.jjz.frameworkdemo.ui.BaseActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel

class ViewModelKtActivity : BaseActivity() {

    private  val binding: ActivityViewModelKtBinding by inflate()
    private  lateinit var  viewModel: ViewModelViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(ViewModelViewModel::class.java)
    }
}
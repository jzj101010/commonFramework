package com.jjz.frameworkdemo.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.jjz.frameworkdemo.BaseActivity
import com.jjz.frameworkdemo.data.db.DBManager
import com.jjz.frameworkdemo.databinding.ActivityDBDemoBinding
import com.jjz.frameworkdemo.inflate
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

class DBDemoActivity : BaseActivity() {

    private  val binding: ActivityDBDemoBinding by inflate()
    private  lateinit var  viewModel: HttpRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)
        viewModel.setViewBehaviorObserver(this,this)

        viewModel.listData.observe(this, Observer {
            DBManager.getInstance().dataBase.testDao().insertFriends(it)
            binding.btRequest.text="${binding.btRequest.text}-success"
        })
        binding.btRequest.setOnClickListener {
            viewModel.getHttpListData()
        }
        binding.btShowDbData.setOnClickListener {
            var query = DBManager.getInstance().dataBase.testDao().query()
            ToastUtils.showShort(query[0].id.toString())
            binding.tvContent.text=GsonUtils.toJson(query)
        }



    }
}
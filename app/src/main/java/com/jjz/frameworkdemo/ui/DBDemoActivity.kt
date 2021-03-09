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
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

class DBDemoActivity : BaseVMActivity<ActivityDBDemoBinding,HttpRequestViewModel>() {

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
            binding.tvContent.text=GsonUtils.toJson(query)
        }
        binding.btClearDbData.setOnClickListener {
            var testDao = DBManager.getInstance().dataBase.testDao()
            var query = testDao.query()
            query.forEach {
                testDao.delete(it)
            }
            binding.btShowDbData.performClick()
        }

    }
}
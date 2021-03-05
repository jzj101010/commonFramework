package com.jjz.frameworkdemo.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.jjz.frameworkdemo.BaseActivity
import com.jjz.frameworkdemo.databinding.ActivityDemoKtBinding
import com.jjz.frameworkdemo.inflate
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

/**
 * 这里也可以继承BaseVMActivity，就不需要手动初始化（根据自己喜好来）
 */
class DemoKtActivity : BaseActivity() {

    private  val binding: ActivityDemoKtBinding by inflate()
    private  lateinit var  viewModel: HttpRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 区别java
         * kotlin 只继承BaseActivity  这里手动初始化 和设置ViewBehavior
         */
        viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)
        viewModel.setViewBehaviorObserver(this,this)

        /**
         * 业务逻辑
         */
        viewModel.testLiveValue.observe(this){
            binding.tvCenter.text=it
        }
        binding.tvCenter.text="点击请求"
        binding.tvCenter.setOnClickListener {
            viewModel.getHttpListData()
        }

        binding.tvCode.text="class HttpRequestActivity : BaseActivity() {\n" +
                "\n" +
                "    private  val binding: ActivityHttpRequestBinding by inflate()\n" +
                "    private  lateinit var  viewModel: HttpRequestViewModel\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)\n" +
                "        viewModel.testLiveValue.observe(this){\n" +
                "            binding.tvCenter.text=it\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        binding.tvCenter.text=\"点击请求\"\n" +
                "        binding.tvCenter.setOnClickListener {\n" +
                "            viewModel.getListData()\n" +
                "        }\n" +
                "\n" +
                "        binding.tvCode.text=\"\"\n" +
                "\n" +
                "    }\n" +
                "}\n" +
                "#################################################\n\n"

    }
}
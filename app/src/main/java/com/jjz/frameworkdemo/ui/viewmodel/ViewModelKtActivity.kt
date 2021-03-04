package com.jjz.frameworkdemo.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjz.frameworkdemo.databinding.ActivityViewModelKtBinding
import com.jjz.frameworkdemo.inflate
import com.jjz.frameworkdemo.BaseActivity
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel

class ViewModelKtActivity : BaseActivity() {

    private  val binding: ActivityViewModelKtBinding by inflate()
    private  lateinit var  viewModel: ViewModelViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(ViewModelViewModel::class.java)

        viewModel.testLiveValue.observe(this,
            Observer<String> {
                binding.tvCenter.text=it
            })
        viewModel.testLiveValue.value="class ViewModelKtActivity : BaseActivity() {\n" +
                "\n" +
                "    private  val binding: ActivityViewModelKtBinding by inflate()\n" +
                "    private  lateinit var  viewModel: ViewModelViewModel\n" +
                "\n" +
                "\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        viewModel=ViewModelProvider(this).get(ViewModelViewModel::class.java)\n" +
                "\n" +
                "        viewModel.testLiveValue.observe(this,\n" +
                "            Observer<String> {\n" +
                "                binding.tvCenter.text=it\n" +
                "            })\n" +
                "        viewModel.testLiveValue.value=\"\"\n" +
                "        \n" +
                "    }\n" +
                "}"

    }
}
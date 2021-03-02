package com.jjz.frameworkdemo.ui.viewbinding.kotlin

import android.os.Bundle
import com.jjz.frameworkdemo.ui.BaseActivity
import com.jjz.frameworkdemo.databinding.ActivityViewBindingKtBinding
import com.jjz.frameworkdemo.inflate

class ViewBindingKtActivity : BaseActivity() {

    private val binding: ActivityViewBindingKtBinding by inflate()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_view_binding_kt)

        binding.tvCenter.text="class ViewBindingKtActivity : BaseActivity() {\n" +
                "\n" +
                "    private val binding: ActivityViewBindingKtBinding by inflate()\n" +
                "\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "//        setContentView(R.layout.activity_view_binding_kt)\n" +
                "\n" +
                "        binding.tvCenter.text=\"ViewBindingKtActivity\"\n" +
                "    }\n" +
                "}\n\n" +
                "###################################################################################\n"
    }
}
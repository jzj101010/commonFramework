package com.jjz.frameworkdemo.ui.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjz.frameworkdemo.databinding.ActivityViewBindingJavaBinding

class ViewBindingJavaActivity : BaseBindingActivity<ActivityViewBindingJavaBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding.tvCenter.text="" +
                "class ViewBindingJavaActivity : BaseBindingActivity<ActivityViewBindingJavaBinding>() {\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        binding.tvCenter=\"\"\n" +
                "    }\n" +
                "}"
    }
}
package com.jjz.frameworkdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.jjz.frameworkdemo.databinding.ActivityMainBinding
import com.jjz.frameworkdemo.ui.BaseActivity
import com.jjz.frameworkdemo.ui.HttpRequestActivity
import com.jjz.frameworkdemo.ui.viewbinding.java.ViewBindingJavaActivity
import com.jjz.frameworkdemo.ui.viewbinding.kotlin.ViewBindingKtActivity
import com.jjz.frameworkdemo.ui.viewmodel.VM2Activity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (i in 0 until binding.gridRoot.childCount) {
            var childAt = binding.gridRoot.getChildAt(i) as Button
            childAt.setOnClickListener {
                when (childAt.text.toString()) {
                    "ViewBindingKtActivity" -> startActivity(ViewBindingKtActivity::class.java)
                    "ViewBindingJavaActivity" -> startActivity(ViewBindingJavaActivity::class.java)
                    "HttpRequestActivity" -> startActivity(HttpRequestActivity::class.java)
                    "ViewModel2Activity" -> startActivity(VM2Activity::class.java)

                }
            }

        }

    }

    open fun startActivity(clazz: Class<*>) {
        val intent = Intent(baseContext!!, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}
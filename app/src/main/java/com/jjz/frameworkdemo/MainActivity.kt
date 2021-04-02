package com.jjz.frameworkdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.jjz.frameworkdemo.databinding.ActivityMainBinding
import com.jjz.frameworkdemo.ui.DBDemoActivity
import com.jjz.frameworkdemo.ui.DemoJavaActivity
import com.jjz.frameworkdemo.ui.DemoKtActivity
import com.jjz.frameworkdemo.ui.paging.RecyclerViewMoreActivity
import com.jjz.frameworkdemo.ui.viewbinding.java.ViewBindingJavaActivity
import com.jjz.frameworkdemo.ui.viewbinding.kotlin.ViewBindingKtActivity
import com.jjz.frameworkdemo.ui.viewmodel.ViewModel2Activity
import com.jjz.frameworkdemo.ui.viewmodel.ViewModelActivity
import com.jjz.frameworkdemo.ui.viewmodel.ViewModelKtActivity
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

class MainActivity : BaseVMActivity<ActivityMainBinding, HttpRequestViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 0 until binding.gridRoot.childCount) {
            var childAt = binding.gridRoot.getChildAt(i) as Button
            childAt.setOnClickListener {
                when (childAt.text.toString()) {
                    "ViewBindingKtActivity" -> startActivity(ViewBindingKtActivity::class.java)
                    "ViewBindingJavaActivity" -> startActivity(ViewBindingJavaActivity::class.java)
                    "ViewModelActivity" -> startActivity(ViewModelActivity::class.java)
                    "ViewModel2Activity" -> startActivity(ViewModel2Activity::class.java)
                    "ViewModelKtActivity" -> startActivity(ViewModelKtActivity::class.java)
                    "DemoJavaActivity" -> startActivity(DemoJavaActivity::class.java)
                    "DemoKtActivity" -> startActivity(DemoKtActivity::class.java)
                    "DBDemoActivity" -> startActivity(DBDemoActivity::class.java)
                    "RecyclerViewMoreActivity" -> startActivity(RecyclerViewMoreActivity::class.java)

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
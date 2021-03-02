package com.jjz.frameworkdemo.ui.viewbinding.java;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jjz.frameworkdemo.databinding.ActivityViewBindingJavaBinding;
import com.jjz.frameworkdemo.viewbinding.BaseBindingActivity;

public class ViewBindingJavaActivity extends BaseBindingActivity<ActivityViewBindingJavaBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.tvCenter.setText("" +
                "class ViewBindingJavaActivity : BaseBindingActivity<ActivityViewBindingJavaBinding>() {\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        binding.tvCenter=\"\"\n" +
                "    }\n" +
                "}\n\n" +
                "####################################################################################\n");
    }
}
package com.jjz.frameworkdemo.ui.viewbinding.java;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.jjz.frameworkdemo.databinding.FragmentViewBindingJavaBinding;
import com.jjz.frameworkdemo.viewbinding.BaseBindingFragment;


public class ViewBindingJavaFragment extends BaseBindingFragment<FragmentViewBindingJavaBinding> {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvCenter.setText("public class ViewBindingJavaFragment extends BaseBindingFragment<FragmentViewBindingJavaBinding> {\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {\n" +
                "        super.onViewCreated(view, savedInstanceState);\n" +
                "        \n" +
                "        binding.tvCenter.setText(\"\");\n" +
                "        \n" +
                "        \n" +
                "    }\n" +
                "}");


    }
}
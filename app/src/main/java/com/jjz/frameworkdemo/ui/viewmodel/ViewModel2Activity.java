package com.jjz.frameworkdemo.ui.viewmodel;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.jjz.frameworkdemo.databinding.ActivityViewModel2Binding;
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity;
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel;

public class ViewModel2Activity extends BaseVMActivity<ActivityViewModel2Binding,ViewModelViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.getRoot();
        viewModel.getTestLiveValue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvCenter.setText(s);
            }
        });

        viewModel .getTestLiveValue().setValue("public class ViewModel2Activity extends BaseVMActivity<ActivityViewModel2Binding,ViewModelViewModel> {\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "\n" +
                "        binding.getRoot();\n" +
                "        viewModel.getTestLiveValue().observe(this, new Observer<String>() {\n" +
                "            @Override\n" +
                "            public void onChanged(String s) {\n" +
                "                binding.tvCenter.setText(s);\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        viewModel .getTestLiveValue().setValue(\"ViewModel2ActivityViewModel2Activity\");\n" +
                "\n" +
                "    }\n" +
                "}");

    }
}
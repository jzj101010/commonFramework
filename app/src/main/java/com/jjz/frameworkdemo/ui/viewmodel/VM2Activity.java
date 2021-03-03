package com.jjz.frameworkdemo.ui.viewmodel;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.jjz.frameworkdemo.databinding.ActivityViewModel2Binding;
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity;
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel;

public class VM2Activity extends BaseVMActivity<ActivityViewModel2Binding,ViewModelViewModel> {

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

        viewModel .getTestLiveValue().setValue("ViewModel2ActivityViewModel2Activity");

    }
}
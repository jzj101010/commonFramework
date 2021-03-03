package com.jjz.frameworkdemo.ui.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.jjz.frameworkdemo.R;
import com.jjz.frameworkdemo.databinding.ActivityViewModelBinding;
import com.jjz.frameworkdemo.viewbinding.BaseBindingActivity;
import com.jjz.frameworkdemo.viewmodel.ViewModelViewModel;

public class ViewModelActivity extends BaseBindingActivity<ActivityViewModelBinding> {

    private ViewModelViewModel mViewModelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModelViewModel=new ViewModelProvider(this).get(ViewModelViewModel.class);

    }
}
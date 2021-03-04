package com.jjz.frameworkdemo.ui.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

        mViewModelViewModel.getTestLiveValue().observe(this, s -> {
            binding.tvCenter.setText(s);
        });
        mViewModelViewModel .getTestLiveValue().setValue("public class ViewModelActivity extends BaseBindingActivity<ActivityViewModelBinding> {\n" +
                "\n" +
                "    private ViewModelViewModel mViewModelViewModel;\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        mViewModelViewModel=new ViewModelProvider(this).get(ViewModelViewModel.class);\n" +
                "\n" +
                "        mViewModelViewModel.getTestLiveValue().observe(this, s -> {\n" +
                "            binding.tvCenter.setText(s);     \n" +
                "        });\n" +
                "        mViewModelViewModel .getTestLiveValue().setValue(\"\");  \n" +
                "    }\n" +
                "}");
    }
}
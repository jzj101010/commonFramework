package com.jjz.frameworkdemo.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.dylanc.viewbinding.base.ViewBindingUtil;
import com.jjz.frameworkdemo.BaseFragment;


 abstract public class BaseBindingFragment<VB extends ViewBinding> extends BaseFragment {

    public VB binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ViewBindingUtil.inflateWithGeneric(this, getLayoutInflater());
        return binding.getRoot();

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
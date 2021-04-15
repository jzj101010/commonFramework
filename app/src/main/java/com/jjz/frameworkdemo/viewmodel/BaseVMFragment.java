package com.jjz.frameworkdemo.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.jjz.frameworkdemo.viewbinding.BaseBindingFragment;

import java.lang.reflect.ParameterizedType;


abstract public class BaseVMFragment<VB extends ViewBinding, VM extends BaseViewModel> extends BaseBindingFragment<VB> {

    public VM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //通过反射机制获取泛型的类类型
        Class<VM> entityClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        viewModel = new ViewModelProvider(this).get(entityClass);
        viewModel.setViewBehaviorObserver(this, this);
        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
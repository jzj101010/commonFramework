package com.jjz.frameworkdemo.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.jjz.frameworkdemo.viewbinding.BaseBindingActivity;

import java.lang.reflect.ParameterizedType;

/**
 * @author jjz
 */
abstract public class BaseVMActivity<VB extends ViewBinding, VM extends BaseViewModel> extends BaseBindingActivity<VB> {

   public VM viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过反射机制获取泛型的类类型
        Class<VM> entityClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        viewModel = new ViewModelProvider(this).get(entityClass);
        viewModel.setViewBehaviorObserver(this,this);
    }
}

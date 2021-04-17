package com.jjz.frameworkdemo.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.dylanc.viewbinding.base.ViewBindingUtil;
import com.jjz.frameworkdemo.viewbinding.BaseBindingActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import kotlinx.coroutines.CoroutineScope;

/**
 * @author jjz
 */
abstract public class BaseVMActivity<VB extends ViewBinding, VM extends BaseViewModel> extends BaseBindingActivity<VB> {

    public VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过反射机制获取泛型的类类型
//        Class<VM> entityClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//        viewModel = new ViewModelProvider(this).get(entityClass);
        viewModel = getVMEntity();
        viewModel.setViewBehaviorObserver(this, this);
    }

    private VM getVMEntity() {
        Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            try {
                Class<VM> entityClass= (Class<VM>) actualTypeArguments[i];
                return new ViewModelProvider(this).get(entityClass);
            } catch (Exception e) {

            }
        }
        return null;
    }

}

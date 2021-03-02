package com.jjz.frameworkdemo.ui.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.jjz.frameworkdemo.ui.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author jjz
 */
abstract public class BaseBindingActivity<VB extends ViewBinding> extends BaseActivity {

    public VB binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//    利用反射，调用指定ViewBinding中的inflate方法填充视图
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //如果支持泛型
            try {
                Class clazz = (Class<VB>)((ParameterizedType) type).getActualTypeArguments()[0];
                Method method = null;
                method = clazz.getMethod("inflate", LayoutInflater.class);
                binding =(VB) method.invoke(null, getLayoutInflater()) ;
                setContentView(binding.getRoot());;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }


    }
}

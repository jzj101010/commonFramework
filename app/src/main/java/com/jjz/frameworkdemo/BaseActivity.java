package com.jjz.frameworkdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel;
import com.jjz.frameworkdemo.viewmodel.ViewBehavior;

/**
 * @author jjz
 */
abstract public  class BaseActivity extends AppCompatActivity implements ViewBehavior {
    protected String TAG = this.getClass().getSimpleName();
    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void showProgressDialog() {
        showProgressDialog("正在加载…");
    }

    public void showProgressDialog(String text) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        dialog.setMessage(text);
        dialog.show();
    }

    public void dismissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    @Override
    public void showLoadingUI(boolean isShow) {
        if(isShow){
            showProgressDialog();
        }else {
            dismissProgressDialog();
        }
    }

    @Override
    public void finishPage(@org.jetbrains.annotations.Nullable Object arg) {
            finish();
    }
}

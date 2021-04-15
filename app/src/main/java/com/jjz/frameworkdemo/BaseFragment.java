package com.jjz.frameworkdemo;

import android.app.ProgressDialog;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

import com.jjz.frameworkdemo.viewmodel.ViewBehavior;

import org.jetbrains.annotations.Nullable;

public class BaseFragment extends Fragment implements ViewBehavior {
    public String TAG = this.getClass().getSimpleName();
    private ProgressDialog dialog;


    public BaseFragment(){
        super();
    }
    public BaseFragment(@LayoutRes int contentLayoutId){
        super(contentLayoutId);
    }


    public void showProgressDialog() {
        showProgressDialog("正在加载…");
    }

    public void showProgressDialog(String text) {
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
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

    }

    @Override
    public void finishPage(@Nullable Object arg) {

    }
}

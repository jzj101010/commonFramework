package com.jjz.frameworkdemo.ui;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public BaseFragment(@LayoutRes int contentLayoutId){
        super(contentLayoutId);
    }
}

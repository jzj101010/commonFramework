package com.example.livelibrary.qiniu;

public interface OnCallBack<T> {
    void onSuccess(T data);
    void onFail(String message);
}

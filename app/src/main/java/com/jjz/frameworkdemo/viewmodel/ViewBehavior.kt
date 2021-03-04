package com.jjz.frameworkdemo.viewmodel

interface ViewBehavior {
    /**
     * 是否显示Loading视图
     */
    fun showLoadingUI(isShow: Boolean)


    /**
     * 关闭页面
     */
    fun finishPage(arg: Any?)
}
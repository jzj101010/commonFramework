package com.jjz.frameworkdemo.ui

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.jjz.frameworkdemo.databinding.ActivityPlayBinding
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayActivity :
    BaseVMActivity<ActivityPlayBinding, HttpRequestViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_play);

//        val mMediaController = MediaController(this)
//        binding.PLVideoView.setMediaController(mMediaController)

        binding.PLVideoView.setVideoPath("rtmp://pili-live-rtmp.qn.qiupindao.com/qpd-zb/newtest")
        binding.PLVideoView.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.PLVideoView.stopPlayback()
    }
}
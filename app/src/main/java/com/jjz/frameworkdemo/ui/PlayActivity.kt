package com.jjz.frameworkdemo.ui

import android.os.Bundle
import com.jjz.frameworkdemo.databinding.ActivityPlayBinding
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel

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
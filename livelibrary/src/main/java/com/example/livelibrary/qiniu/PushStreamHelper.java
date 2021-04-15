package com.example.livelibrary.qiniu;

import android.content.Context;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.livelibrary.R;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.AudioSourceCallback;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.PLAuthenticationResultCallback;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * 作为推流的帮助类
 * 推流SDK设置参考：https://developer.qiniu.com/pili/sdk/3719/PLDroidMediaStreaming-function-using
 * {@link #RTMP_PUBLISH_DOMAIN}和{@link #RTMP_PLAY_DOMAIN}需要替换为自己的域名，{@link #HUB}为直播空间名
 */
public class PushStreamHelper implements StreamingStateChangedListener, StreamingSessionListener, StreamStatusCallback, AudioSourceCallback/*, PLAuthenticationResultCallback*/ {
    private static final String GENERATE_STREAM_TEXT = "https://api-demo.qnsdk.com/v1/live/stream/";
    private static final String TAG = "PushStreamHelper";
    //private static final String DEFAULT_PUBLISH_URL = "rtmp://pili-publish.qnsdk.com/sdk-live/defualt?e=1587644086&token=QxZugR8TAhI38AiJ_cptTl3RbzLyca3t-AAiH-Hh:nJeMAL3vKgA0sJ1pIfHkxZ9mn1o=";
    private static final String DEFAULT_PUBLISH_URL = "rtmp://pili-publish.qnsdk.com/sdk-live/defualt";
    private static final String PUBLISH_HOST = "rtmp://";
    private static final String RTMP_PUBLISH_DOMAIN = "";
    private static final String RTMP_PLAY_DOMAIN = "";
    private static final String HUB = "";
    private static PushStreamHelper instance;

    private StreamingProfile mProfile;
    private MediaStreamingManager mMediaStreamingManager;
    private CameraStreamingSetting cameraStreamingSetting;
    private String publishUrl = DEFAULT_PUBLISH_URL;
    private EncodingConfig config;

    private OnPushStageChange stageChange;

    private PushStreamHelper(){}

    public static PushStreamHelper getInstance() {
        if(instance == null) {
            synchronized (PushStreamHelper.class) {
                if(instance == null) {
                    instance = new PushStreamHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 根据推流官方文档，可以在application的onCreate()中进行初始化
     * @param context
     */
    public void init(Context context) {
        StreamingEnv.init(context.getApplicationContext());
    }

    /**
     * 获取推流地址，此处为演示，为未校验鉴权
     * 实际开发中，应通过app server获取推流地址，并进行鉴权避免直播内容的盗用。
     * @param userId
     * @param callBack
     */
    public void getPublishUrl(String userId, OnCallBack<String> callBack) {
        if(callBack != null) {
            callBack.onSuccess(PUBLISH_HOST + RTMP_PUBLISH_DOMAIN + "/" + HUB + "/" + userId);
        }
//        new Thread(){
//            public void run(){
//                callBack.onSuccess(Util.syncRequest(GENERATE_STREAM_TEXT + userId));
//            }
//        }.start();
    }

    /**
     * 获取拉流地址，此处为演示，为未校验鉴权
     * 实际开发中，应通过app server获取播放地址。
     * @param userId
     * @param callBack
     */
    public void getPlayUrl(String userId, OnCallBack<String> callBack) {
        if(callBack != null) {
            callBack.onSuccess(PUBLISH_HOST + RTMP_PLAY_DOMAIN + "/" + HUB + "/" + userId);
        }
    }

    /**
     * 初始化-只使用音频推流
     * @param context
     */
    public void initPublishOnlyAudio(Context context) {
        if(this.config == null) {
            this.config = new EncodingConfig();
            this.config.mCodecType= AVCodecType.SW_AUDIO_CODEC;
            config.mIsPictureStreamingEnabled=false;
        }
        try {
//            setCameraStreamingSetting();
            initProfile(this.publishUrl);
            setMediaStreamManager(context);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void initPublishOnlyAudio(Context context, String publishUrl) {
        this.publishUrl = publishUrl;
        if(TextUtils.isEmpty(this.publishUrl)) {
            this.publishUrl = DEFAULT_PUBLISH_URL;
        }
        initPublishOnlyAudio(context);
    }
    public void initPublishOnlyAudio(Context context, String publishUrl, EncodingConfig config) {
        this.config = config;
        initPublishOnlyAudio(context,publishUrl);
    }

    /**
     * 初始化-视频音频推流
     * @param surfaceView
     */
    public void initPublishVideo(GLSurfaceView surfaceView) {
        if(this.config == null) {
            this.config = new EncodingConfig();
        }
        try {
            setCameraStreamingSetting();
            initProfile(this.publishUrl);
            setMediaStreamManager(surfaceView);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void initPublishVideo(GLSurfaceView surfaceView, String publishUrl) {
        this.publishUrl = publishUrl;
        if(TextUtils.isEmpty(this.publishUrl)) {
            this.publishUrl = DEFAULT_PUBLISH_URL;
        }
        initPublishVideo( surfaceView);
    }
    public void initPublishVideo(GLSurfaceView surfaceView, String publishUrl, EncodingConfig config) {
        this.config = config;
        initPublishVideo( surfaceView,publishUrl);
    }

    /**
     * 设置推流地址
     * @param publishUrl
     */
    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
        if(!TextUtils.isEmpty(this.publishUrl)) {
            if(mProfile == null || mMediaStreamingManager == null) {
                return;
            }
            try {
                initProfile(this.publishUrl);
                mMediaStreamingManager.setStreamingProfile(mProfile);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private void initProfile(String publishUrl) throws URISyntaxException {
        //encoding setting
        Log.e(TAG, "initProfile");
        mProfile = new StreamingProfile();
        if(config.mIsPictureStreamingEnabled) {
            if(config.mPictureStreamingFilePath == null) {
                mProfile.setPictureStreamingResourceId(R.drawable.pause_publish);
            }else {
                mProfile.setPictureStreamingFilePath(config.mPictureStreamingFilePath);
            }
        }
        //开启QUIC推流
        //QUIC 是基于 UDP 开发的可靠传输协议，在弱网下拥有更好的推流效果，相比于 TCP 拥有更低的延迟，可抵抗更高的丢包率。
        mProfile.setQuicEnable(true);
        mProfile.setVideoQuality(config.mVideoQualityPreset)              // 设置视频质量
                .setAudioQuality(config.mAudioQualityPreset)            // 设置音频质量
                .setEncodingSizeLevel(config.mVideoSizePreset)
                .setEncoderRCMode(config.mEncoderRCMode) // 软编的EncoderRCModes,默认为EncoderRCModes.QUALITY_PRIORITY
                .setPublishUrl(publishUrl);                               // 设置推流地址
    }

    /**
     * 设置CameraStreamingSetting
     * 内置美颜流程的开启通过 CameraStreamingSetting#setBuiltInFaceBeautyEnabled(boolean eanble) 进行，
     * 注意，若希望自定义美颜，需要 disable 该接口，否则行为未知。
     * 在初始化 CameraStreamingSetting 的时候，可以初始化对应的美颜参数：
     * <pre class="prettyprint">
     *     // FaceBeautySetting 中的参数依次为：beautyLevel，whiten，redden，即磨皮程度、美白程度以及红润程度，取值范围为[0.0f, 1.0f]
     *      Setting.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f))
     *             .setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY)
     * </pre>
     * @return
     */
    private void setCameraStreamingSetting() {
        Log.e(TAG, "setCameraStreamingSetting");
        //preview setting
        cameraStreamingSetting = new CameraStreamingSetting();
        cameraStreamingSetting.setCameraId(config.mFrontFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK) //默认后置摄像头，设置后置摄像头，CAMERA_FACING_FRONT为前置
                .setContinuousFocusModeEnabled(config.mContinuousAutoFocus)    //自动对焦，默认开启
                .setFocusMode(config.mFocusMode)   //设置对焦模式，默认是VIDEO，可选FOCUS_MODE_CONTINUOUS_PICTURE
                // 及FOCUS_MODE_AUTO, PICTURE 对焦会比 VIDEO 更加频繁，
                // 功耗会更高，建议使用 VIDEO
                //.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f)) //初始化美颜参数
                //.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY)
                .setCameraPrvSizeLevel(config.mSizeLevel)
                .setCameraPrvSizeRatio(config.mSizeRatio)
                .setFrontCameraPreviewMirror(config.mPreviewMirror)
                .setFrontCameraMirror(config.mEncodingMirror)
                .setRecordingHint(false)
                .setResetTouchFocusDelayInMs(3000);
    }

    /**
     * 设置MediaStreamManager, 核心类
     * 所有麦克风相关的配置，都在{@link com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting} 类中进行。
     * 例如，希望增加蓝牙麦克风的支持：
     * <pre class="prettyprint">
     *     mMicrophoneStreamingSetting.setBluetoothSCOEnabled(true);
     *     mMediaStreamingManager.prepare(setting, mMicrophoneStreamingSetting, mProfile);
     * </pre>
     * @param surfaceView
     */
    private void setMediaStreamManager(GLSurfaceView surfaceView) {
        surfaceView.setZOrderOnTop(true);
        surfaceView.setZOrderMediaOverlay(true);
        //streaming engine init and setListener
        mMediaStreamingManager = new MediaStreamingManager(surfaceView.getContext(), surfaceView, config.mCodecType);  // soft codec
        mMediaStreamingManager.prepare(cameraStreamingSetting, mProfile);
        Log.e(TAG, "setMediaStreamManager");
        mMediaStreamingManager.setAutoRefreshOverlay(true);
        mMediaStreamingManager.setStreamingStateListener(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        //StreamingEnv.checkAuthentication(this);
        Log.e(TAG, "setMediaStreamManager end");
    }
    /**
     * 只设置音频 不使用视频
     * 设置MediaStreamManager, 核心类
     * 所有麦克风相关的配置，都在{@link com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting} 类中进行。
     * 例如，希望增加蓝牙麦克风的支持：
     * <pre class="prettyprint">
     *     mMicrophoneStreamingSetting.setBluetoothSCOEnabled(true);
     *     mMediaStreamingManager.prepare(setting, mMicrophoneStreamingSetting, mProfile);
     * </pre>
     * @param context
     */
    private void setMediaStreamManager(Context context) {
        //streaming engine init and setListener
        mMediaStreamingManager = new MediaStreamingManager(context,config.mCodecType);  // soft codec
        mMediaStreamingManager.prepare(mProfile);
        Log.e(TAG, "setMediaStreamManager");
        mMediaStreamingManager.setAutoRefreshOverlay(true);
        mMediaStreamingManager.setStreamingStateListener(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        //StreamingEnv.checkAuthentication(this);
        Log.e(TAG, "setMediaStreamManager end");
    }
    /**
     * 退出 MediaStreamingManager，该操作会主动断开当前的流链接，并关闭 Camera 和释放相应的资源。
     */
    public void pause() {
        if(mMediaStreamingManager != null) {
            Log.e(TAG, "media pause");
            mMediaStreamingManager.pause();
        }
    }

    /**
     * 进行 Camera 的打开操作，当成功打开后，会返回 STATE.READY 消息，用户可以在接受到 STATE.READY 之后，安全地进行推流操作
     */
    public void resume() {
        if(mMediaStreamingManager != null) {
            Log.e(TAG, "media resume");
            mMediaStreamingManager.resume();
        }
    }

    /**
     * 释放不紧要资源。
     */
    public void destroy() {
        this.publishUrl = DEFAULT_PUBLISH_URL;
        if(mMediaStreamingManager != null) {
            Log.e(TAG, "media destroy");
            mMediaStreamingManager.destroy();
        }
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        if(mMediaStreamingManager != null) {
            Log.e(TAG, "media switchCamera");
            mMediaStreamingManager.switchCamera();
        }
    }

    /**
     * 设置是否禁音推流
     * @param isMute
     */
    public void mute(boolean isMute) {
        if(mMediaStreamingManager != null) {
            mMediaStreamingManager.mute(isMute);
        }
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        Log.e(TAG, "streamingState = " + streamingState + "extra = " + extra);
        switch (streamingState) {
            case PREPARING:
                Log.e(TAG, "PREPARING");
                break;
            case READY:
                Log.e(TAG, "READY");
                // start streaming when READY
                startStreamingInternal();
                break;
            case CONNECTING:
                Log.e(TAG, "连接中");
                break;
            case STREAMING:
                Log.e(TAG, "推流中");
                // The av packet had been sent.
                break;
            case SHUTDOWN:
                Log.e(TAG, "直播中断");
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                Log.e(TAG, "网络连接失败");
                if(stageChange != null) {
                    stageChange.ioError();
                }
                break;
            case OPEN_CAMERA_FAIL:
                Log.e(TAG, "摄像头打开失败");
                // Failed to open camera.
                if(stageChange != null) {
                    stageChange.openCameraFail();
                }
                break;
            case DISCONNECTED:
                Log.e(TAG, "已经断开连接");
                // The socket is broken while streaming
                if(stageChange != null) {
                    stageChange.disconnected();
                }
                break;
            case TORCH_INFO:
                Log.e(TAG, "开启闪光灯");
                break;
            case CAMERA_SWITCHED:
                Log.e(TAG, "切换摄像头 "+extra);
                break;
        }
    }

    @Override
    public boolean onRecordAudioFailedHandled(int i) {
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int i) {
        startStreamingInternal();
        return true;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        return null;
    }

    @Override
    public int onPreviewFpsSelected(List<int[]> list) {
        return -1;
    }

    @Override
    public void notifyStreamStatusChanged(StreamingProfile.StreamStatus streamStatus) {

    }

    @Override
    public void onAudioSourceAvailable(ByteBuffer byteBuffer, int i, long l, boolean b) {

    }

    public void startStreamingInternal() {
        new Thread(()-> {
            if (mMediaStreamingManager != null) {
                mMediaStreamingManager.startStreaming();
            }
        }).start();
    }

    /*@Override
    public void onAuthorizationResult(int result) {
        Log.e("TAG", "result = "+result);
    }*/

    public void setOnPushStageChange(OnPushStageChange change) {
        this.stageChange = change;
    }

    public interface OnPushStageChange {
        /**
         * 网络连接失败
         */
        void ioError();

        /**
         * 摄像头打开失败
         */
        void openCameraFail();

        /**
         * The socket is broken while streaming
         */
        void disconnected();
    }
}

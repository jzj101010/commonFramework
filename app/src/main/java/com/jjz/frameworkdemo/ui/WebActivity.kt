package com.jjz.frameworkdemo.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.webkit.*
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ToastUtils
import com.jjz.frameworkdemo.R
import com.jjz.frameworkdemo.databinding.ActivityWebBinding
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel
import java.util.HashMap

class WebActivity :
    BaseVMActivity<ActivityWebBinding, HttpRequestViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonInitWebView(this,binding.webView,"")
    }

    fun commonInitWebView(context: Context, webView: WebView, url: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.pluginState = WebSettings.PluginState.ON
        webView.settings.loadsImagesAutomatically = true
        webView.settings.databaseEnabled = true
        webView.settings.userAgentString = "native-app"
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view?.loadUrl(url?:"");
                return true;
//                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
//                    super.onReceivedSslError(view, handler, error)
            }


        }

        val content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>video.js播放rtmp流</title>\n" +
                "    <!--引入播放器样式-->\n" +
                "    <link href=\"http://vjs.zencdn.net/5.19/video-js.min.css\" rel=\"stylesheet\">\n" +
                "    <!--引入播放器js-->\n" +
                "    <script src=\"http://vjs.zencdn.net/5.19/video.min.js\"></script>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/videojs-flash@2/dist/videojs-flash.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<!--vjs-big-play-centered 播放按钮居中-->\n" +
                "<!--poster默认的显示界面，就是还没点播放，给你显示的界面-->\n" +
                "<!--controls 规定浏览器应该为视频提供播放控件-->\n" +
                "<!--preload=\"auto\" 是否提前加载-->\n" +
                "<!--autoplay 自动播放-->\n" +
                "<!--loop=true 自动循环-->\n" +
                "<!--data-setup='{\"example_option\":true}' 可以把一些属性写到这个里面来，如data-setup={\"autoplay\":true}-->\n" +
                "<video id=\"my-player\"\n" +
                "       class=\"video-js vjs-default-skin vjs-big-play-centered\" controls\n" +
                "       preload=\"auto\" autoplay=\"autoplay\"\n" +
                "       poster=\"images/logo.png\" width=\"500\" height=\"400\"\n" +
                "       data-setup='{}'>\n" +
                "    <!--src: 规定媒体文件的 URL  type:规定媒体资源的类型-->\n" +
                "    <source src='rtmp://ns8.indexforce.com/home/mystream' type='rtmp/flv' />\n" +
                "</video>\n" +
                "<script type=\"text/javascript\">\n" +
                "\t\t    // 设置flash路径,用于在videojs发现浏览器不支持HTML5播放器的时候自动唤起flash播放器\n" +
                "\t\t    videojs.options.flash.swf = 'https://cdn.bootcss.com/videojs-swf/5.4.1/video-js.swf';\n" +
                "\t\t  \t//my-player为页面video元素的id\n" +
                "\t\t    var player = videojs('my-player');\n" +
                "\t\t  \t//播放\n" +
                "\t\t    player.play();\n" +
                "\t\t\t//    1. 播放   player.play()\n" +
                "\t\t\t//    2. 停止   player.pause()\n" +
                "\t\t\t//    3. 暂停   player.pause()\n" +
                "\t\t</script>\n" +
                "</body>\n" +
                "</html>"

        var content1="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                " \n" +
                "    <title>HTML5 直播</title>\n" +
                "    <link href=\"http://vjs.zencdn.net/5.19/video-js.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"http://vjs.zencdn.net/5.19/video.min.js\"></script>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/videojs-flash@2/dist/videojs-flash.min.js\"></script>\n" +
                " \n" +
                "</head>\n" +
                "<body>\n" +
                " \n" +
                "<video id=\"myvideo\" class=\"video-js vjs-default-skin\" controls preload=\"auto\"\n" +
                "       width=\"1280\" height=\"720\" poster=\"http://vjs.zencdn.net/v/oceans.png\" data-setup=\"{}\">\n" +
                "    <source src=\"rtmp://ns8.indexforce.com/home/mystream\" type=\"rtmp/flv\">//src里面填的是rtmp的地址rtmp://192.168.0.221/live跟密钥1234\n" +
                " \n" +
                "    <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that\n" +
                "        <a href=\"http://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a>\n" +
                "    </p>\n" +
                "</video>\n" +
                " \n" +
                "</body>\n" +
                " \n" +
                "</html>"

//        webView.loadUrl("http://www.baidu.com")
        webView.loadData(content1, "text/html", "utf-8");

//        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
//        webView.addJavascriptInterface(JavaInterface(context, webView), "app")
    }
}
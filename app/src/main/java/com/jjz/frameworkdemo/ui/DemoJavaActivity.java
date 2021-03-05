package com.jjz.frameworkdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.jjz.frameworkdemo.databinding.ActivityDemoJavaBinding;
import com.jjz.frameworkdemo.viewmodel.BaseVMActivity;
import com.jjz.frameworkdemo.viewmodel.HttpRequestViewModel;

/**
 * @author Administrator
 */
public class DemoJavaActivity extends BaseVMActivity<ActivityDemoJavaBinding, HttpRequestViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 业务逻辑  不需要手动初始化ViewModel 和 Viewbinding   kotlin页面可使用
         */
        viewModel.getTestLiveValue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String it) {
                binding.tvCenter.setText(it);
            }
        });
        binding.tvCenter.setText("点击请求");
        binding.tvCenter.setOnClickListener(v -> viewModel.getHttpListData());

        binding.tvCode.setText("class HttpRequestActivity : BaseActivity() {\n" +
                "\n" +
                "    private  val binding: ActivityHttpRequestBinding by inflate()\n" +
                "    private  lateinit var  viewModel: HttpRequestViewModel\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        viewModel = ViewModelProvider(this).get(HttpRequestViewModel::class.java)\n" +
                "        viewModel.testLiveValue.observe(this){\n" +
                "            binding.tvCenter.text=it\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        binding.tvCenter.text=\"点击请求\"\n" +
                "        binding.tvCenter.setOnClickListener {\n" +
                "            viewModel.getListData()\n" +
                "        }\n" +
                "\n" +
                "        binding.tvCode.text=\"\"\n" +
                "\n" +
                "    }\n" +
                "}\n" +
                "#################################################\n\n");
    }
}
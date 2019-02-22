package one.example.com.mysample.main.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import one.example.com.mysample.R;
import one.example.com.mysample.databinding.ActivityMainBinding;
import one.example.com.mysample.main.proje.HeadBean;
import one.example.com.mysample.main.proje.HeadVisibilityBean;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.ToastUtiles;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHeadBean(new HeadBean("", "全球电影榜", ""));
        binding.setVisibleHeadBean(new HeadVisibilityBean(false,false,false,true,false));
        binding.mainRefreshLayout.setOnLoadMoreListener(loadMoreListener);
        binding.mainRefreshLayout.setOnRefreshListener(refreshListener);
//        binding.mainRefreshLayout.autoRefresh();//刷新并且启动刷新动画
//        binding.mainRefreshLayout.autoLoadMore();//刷新并且启动刷新动画
    }


    OnLoadMoreListener loadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            Logs.eprintln(TAG, "onLoadMore");
        }
    };

    OnRefreshListener refreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            Logs.eprintln(TAG, "onRefresh");
        }
    };


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            }
            else {
                ToastUtiles.toastShort(this,
                        this.getApplicationContext().getResources().getString(R.string.agineBacktoast));
                firstTime = System.currentTimeMillis();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

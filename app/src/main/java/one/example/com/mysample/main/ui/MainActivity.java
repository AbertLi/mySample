package one.example.com.mysample.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import one.example.com.mysample.R;
import one.example.com.mysample.databinding.ActivityMainBinding;
import one.example.com.mysample.main.webservice.bean.TopMovieListInfoEntity;
import one.example.com.mysample.main.proje.HeadBean;
import one.example.com.mysample.main.proje.HeadVisibilityBean;
import one.example.com.mysample.main.webservice.SendMessageManager;
import one.example.com.mysample.utile.EvenType;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.MyBusEven;
import one.example.com.mysample.utile.ToastUtiles;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHeadBean(new HeadBean("", "全球电影榜", ""));
        binding.setVisibleHeadBean(new HeadVisibilityBean(false, false, false, true, false));
        binding.mainRefreshLayout.setOnLoadMoreListener(loadMoreListener);
        binding.mainRefreshLayout.setOnRefreshListener(refreshListener);
        encapRequest();
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


    /**
     * 封装使用
     */
    private void encapRequest() {
//        SendMessageManager.getInstance().getPostInfo("yuantong", "11111111111");
        SendMessageManager.getInstance().getMoveTop(0, 3);
        //可以用EventBus框架替换
        MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).observe(MainActivity.class,
                new MyBusEven.ICallBack() {
                    @Override
                    public void back(Object o) {
                        ToastUtiles.toastShort(MainActivity.this, ((TopMovieListInfoEntity) o).toString());
                        Logs.eprintln("MyBusEven1");
                    }
                });

        MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).observe(TopMovieListInfoEntity.class, o -> {
            Logs.eprintln("MyBusEven2");
        });
    }


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



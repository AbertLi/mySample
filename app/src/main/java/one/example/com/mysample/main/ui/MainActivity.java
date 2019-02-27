package one.example.com.mysample.main.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import one.example.com.mysample.R;
import one.example.com.mysample.databinding.ActivityMainBinding;
import one.example.com.mysample.main.proje.HeadBean;
import one.example.com.mysample.main.proje.HeadVisibilityBean;
import one.example.com.mysample.main.webservice.Constant;
import one.example.com.mysample.main.webservice.PostInfo;
import one.example.com.mysample.main.webservice.RetrofitService;
import one.example.com.mysample.main.webservice.RetrofitUtils;
import one.example.com.mysample.main.webservice.SendMessageManager;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.ToastUtiles;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
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
//        binding.mainRefreshLayout.autoRefresh();//刷新并且启动刷新动画
//        binding.mainRefreshLayout.autoLoadMore();//刷新并且启动刷新动画
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 封装使用
     */
    private void encapRequest() {
        SendMessageManager.getInstance().getPostInfo("yuantong", "11111111111");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostInfo postInfo) {
        Log.i("接收消息：", postInfo.toString());
        Toast.makeText(MainActivity.this, postInfo.toString(), Toast.LENGTH_SHORT).show();
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



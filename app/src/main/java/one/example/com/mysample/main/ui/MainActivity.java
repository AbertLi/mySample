package one.example.com.mysample.main.ui;

import android.arch.lifecycle.ViewModelProviders;
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
import one.example.com.mysample.main.viewmodel.MoveViewModel;
import one.example.com.mysample.main.proje.HeadBean;
import one.example.com.mysample.main.proje.HeadVisibilityBean;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.ToastUtiles;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MoveViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHeadBean(new HeadBean("", "视频段子", ""));
        binding.setVisibleHeadBean(new HeadVisibilityBean(false, false, false, true, false));
        binding.mainRefreshLayout.setOnLoadMoreListener(loadMoreListener);
        binding.mainRefreshLayout.setOnRefreshListener(refreshListener);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        viewModel.requestMoveData(1, 10);
        viewModel.getSubjectData().observe(this, liveDataBean -> {
            Logs.eprintln(TAG, "subscribeUi  onChanged  liveDataBean==null " + (liveDataBean == null));
            if (isRefresh) {
                if (liveDataBean != null) {
                    Logs.eprintln(TAG, "subscribeUi  onChanged=" + liveDataBean);
                    if (liveDataBean.isSuc()) {
                        binding.mainRefreshLayout.finishRefresh(true);
                        ToastUtiles.toastShort(this,
                                this.getApplicationContext().getResources().getString(R.string.refresh_suc));
                    }
                    else {
                        binding.mainRefreshLayout.finishRefresh(false);
                        ToastUtiles.toastShort(this,
                                this.getApplicationContext().getResources().getString(R.string.refresh_fail));
                    }
                }
                isRefresh = false;
            }
            binding.executePendingBindings();
        });
    }


    OnLoadMoreListener loadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            Logs.eprintln(TAG, "onLoadMore");
//            binding.mainRefreshLayout.finishLoadMore();
        }
    };

    boolean isRefresh;


    OnRefreshListener refreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            isRefresh = true;
            viewModel.requestNet(1, 10);
            Logs.eprintln(TAG, "onRefresh");
            binding.mainRefreshLayout.finishRefresh(true);
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
/**
 * 封装使用
 */
//    private void encapRequest() {
//        SendMessageManager.getInstance().getMoveTop(0, 3);
//        //可以用EventBus框架替换
//        MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).observe(MainActivity.class,
//                new MyBusEven.ICallBack() {
//                    @Override
//                    public void back(Object o) {
//                        ToastUtiles.toastShort(MainActivity.this, ((JokeInfoEntity) o).toString());
//                        Logs.eprintln("MyBusEven1");
//                    }
//                });
//
//        MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).observe(JokeInfoEntity.class, o -> {
//            Logs.eprintln("MyBusEven2");
//        });
//    }
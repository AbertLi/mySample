package one.example.com.mysample.main.webservice;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import one.example.com.mysample.utile.Logs;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http通道
 * Created by yangle on 2017/6/19.
 */

public class HttpChannel {

    private static HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static HttpChannel getInstance() {
        return httpChannel == null ? httpChannel = new HttpChannel() : httpChannel;
    }

    private HttpChannel() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL2)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(RetrofitUtils.getOkHttpClient()) // 打印请求参数
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }


    /**
     * 发送消息
     *
     * @param observable Observable<? extends BaseBean>
     * @param urlOrigin  请求地址
     */
    public void sendMessage(Observable<? extends BaseBean> observable, final String urlOrigin) {
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<BaseBean>() {
                      @Override
                      public void onSubscribe(@NonNull Disposable d) {
                          Logs.eprintln("HttpChannel", "onSubscribe = " + d.toString());
                      }

                      @Override
                      public void onNext(@NonNull BaseBean baseBean) {
                          ReceiveMessageManager.getInstance().dispatchMessage(baseBean, urlOrigin,null);
                      }

                      @Override
                      public void onError(@NonNull Throwable e) {
                          Logs.eprintln("HttpChannel", "onError = " + e.toString());
                          ReceiveMessageManager.getInstance().dispatchMessage(null, urlOrigin,
                                  e == null ? "failMsg=null" : e.toString());
                      }

                      @Override
                      public void onComplete() {
                          Logs.eprintln("HttpChannel", "onComplete");
                      }
                  });
    }


    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
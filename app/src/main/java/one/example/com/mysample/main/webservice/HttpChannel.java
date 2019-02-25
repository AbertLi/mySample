package one.example.com.mysample.main.webservice;

import android.util.Log;

import io.reactivex.schedulers.Schedulers;
import one.example.com.mysample.main.webservice.fz.RetrofitUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpChannel {

    private static HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static HttpChannel getInstance() {
        return httpChannel == null ? httpChannel = new HttpChannel() : httpChannel;
    }

    private HttpChannel() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava
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
                      public void onCompleted() {

                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onNext(BaseBean baseBean) {
                          Log.i("http返回：", baseBean.toString() + "");
                          ReceiveMessageManager.getInstance().dispatchMessage(baseBean, urlOrigin);
                      }
                  });
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
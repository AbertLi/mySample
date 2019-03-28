package one.example.com.mysample.main.webservice;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Retrofit工具
 * Created by yangle on 2017/6/20.
 */

public class RetrofitUtils {

    /**
     * 获取OkHttpClient
     * 用于打印请求参数
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient() {
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("http请求参数：", message);
            }
        });
        loggingInterceptor.setLevel(level);
        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //https://blog.csdn.net/rainbow702/article/details/53544671
        httpClientBuilder.connectTimeout(1000, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(1000, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(1000, TimeUnit.SECONDS);
        // OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
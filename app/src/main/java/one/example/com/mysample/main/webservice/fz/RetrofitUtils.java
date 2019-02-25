package one.example.com.mysample.main.webservice.fz;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private String baseURL = "https://api.douban.com/v2/movie/top250";
    private Retrofit retrofit;
    private static ApiService apiService;
    public Handler mHandler = new Handler(Looper.getMainLooper());

    private static final class Holder {
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    public void initRxRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitUtils getInstance() {
        return Holder.INSTANCE;
    }

    public static ApiService Api() {
        if (apiService == null)
            throw new IllegalStateException("需要APPLICATION种初始化");
        return apiService;
    }

    public <T> void requestData(Call<T> call, final WaterDropInterdace<T> waterDropInterdace) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(final Call<T> call, final Response<T> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        waterDropInterdace.onSuccess(response.body(), response.message(), response.code());
                    }
                });
            }

            @Override
            public void onFailure(final Call<T> call, final Throwable t) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        waterDropInterdace.onFailure(call, t);
                    }
                });
            }
        });
    }
}
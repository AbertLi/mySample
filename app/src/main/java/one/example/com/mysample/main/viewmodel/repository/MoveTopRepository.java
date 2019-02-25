package one.example.com.mysample.main.viewmodel.repository;

import android.util.Log;

import one.example.com.mysample.main.webservice.PostInfo;
import one.example.com.mysample.main.webservice.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoveTopRepository {

    public void getTOP() {
        // 网络请求
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<PostInfo> call = service.getPostInfo("yuantong", "11111111111");
        call.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                Log.i("http返回：", response.body().toString() + "");
            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {

            }
        });
    }
}



package one.example.com.mysample.main.webservice.fz;

import retrofit2.Call;

interface WaterDropInterdace<T> {

    void onSuccess(T t, String msg, long code);

    void onFailure(Call call, Throwable throwable);

}
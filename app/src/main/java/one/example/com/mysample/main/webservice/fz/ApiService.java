package one.example.com.mysample.main.webservice.fz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiService {
    @GET("query")
    Call<TopicBean> getTopicList(@Query("app") String app, @Query("action") String action, @Query("shuidishichuan")String shuidishichuan, @Query("page") int page) {

        return null;
    }
}

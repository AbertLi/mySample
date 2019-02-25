package one.example.com.mysample.main.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 定义请求参数接口
 *
 * https://www.jianshu.com/p/04ca8dd9471e
 */
public interface RetrofitService {

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return Call<PostInfo>
     */
    @GET("query")
    Call<PostInfo> getPostInfo(@Query("type") String type, @Query("postid") String postid);
}


package one.example.com.mysample.main.webservice;

import com.google.gson.JsonObject;

import one.example.com.mysample.main.db.entity.TopMovieListInfoEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * 请求参数接口
 * Created by yangle on 2017/6/19.
 */

public interface RetrofitService {

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return Call<PostInfo>
     *     转成Json对象
     *     https://www.jianshu.com/p/da561ce76850
     */
//    @GET(Constant.UrlOrigin.get_post_info)
//    Call<JsonObject> getPostInfo(@Query("type") String type, @Query("postid") String postid);

    /**
     * 获取快递信息
     * Rx方式
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return Observable<PostInfo>
     */
    @GET(Constant.UrlOrigin.get_post_info)
    Observable<PostInfo> getPostInfoRx(@Query("type") String type, @Query("postid") String postid);


    @GET(Constant.UrlOrigin.get_move_post_info)
    Observable<TopMovieListInfoEntity> getMoveTopRx(@Query("start") int start, @Query("count") int postid);
}
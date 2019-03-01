package one.example.com.mysample.main.webservice;
import io.reactivex.Observable;
import one.example.com.mysample.main.db.entity.TopMovieListInfoEntity;

/**
 * 消息发送管理
 * Created by yangle on 2017/6/19.
 * 该代码参考以下博客
 * https://github.com/alidili/Demos/tree/master/RetrofitDemo
 */

public class SendMessageManager {
    private HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static SendMessageManager getInstance() {
        return SingletonHolder.manager;
    }

    private static class SingletonHolder {
        private static SendMessageManager manager = new SendMessageManager();
    }

    private SendMessageManager() {
        httpChannel = HttpChannel.getInstance();
        retrofitService = httpChannel.getRetrofitService();
    }

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     */
    public void getPostInfo(String type, String postid) {
        Observable<PostInfo> observable = retrofitService.getPostInfoRx(type, postid);
        httpChannel.sendMessage(observable, Constant.UrlOrigin.get_post_info);
    }


    /**
     * 获取电影list
     *
     * @param start   快递类型
     * @param con 快递单号
     */
    public void getMoveTop(int start, int con) {
        Observable<TopMovieListInfoEntity> observable = retrofitService.getMoveTopRx(start, con);
        httpChannel.sendMessage(observable, Constant.UrlOrigin.get_move_post_info);
    }
}
package one.example.com.mysample.main.webservice;

import io.reactivex.Observable;

public class SendMessageManager {

    private static SendMessageManager manager;
    private HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static SendMessageManager getInstance() {
        return manager == null ? manager = new SendMessageManager() : manager;
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
}
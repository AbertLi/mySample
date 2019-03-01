package one.example.com.mysample.main.webservice;


/**
 * 常量
 * Created by yangle on 2017/6/19.
 */

public class Constant {

    /**
     * 服务器地址
     */

    //  http://www.kuaidi100.com/query?type=11111111&postid=aljfkd
    public static final String SERVER_URL = "http://www.kuaidi100.com/";
    public static final String SERVER_URL2 = "https://api.douban.com/v2/movie/";

    /**
     * 接口请求地址
     */
    public static class UrlOrigin {
        /**
         * 获取快递信息
         */
        public static final String get_post_info = "query";


        public static final String get_move_post_info = "top250";
    }
}
package one.example.com.mysample.main.webservice;


/**
 * 常量
 * Created by yangle on 2017/6/19.
 */

public class Constant {

    /**
     * 服务器地址
     */

    //  http://api.apiopen.top/getJoke?page=1&count=10&type=video
    public static final String SERVER_URL2 = "https://api.apiopen.top/";

    /**
     * 接口请求地址
     */
    public static class UrlOrigin {
        /**
         * 获取快递信息
         */
        public static final String get_post_info = "query";


        public static final String get_joke_post_info = "getJoke";
    }
}
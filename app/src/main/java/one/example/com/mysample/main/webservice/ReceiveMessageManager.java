package one.example.com.mysample.main.webservice;

import org.greenrobot.eventbus.EventBus;

import one.example.com.mysample.main.webservice.bean.JokeInfoEntity;
import one.example.com.mysample.utile.EvenType;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.MyBusEven;

/**
 * 消息接收管理
 * Created by yangle on 2017/6/19.
 */

public class ReceiveMessageManager {
    public static ReceiveMessageManager getInstance() {
        return SingletonHolder.manager;
    }

    private static class SingletonHolder {
        private static ReceiveMessageManager manager = new ReceiveMessageManager();
    }

    private ReceiveMessageManager() {
    }

    /**
     * 分发消息
     *
     * @param baseBean  Bean基类
     * @param urlOrigin 请求地址
     */
    public void dispatchMessage(BaseBean baseBean, String urlOrigin, String failMsg) {
        switch (urlOrigin) {
            case Constant.UrlOrigin.get_joke_post_info:
                if(baseBean!=null)
                Logs.eprintln("ReceiveMessageManager", "" + ((JokeInfoEntity) baseBean).toString());
//                JokeInfoEntity postInfo1 = (JokeInfoEntity) baseBean;
//                EventBus.getDefault().post(postInfo1);
                if (baseBean == null && failMsg != null) {
                    MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).postFail(failMsg);
                }
                else {
                    MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).postValue(baseBean);
                }
                break;
            case Constant.UrlOrigin.get_post_info:
                PostInfo postInfo = (PostInfo) baseBean;
                EventBus.getDefault().post(postInfo);
                break;

            default:
                break;
        }
    }


//    //JsonObject
//    public void dispatchMessage2(JsonObject baseBean, String urlOrigin) {
//        switch (urlOrigin) {
//            case Constant.UrlOrigin.get_joke_post_info:
//                Logs.eprintln("ReceiveMessageManager", "" + baseBean.toString());
//                MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).postValue(baseBean);
//
////                EventBus.getDefault().post(baseBean);
//                break;
//            default:
//                break;
//        }
//    }
}

package one.example.com.mysample.utile;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 是件观察者模式，执行在主线程。
 *
 * 可以支持一个被观察者，多观察者的情况。
 */
public class MyBusEven {

    private static Hashtable<String, BusliveEvent> liveDataBusEeven = new Hashtable<>();

    public static class singletHolder {
        private static MyBusEven myBusEeven = new MyBusEven();
    }

    public static MyBusEven getInstance() {
        return singletHolder.myBusEeven;
    }

    /**
     * @param key 用于区分不同的接口
     * @return
     */
    public BusliveEvent with(String key) {
        if (!liveDataBusEeven.containsKey(key)) {
            liveDataBusEeven.put(key, new BusliveEvent());
        }
        return (BusliveEvent) liveDataBusEeven.get(key);
    }

    public interface ICallBack {
        void back(Object o);
        void fail();
    }

    public static class BusliveEvent {
        private static Hashtable<Class, ICallBack> iCallBackTable = new Hashtable<>();


        public void postValue(final Object value) {
            if (iCallBackTable.size() == 0) {
                return;
            }
            Enumeration<ICallBack> e2 = iCallBackTable.elements();
            while (e2.hasMoreElements()) {
                ICallBack callBack = (ICallBack) e2.nextElement();
                callBack.back(value);
            }
        }

        public void postFail(final String failMsg) {
            if (iCallBackTable.size() == 0) {
                return;
            }
            Enumeration<ICallBack> e2 = iCallBackTable.elements();
            while (e2.hasMoreElements()) {
                ICallBack callBack = (ICallBack) e2.nextElement();
                callBack.fail();
            }
        }

        /**
         * 传入一个类的class对象。这个是用来区分同一个接口多个观察者的相应
         * @param c
         * @param iCallBack
         */
        public void observe(Class<?> c, final ICallBack iCallBack) {
            iCallBackTable.put(c, iCallBack);
        }

    }
}
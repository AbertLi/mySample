package one.example.com.mysample.main.viewmodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import one.example.com.mysample.main.db.AppDatabase;
import one.example.com.mysample.main.db.dao.JokeDao;
import one.example.com.mysample.main.db.entity.JokeEntity;
import one.example.com.mysample.main.ui.MainActivity;
import one.example.com.mysample.main.viewmodel.MoveLiveDataBean;
import one.example.com.mysample.main.webservice.SendMessageManager;
import one.example.com.mysample.main.webservice.bean.JokeInfoEntity;
import one.example.com.mysample.utile.AppExecutors;
import one.example.com.mysample.utile.EvenType;
import one.example.com.mysample.utile.Logs;
import one.example.com.mysample.utile.MyBusEven;

/**
 *
 * 方案一
 * 和数据库，ViewModle，网络打交道
 * <p>
 * 这个类可以创建一个公共的抽象类，把一些公共的方法抽象到那个抽象类里面，后面要用到的Repository类就实现这个抽象类。把网络访问类放在里面，网络超时直接访问Db。网络返回数据插入Db同时更新UI
 * <p>
 * （网络超时直接访问Db。网络返回数据插入Db同时更新UI --- 这里放在同一个类里面完成 ）
 *
 * 方案二
 * 和数据库，ViewModle，网络打交道 （先直接访问数据库数据等网络数据返回的是直接刷新数据，再更新Ui ）
 *
 */
public class JokeRepository {
    MediatorLiveData<MoveLiveDataBean> mLiveData;
    JokeDao mSubjectsDao;

    public JokeRepository() {
        AppDatabase db = AppDatabase.getInstance();
        mSubjectsDao = db.jokeDao();
    }


//    public void queryAll(MediatorLiveData<List<JokeInfoEntity>> liveData,int numLine, int start) {
//        //JSON.parseObject()  https://blog.csdn.net/weixin_37623470/article/details/79030525
//        LiveData<List<JokeEntity>> liveData1 = mSubjectsDao.query(numLine, start);
//        liveData.addSource(liveData1,liveData::setValue);
//    }
    public void querySubjects(MediatorLiveData<MoveLiveDataBean> liveData,int numLine, int start) {
        //JSON.parseObject()  https://blog.csdn.net/weixin_37623470/article/details/79030525
        LiveData<List<JokeEntity>> liveData1 = mSubjectsDao.query(numLine, start);


        mLiveData = liveData;
        liveData.addSource(liveData1,value->{
            MoveLiveDataBean bean = new MoveLiveDataBean();
            bean.setList(value);
            bean.setSuc(true);
            liveData.setValue(bean);
        });
    }

//    public void queryRating(MediatorLiveData<List<RatingEntity>> liveData,int numLine, int start) {
//        LiveData<List<RatingEntity>> liveData1 = mRatingDao.query(numLine, start);
//        liveData.addSource(liveData1,liveData::setValue);
//    }

    public void getNetData(int start, int con, MediatorLiveData<MoveLiveDataBean> liveData) {
        SendMessageManager.getInstance().getMoveTop(start, con);
        //可以用EventBus框架替换
        MyBusEven.getInstance().with(EvenType.EVEN_TOP250_REQUEST).observe(MainActivity.class,
                new MyBusEven.ICallBack() {
                    @Override
                    public void back(Object o) {
                        if (o==null)
                        {
                            Logs.eprintln(" O The return value is empty");
                            return;
                        }
                        AppExecutors.getNewCachedT().execute(new Runnable() {
                            @Override
                            public void run() {
                                insertAll((JokeInfoEntity) o);
                            }
                        });
                        Logs.eprintln("EVEN_TOP250_REQUEST =" + ((JokeInfoEntity) o).toString());
                    }

                    @Override
                    public void fail() {
                        MoveLiveDataBean bean = new MoveLiveDataBean();
                        bean.setList(null);
                        bean.setSuc(false);
                        liveData.setValue(bean);
                    }
                });
    }

    //此方法在子线程操作
    public void insertAll(JokeInfoEntity entity) {
        if (entity != null) {
            if (entity.getResult() != null) {
                for (JokeEntity jokeinfo : entity.getResult()) {
                    mSubjectsDao.insertSubjects(jokeinfo);
                }
            }
        }
    }
}



package one.example.com.mysample.main.viewmodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import one.example.com.mysample.main.db.AppDatabase;
import one.example.com.mysample.main.db.dao.CastsDao;
import one.example.com.mysample.main.db.dao.DirectorsDao;
import one.example.com.mysample.main.db.dao.GenresDao;
import one.example.com.mysample.main.db.dao.ImagesDao;
import one.example.com.mysample.main.db.dao.RatingDao;
import one.example.com.mysample.main.db.dao.SubjectsDao;
import one.example.com.mysample.main.db.entity.CastsEntity;
import one.example.com.mysample.main.db.entity.DirectorsEntity;
import one.example.com.mysample.main.db.entity.GenresEntity;
import one.example.com.mysample.main.db.entity.ImagesEntity;
import one.example.com.mysample.main.db.entity.RatingEntity;
import one.example.com.mysample.main.db.entity.SubjectsEntity;
import one.example.com.mysample.main.ui.MainActivity;
import one.example.com.mysample.main.viewmodel.MoveLiveDataBean;
import one.example.com.mysample.main.webservice.SendMessageManager;
import one.example.com.mysample.main.webservice.bean.SubjectsBean;
import one.example.com.mysample.main.webservice.bean.TopMovieListInfoEntity;
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
public class MoveTopRepository {
    MediatorLiveData<MoveLiveDataBean> mLiveData;
    SubjectsDao mSubjectsDao;
    RatingDao mRatingDao;
    ImagesDao mImagesDao;
    GenresDao mGenresDao;
    DirectorsDao mDirectorsDao;
    CastsDao mCastsDao;

    public MoveTopRepository() {
        AppDatabase db = AppDatabase.getInstance();
        mSubjectsDao = db.subjectsDao();
        mRatingDao = db.ratingDao();
        mImagesDao = db.imagesDao();
        mGenresDao = db.genresDao();
        mDirectorsDao = db.directorsDao();
        mCastsDao = db.castsDao();
    }


//    public void queryAll(MediatorLiveData<List<TopMovieListInfoEntity>> liveData,int numLine, int start) {
//        //JSON.parseObject()  https://blog.csdn.net/weixin_37623470/article/details/79030525
//        LiveData<List<SubjectsEntity>> liveData1 = mSubjectsDao.query(numLine, start);
//        liveData.addSource(liveData1,liveData::setValue);
//    }
    public void querySubjects(MediatorLiveData<MoveLiveDataBean> liveData,int numLine, int start) {
        //JSON.parseObject()  https://blog.csdn.net/weixin_37623470/article/details/79030525
        LiveData<List<SubjectsEntity>> liveData1 = mSubjectsDao.query(numLine, start);


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
                        insertAll((TopMovieListInfoEntity) o);
                        Logs.eprintln("EVEN_TOP250_REQUEST =" + ((TopMovieListInfoEntity) o).toString());
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

    public void insertAll(TopMovieListInfoEntity entity) {
        if (entity!=null){
            if (entity.getSubjects()!=null){
                for (int i = 0; i < entity.getSubjects().size(); i++) {
                    SubjectsBean bean = entity.getSubjects().get(i);
                    SubjectsEntity subjectsEntity = new SubjectsEntity();
                    subjectsEntity.setId(bean.getId());
                    subjectsEntity.setTitle(bean.getTitle());
                    subjectsEntity.setCollect_count(bean.getCollect_count());
                    subjectsEntity.setOriginal_title(bean.getOriginal_title());
                    subjectsEntity.setYear(bean.getYear());
                    subjectsEntity.setSubtype(bean.getSubtype());
                    subjectsEntity.setAlt(bean.getAlt());

                    SubjectsBean.RatingBean ratingBean = bean.getRating();
                    RatingEntity ratingEntity = new RatingEntity();
                    if (ratingBean!=null){
                        ratingEntity.setSubjects_id(bean.getId());
                        ratingEntity.setMax(ratingBean.getMax());
                        ratingEntity.setAverage(ratingBean.getAverage());
                        ratingEntity.setStars(ratingBean.getStars());
                        ratingEntity.setMin(ratingBean.getMin());
                    }

                    SubjectsBean.ImagesBean imageBean = bean.getImages();
                    ImagesEntity imagesEntity = new ImagesEntity();
                    if (imageBean != null) {
                        imagesEntity.setSubjects_id(bean.getId());
                        imagesEntity.setLarge(imageBean.getLarge());
                        imagesEntity.setMedium(imageBean.getMedium());
                        imagesEntity.setSmall(imageBean.getSmall());
                    }

                    List<String> genres = bean.getGenres();
                    GenresEntity[] genresEntityList = null;
                    if (genres != null) {
                        genresEntityList = new GenresEntity[genres.size()];
                        for (int j = 0; j < genres.size(); j++) {
                            GenresEntity genresEntity = new GenresEntity();
                            genresEntity.setSubjects_id(bean.getId());
                            genresEntity.setGenres(genres.get(j));
                            genresEntityList[j] = genresEntity;
                        }
                    }

                    List<SubjectsBean.CastsBean> casts = bean.getCasts();
                    CastsEntity[]castsEntitiesList = null;
                    if (casts != null) {
                        castsEntitiesList = new CastsEntity[casts.size()];
                        for (int j = 0; j < casts.size(); j++) {
                            CastsEntity castsEntity = new CastsEntity();
                            SubjectsBean.CastsBean castsItem = casts.get(j);
                            if (castsItem != null) {
                                castsEntity.setSubjects_id(bean.getId());
                                castsEntity.setId(castsItem.getId());
                                castsEntity.setAlt(castsItem.getAlt());
                                castsEntity.setName(castsItem.getName());
                                SubjectsBean.CastsBean.AvatarsBean avatarsBean = castsItem.getAvatars();
                                if (avatarsBean != null) {
                                    castsEntity.setLarge(avatarsBean.getLarge());
                                    castsEntity.setMedium(avatarsBean.getMedium());
                                    castsEntity.setSmall(avatarsBean.getSmall());
                                }
                            }
                            castsEntitiesList[j]=castsEntity;
                        }
                    }

                    List<SubjectsBean.DirectorsBean> directors = bean.getDirectors();
                    DirectorsEntity[] directorsEntitiesList = null;
                    if (directors != null) {
                        directorsEntitiesList = new DirectorsEntity[directors.size()];
                        for (int j = 0; j < j; j++) {
                            DirectorsEntity directorsEntityItem = new DirectorsEntity();
                            SubjectsBean.DirectorsBean directorsBean = directors.get(j);
                            if (directorsBean != null) {
                                directorsEntityItem.setSubjects_id(bean.getId());
                                directorsEntityItem.setId(directorsBean.getId());
                                directorsEntityItem.setAlt(directorsBean.getAlt());
                                directorsEntityItem.setName(directorsBean.getName());
                                SubjectsBean.DirectorsBean.AvatarsBeanX avatarsBean = directorsBean.getAvatars();
                                if (avatarsBean != null) {
                                    directorsEntityItem.setLarge(avatarsBean.getLarge());
                                    directorsEntityItem.setMedium(avatarsBean.getMedium());
                                    directorsEntityItem.setSmall(avatarsBean.getSmall());
                                }
                            }
                            directorsEntitiesList[j] = directorsEntityItem;
                        }
                    }

                    mSubjectsDao.insertSubjects(subjectsEntity);
                    mRatingDao.insertRating(ratingEntity);
                    mImagesDao.insertImages(imagesEntity);

                    mGenresDao.insertGenres(genresEntityList);
                    mDirectorsDao.insertDirectors(directorsEntitiesList);
                    mCastsDao.insertCasts(castsEntitiesList);
                }
            }
        }
    }
}



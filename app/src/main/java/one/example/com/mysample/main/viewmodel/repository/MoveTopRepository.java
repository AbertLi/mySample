package one.example.com.mysample.main.viewmodel.repository;

import one.example.com.mysample.main.db.AppDatabase;
import one.example.com.mysample.main.db.dao.CastsDao;
import one.example.com.mysample.main.db.dao.DirectorsDao;
import one.example.com.mysample.main.db.dao.GenresDao;
import one.example.com.mysample.main.db.dao.ImagesDao;
import one.example.com.mysample.main.db.dao.RatingDao;
import one.example.com.mysample.main.db.dao.SubjectsDao;

/**
 *  和数据库，ViewModle，网络打交道
 */
public class MoveTopRepository {
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
}



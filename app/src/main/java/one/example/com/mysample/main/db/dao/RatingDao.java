package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.CastsEntity;
import one.example.com.mysample.main.db.entity.ImagesEntity;
import one.example.com.mysample.main.db.entity.RatingEntity;

@Dao
public interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRating(RatingEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_RATING_TABLE + " Limit:numLine Offset:start")
    LiveData<List<RatingEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.MOVEINFO_RATING_TABLE + " where subjects_id  in (:subjectsId)")
    LiveData<List<RatingEntity>> query(int subjectsId);
}

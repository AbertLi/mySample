package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.CastsEntity;

@Dao
public interface CastsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCasts(CastsEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_CASTS_TABLE + " Limit:numLine Offset:start")
    LiveData<List<CastsEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.MOVEINFO_CASTS_TABLE + " where subjects_id in (:subjectsId)")
    LiveData<List<CastsEntity>> query(int subjectsId);
}

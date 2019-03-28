package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.GenresEntity;

@Dao
public interface GenresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenres(GenresEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_GENRES_TABLE + " Limit:numLine Offset:start")
    LiveData<List<GenresEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.MOVEINFO_GENRES_TABLE + " where subjects_id in (:subjectsId)")
    LiveData<List<GenresEntity>> query(int subjectsId);
}

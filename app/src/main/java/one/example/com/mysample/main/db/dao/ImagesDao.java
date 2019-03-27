package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.CastsEntity;
import one.example.com.mysample.main.db.entity.GenresEntity;
import one.example.com.mysample.main.db.entity.ImagesEntity;

@Dao
public interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjects(ImagesEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_IMAGES_TABLE + " Limit:numLine Offset:start")
    LiveData<List<ImagesEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.MOVEINFO_IMAGES_TABLE + " where subjects_id in (:subjectsId)")
    LiveData<List<ImagesEntity>> query(int subjectsId);
}

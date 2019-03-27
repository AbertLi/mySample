package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.SubjectsEntity;

@Dao
public interface SubjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjects(SubjectsEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_SUBJECTS_TABLE + " Limit:numLine Offset:start")
    LiveData<List<SubjectsEntity>> query(int numLine, int start);


    @Query("select * from " + DbConstant.MOVEINFO_SUBJECTS_TABLE + " Limit:numLine Offset:start")
    List<SubjectsEntity> queryList(int numLine, int start);

}

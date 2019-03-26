package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.DirectorsEntity;

@Dao
public interface DirectorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjects(DirectorsEntity... entity);

    @Query("select * from " + DbConstant.MOVEINFO_DIRECTORS_TABLE + " Limit numLine Offset start")
    LiveData<List<DirectorsEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.MOVEINFO_DIRECTORS_TABLE + " where subjects_id = subjectsId")
    LiveData<List<DirectorsEntity>> query(int subjectsId);
}

package one.example.com.mysample.main.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import one.example.com.mysample.main.db.DbConstant;
import one.example.com.mysample.main.db.entity.JokeEntity;

@Dao
public interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjects(JokeEntity... entity);

    @Query("select * from " + DbConstant.JOKE_ENTITY_TABLE + " Limit:numLine Offset:start")
    LiveData<List<JokeEntity>> query(int numLine, int start);

    @Query("select * from " + DbConstant.JOKE_ENTITY_TABLE + " Limit:numLine Offset:start")
    LiveData<List<JokeEntity>> queryAll(int numLine, int start);
}

package one.example.com.mysample.main.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import one.example.com.mysample.AppInstance;
import one.example.com.mysample.main.db.dao.JokeDao;
import one.example.com.mysample.main.db.entity.JokeEntity;
import one.example.com.mysample.utile.AppExecutors;

/**
 * 目前数据库没有建立索引
 */
@Database(entities = {JokeEntity.class}, version = DbConstant.DB_VERSION_1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mAppDatabase;

    public static AppDatabase getInstance() {
        if (mAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (mAppDatabase == null) {
                    mAppDatabase = buildDatabase(AppInstance.getInstance().getAppContext());
                }
            }
        }
        return mAppDatabase;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DbConstant.DATABASE_NAME)
                   .addCallback(new Callback() {
                       @Override
                       public void onCreate(@NonNull SupportSQLiteDatabase db) {
                           super.onCreate(db);
                           AppExecutors.diskIO().execute(new Runnable() {
                               @Override
                               public void run() {
//                                   AppDatabase database = AppDatabase.getInstance(appContext);
//                                   database.setTransactionSuccessful();
                               }
                           });
                       }
                   })
//                   .addMigrations(MIGRATION_1_2)//这种方法可以添加字段，修改表名等，比较常用。
//                .fallbackToDestructiveMigration()//如果更新新数据库,则丢弃原来的表
//                   .allowMainThreadQueries()//表示可以在主线程访问
                   .build();
    }


    //版本从1升级到2用到的。
    private static final Migration MIGRATION_1_2 = new Migration(DbConstant.DB_VERSION_1, DbConstant.DB_VERSION_2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE  TABLE IF NOT EXISTS 'User'(`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT, `last_name` TEXT ,`age` TEXT)");//添加User表，里面的字段必须和User实体类里面的字段一致。
        }
    };

    public abstract JokeDao jokeDao();
}

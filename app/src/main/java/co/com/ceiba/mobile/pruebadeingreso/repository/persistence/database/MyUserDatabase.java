package co.com.ceiba.mobile.pruebadeingreso.repository.persistence.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

@Database(entities = {UserEntity.class, PostEntity.class}, version = 1)
public abstract class MyUserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract PostDao postDao();

    private static MyUserDatabase sInstance;

    public static synchronized MyUserDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyUserDatabase.class, "user_database")
                    .build();
        }
        return sInstance;
    }
}

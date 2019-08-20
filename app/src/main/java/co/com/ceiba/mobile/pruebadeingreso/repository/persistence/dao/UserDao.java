package co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<UserEntity>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);

}

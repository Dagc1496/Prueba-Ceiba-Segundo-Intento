package co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
@Dao
public interface PostDao {

    @Query("SELECT * FROM post")
    LiveData<List<PostEntity>> getAllPost();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(PostEntity postEntity);

    @Query("SELECT * FROM post WHERE userId = :userId")
    LiveData<List<PostEntity>> getPostByuserId(String userId);
}

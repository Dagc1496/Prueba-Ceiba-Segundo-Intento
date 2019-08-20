package co.com.ceiba.mobile.pruebadeingreso;

import android.content.Context;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.database.MyUserDatabase;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4ClassRunner.class)
public class DataBaseRoomTest {

    private UserDao userDao;
    private MyUserDatabase myUserDatabase;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        myUserDatabase = Room.inMemoryDatabaseBuilder(context, MyUserDatabase.class).build();
        userDao = myUserDatabase.userDao();
    }

    @After
    public void closeDB() throws IOException {
        myUserDatabase.close();
    }

    @Test
    public void writeUserInDataBase(){

        //Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("David");
        userEntity.setEmail("David@checked.com");
        userEntity.setPhone("3168645017");

        //Act
        userDao.insertUser(userEntity);
        List<UserEntity> users = userDao.getAllUsers().getValue();

        //Assert
        assertThat(users.get(0).getName(), equalTo(userEntity.getName()));
    }
}

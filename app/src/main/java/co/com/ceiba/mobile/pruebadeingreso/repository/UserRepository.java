package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.asyncTask.InsertUserAsyncTask;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.database.MyUserDatabase;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.repository.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.repository.rest.WebServiceData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    private WebServiceData webServiceData;
    private UserDao userDao;
    private LiveData<List<UserEntity>> userLiveData;
    private ArrayList<UserEntity> usersList;

    public UserRepository(Application application){
        MyUserDatabase myUserDatabase = MyUserDatabase.getInstance(application);
        userDao = myUserDatabase.userDao();
        usersList = getUserListFromAPI();
        userLiveData = getUserListFromDataBase();
    }

    public void insertUser(ArrayList<UserEntity> users){
        for(int i =0; i<users.size();i++){
            new InsertUserAsyncTask(userDao).execute(users.get(i));
        }
    }

    public LiveData<List<UserEntity>> getUsers(){
        return userLiveData;
    }

    private boolean isDBEmpty(){

        return userDao.getAllUsers().getValue() == null;
    }

    public LiveData<List<UserEntity>> getUserListFromDataBase(){
        userLiveData = userDao.getAllUsers();
        return userLiveData;
    }

    public ArrayList<UserEntity> getUserListFromAPI(){
        usersList = new ArrayList<>();
        if(isDBEmpty()){
            usersList = executeRestService();
        }
        return usersList;
    }

    private ArrayList<UserEntity> executeRestService(){
        final Retrofit retrofit =new Retrofit.Builder().baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceData = retrofit.create(WebServiceData.class);
        webServiceData.getUserGet().enqueue(new Callback<ArrayList<UserEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<UserEntity>> call, Response<ArrayList<UserEntity>> response) {
                usersList = new ArrayList<>();
                usersList = response.body();
                insertUser(usersList);
            }
            @Override
            public void onFailure(Call<ArrayList<UserEntity>> call, Throwable t) {
            }
        });

        return usersList;
    }
}

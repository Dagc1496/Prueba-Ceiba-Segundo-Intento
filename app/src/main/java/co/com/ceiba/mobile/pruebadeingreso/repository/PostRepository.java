package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.asyncTask.InsertPostAsyncTask;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.database.MyUserDatabase;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.repository.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.repository.rest.WebServiceData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private WebServiceData webServiceData;
    private PostDao postDao;
    private LiveData<List<PostEntity>> postLiveData;
    private ArrayList<PostEntity> postList;

    public PostRepository(Application application){
        MyUserDatabase myUserDatabase = MyUserDatabase.getInstance(application);
        postDao = myUserDatabase.postDao();
        postList = getAllPostListFromAPI();
    }

    private void insertPost(ArrayList<PostEntity> posts){
        for(int i =0; i<posts.size();i++){
            new InsertPostAsyncTask(postDao).execute(posts.get(i));
        }
    }

    private ArrayList<PostEntity> getAllPostListFromAPI(){
        postList = new ArrayList<>();
        if(isDBEmpty()){
            postList = executeRestService();
        }
        return postList;
    }

    public LiveData<List<PostEntity>> getPostsByUserId(String userId){
        postLiveData = postDao.getPostByuserId(userId);
        return postLiveData;
    }

    private boolean isDBEmpty(){
        return postDao.getAllPost().getValue() == null;
    }

    private ArrayList<PostEntity> executeRestService(){
        final Retrofit retrofit =new Retrofit.Builder().baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceData = retrofit.create(WebServiceData.class);
        webServiceData.getAllPostsGet().enqueue(new Callback<ArrayList<PostEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<PostEntity>> call, Response<ArrayList<PostEntity>> response) {
                postList = new ArrayList<>();
                postList = response.body();
                insertPost(postList);
            }
            @Override
            public void onFailure(Call<ArrayList<PostEntity>> call, Throwable t) {
            }
        });
        return postList;
    }
}


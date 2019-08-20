package co.com.ceiba.mobile.pruebadeingreso.repository.rest;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServiceData {

    @GET("users")
    Call<ArrayList<UserEntity>> getUserGet();

    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET ("posts")
    Call <ArrayList<PostEntity>> getPostGet(@Query("userId") int userId);

    @GET ("posts")
    Call <ArrayList<PostEntity>> getAllPostsGet();
}
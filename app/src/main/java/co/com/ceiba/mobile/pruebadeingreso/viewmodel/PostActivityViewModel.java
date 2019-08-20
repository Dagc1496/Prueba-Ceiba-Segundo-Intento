package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;

public class PostActivityViewModel extends AndroidViewModel {

    private PostRepository postRepository;
    private LiveData<List<PostEntity>> allPosts;

    public PostActivityViewModel(Application application){
        super(application);
        postRepository = new PostRepository(application);
    }

    public LiveData<List<PostEntity>> getPost(String userId) {
        if(allPosts==null){
            allPosts = postRepository.getPostsByUserId(userId);
        }
        return allPosts;
    }

}

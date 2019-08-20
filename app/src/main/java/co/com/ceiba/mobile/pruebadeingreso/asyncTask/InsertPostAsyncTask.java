package co.com.ceiba.mobile.pruebadeingreso.asyncTask;

import android.os.AsyncTask;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;

public class InsertPostAsyncTask extends AsyncTask<PostEntity, Void, Void> {

    private PostDao postDao;

    public InsertPostAsyncTask(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    protected Void doInBackground(PostEntity... posts) {
        postDao.insertPost(posts[0]);
        return null;
    }
}
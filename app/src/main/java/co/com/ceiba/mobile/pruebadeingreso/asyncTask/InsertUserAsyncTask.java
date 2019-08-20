package co.com.ceiba.mobile.pruebadeingreso.asyncTask;

import android.os.AsyncTask;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

public class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

    private UserDao userDao;

    public InsertUserAsyncTask(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(UserEntity... users) {
        userDao.insertUser(users[0]);
        return null;
    }
}

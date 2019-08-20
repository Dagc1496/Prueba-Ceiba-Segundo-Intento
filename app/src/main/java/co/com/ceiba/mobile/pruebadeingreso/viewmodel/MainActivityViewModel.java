package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.helpers.UsersFilter;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<UserEntity>> allUsers;
    private UsersFilter usersFilter;

    public MainActivityViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getUsers();
    }

    public LiveData<List<UserEntity>> getUsers() {
        return allUsers;
    }

    public List<UserEntity> filterByKeyWord(String keyWord){
        usersFilter = new UsersFilter(getUsers().getValue());
        return usersFilter.filter(keyWord);
    }

    public Intent setUserInformationToIntent(Intent intent, int position){
        intent.putExtra(getApplication().getBaseContext().getString(R.string.user_id), allUsers.getValue().get(position).getId());
        intent.putExtra(getApplication().getBaseContext().getString(R.string.user_name),allUsers.getValue().get(position).getName());
        intent.putExtra(getApplication().getBaseContext().getString(R.string.user_phone),allUsers.getValue().get(position).getPhone());
        intent.putExtra(getApplication().getBaseContext().getString(R.string.user_email),allUsers.getValue().get(position).getEmail());
        return intent;
    }
}

package co.com.ceiba.mobile.pruebadeingreso.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;

public class UsersFilter {

    private List<UserEntity> users;
    private List<UserEntity> usersFilter;

    public UsersFilter(List<UserEntity> users){
        this.users = users;
    }

    public List<UserEntity> filter(String text) {
        usersFilter = new ArrayList<>();

        if (text.length() == 0) {
            return users;
        } else {
            text = text.toLowerCase().trim();
            for (final UserEntity user : users) {
                if (user.getName().toLowerCase().contains(text)) {
                    usersFilter.add(user);
                }
            }
        }

        return usersFilter;
    }
}

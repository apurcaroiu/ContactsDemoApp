package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.datamapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.UserEntity;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

//mapper used for separating domain layer from data layer
public class UserEntityAutoMapper {
    public List<User> transform(Collection<UserEntity> userEntityCollection) {
        final List<User> userList = new ArrayList<>(20);
        for (UserEntity userEntity : userEntityCollection) {
            final User user = transform(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public User transform(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();

        }
        return user;
    }
}

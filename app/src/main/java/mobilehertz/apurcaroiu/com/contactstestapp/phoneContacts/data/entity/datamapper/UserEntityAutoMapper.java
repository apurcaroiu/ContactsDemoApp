package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.datamapper;

import java.util.ArrayList;
import java.util.List;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.UserEntity;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.UsersResponse;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

//mapper used for separating domain layer from data layer
public class UserEntityAutoMapper {
    public List<User> transform(UsersResponse usersResponse) {
        final List<User> userList = new ArrayList<>(20);
        final List<UserEntity> userEntities = usersResponse.getResults();

        for (UserEntity userEntity : userEntities) {
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
            user.setAddress(userEntity.getLocation().getStreet());
            user.setEmail(userEntity.getEmail());
            user.setFirstName(userEntity.getName().getFirst());
            user.setLastName(userEntity.getName().getLast());
            user.setPhoneNumber(userEntity.getPhone());
            user.setPictureUrl(userEntity.getPicture().getThumbnail());
            user.setCoverUrl(userEntity.getPicture().getLarge());

        }
        return user;
    }
}

package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public class UserViewModelMapper {

    public UserViewModel transform(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final UserViewModel userViewModel = new UserViewModel();
        userViewModel.setAddress(user.getAddress());
        userViewModel.setEmail(user.getEmail());
        userViewModel.setFirstName(user.getFirstName());
        userViewModel.setLastName(user.getLastName());
        userViewModel.setPhoneNumber(user.getPhoneNumber());
        userViewModel.setPictureUrl(user.getPictureUrl());

        return userViewModel;
    }

    public Collection<UserViewModel> transform(Collection<User> usersCollection) {
        Collection<UserViewModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (User user : usersCollection) {
                userModelsCollection.add(transform(user));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}

package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public interface UserDetailView extends DataLoadView {
    void displayUser(UserViewModel userViewModel);
}

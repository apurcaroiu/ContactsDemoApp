package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view;

import java.util.Collection;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public interface UsersListView extends DataLoadView{
    void displayUserList(Collection<UserViewModel> userViewModels);
    void viewUser(UserViewModel userViewModel);
}

package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.activity;

import android.net.Uri;
import android.os.Bundle;

import mobilehertz.apurcaroiu.com.contactstestapp.R;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserDetailFragment;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserListFragment;

public class MainActivity extends BaseActivity implements UserListFragment.OnFragmentUserInteractionListener, UserDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchFragment(R.id.fragmentContainer, new UserListFragment());
    }

    @Override
    public void onUserFragmentInteraction(UserViewModel userViewModel) {
        final UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(userViewModel.getFirstName(), userViewModel.getPhoneNumber(), userViewModel.getEmail(), userViewModel.getAddress(), userViewModel.getCoverUrl());
        launchFragment(R.id.fragmentContainer, userDetailFragment);
    }

    @Override
    public void onUserDetailFragmentInteraction(Uri uri) {

    }
}

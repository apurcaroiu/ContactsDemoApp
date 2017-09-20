package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mobilehertz.apurcaroiu.com.contactstestapp.R;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserDetailFragment;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserListFragment;

public class MainActivity extends BaseActivity implements UserListFragment.OnFragmentUserInteractionListener, UserDetailFragment.OnFragmentInteractionListener {

    private Unbinder mUnbinder;

    private static String[] PERMISSIONS_LOCATION = {Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        //mock handle for orientation change
        if (savedInstanceState == null ){
            launchFragment(R.id.fragmentContainer, new UserListFragment());
        }

        requestLocationPermissions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.saved_state),getString(R.string.fragment));
    }

    @Override
    public void onUserFragmentInteraction(UserViewModel userViewModel) {
        final UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(userViewModel.getFirstName(), userViewModel.getPhoneNumber(), userViewModel.getEmail(), userViewModel.getAddress(), userViewModel.getCoverUrl());
        launchFragment(R.id.fragmentContainer, userDetailFragment);
    }

    @Override
    public void onUserDetailFragmentInteraction(Uri uri) {

    }

    protected void requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS_LOCATION, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            Log.d("MainActivity"," permission accepted");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

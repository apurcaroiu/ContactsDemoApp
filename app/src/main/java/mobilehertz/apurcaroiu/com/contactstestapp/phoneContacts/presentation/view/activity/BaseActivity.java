package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserDetailFragment;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserListFragment;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void launchFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        if (fragment instanceof UserDetailFragment){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

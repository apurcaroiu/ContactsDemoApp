package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.activity;

import android.os.Bundle;

import mobilehertz.apurcaroiu.com.contactstestapp.R;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment.UserListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchFragment(R.id.fragmentContainer, new UserListFragment());
    }
}

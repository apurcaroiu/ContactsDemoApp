package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class BaseActivity extends AppCompatActivity {


    protected void launchFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
}

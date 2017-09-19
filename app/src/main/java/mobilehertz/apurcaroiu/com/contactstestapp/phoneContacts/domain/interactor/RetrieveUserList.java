package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.gateway.UserGateway;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public class RetrieveUserList {

    private UserGateway mUserGateway;

    public RetrieveUserList(UserGateway mUserGateway) {
        this.mUserGateway = mUserGateway;
    }

    public Single<List<User>> execute(int pageNumber, int pageSize, String seed){
     return mUserGateway.getUsers(pageNumber, pageSize, seed);
    }
}

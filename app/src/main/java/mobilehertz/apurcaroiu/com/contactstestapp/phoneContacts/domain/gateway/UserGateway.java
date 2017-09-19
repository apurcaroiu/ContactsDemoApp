package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.gateway;

import java.util.List;

import io.reactivex.Single;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public interface UserGateway {
    Single<List<User>> getUsers(int pageNumber, int pageSize, String seed);
}

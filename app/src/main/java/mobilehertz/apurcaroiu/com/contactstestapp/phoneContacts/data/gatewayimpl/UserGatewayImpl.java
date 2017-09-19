package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.gatewayimpl;

import java.util.List;

import io.reactivex.Single;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.datamapper.UserEntityAutoMapper;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.service.RestAPI;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.service.RetrofitServiceFactory;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.gateway.UserGateway;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public class UserGatewayImpl implements UserGateway {

    protected static final RestAPI mService = RetrofitServiceFactory.createRetrofitService(RestAPI.class,RestAPI.BASE_ENDPOINT);

    private UserEntityAutoMapper muUerEntityAutoMapper;

    public UserGatewayImpl(UserEntityAutoMapper muUerEntityAutoMapper) {
        this.muUerEntityAutoMapper = muUerEntityAutoMapper;
    }

    @Override
    public Single<List<User>> getUsers(int pageNumber, int pageSize, String seed) {
        return mService.getUserEntityList(pageNumber,pageSize,seed).map(this.muUerEntityAutoMapper::transform);
    }
}

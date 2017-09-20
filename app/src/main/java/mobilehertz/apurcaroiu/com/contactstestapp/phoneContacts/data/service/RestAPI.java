package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.service;

import io.reactivex.Single;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.UsersResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public interface RestAPI {
    String BASE_ENDPOINT = "https://randomuser.me";


    @GET("/api/")
    Single<UsersResponse> getUserEntityListResponse(@Query("page") int pageNumber, @Query("results") int pageSize, @Query("seed") String seed);
}

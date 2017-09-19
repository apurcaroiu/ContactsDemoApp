package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class RetrofitServiceFactory {
    private static Retrofit retrofit = null;

    public static <T> T createRetrofitService(final Class<T> myClass, final String endPoint){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addConverterFactory(buildGsonConverterFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        T service = retrofit.create(myClass);
        return  service;
    }
    private static GsonConverterFactory buildGsonConverterFactory(){
        Gson gson = new GsonBuilder().create();
        return  GsonConverterFactory.create(gson);
    }
}

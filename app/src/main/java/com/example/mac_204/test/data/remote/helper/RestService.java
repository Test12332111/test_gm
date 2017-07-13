package com.example.mac_204.test.data.remote.helper;

import com.example.mac_204.test.data.remote.models.LocationRestModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by mac-204 on 7/13/17.
 */

public interface RestService {

    @GET("rest.json")
    Observable<List<LocationRestModel>> getLocations(@QueryMap Map<String, String> options);
}

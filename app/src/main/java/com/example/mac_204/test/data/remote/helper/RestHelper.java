package com.example.mac_204.test.data.remote.helper;

import com.example.mac_204.test.data.remote.models.LocationRestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by mac-204 on 7/13/17.
 */

public class RestHelper {


    private RestService restService;

    public RestHelper(RestService restService) {
        this.restService = restService;
    }


    public Observable<List<LocationRestModel>> getLocations() {
        Map<String, String> queryParams = new HashMap<String, String>() {{

        }};

        return restService.getLocations(queryParams);
    }
}

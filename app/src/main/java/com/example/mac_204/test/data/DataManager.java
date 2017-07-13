package com.example.mac_204.test.data;

import com.example.mac_204.test.data.local.db.DBHelper;
import com.example.mac_204.test.data.remote.helper.RestHelper;
import com.example.mac_204.test.data.remote.models.LocationRestModel;

import java.util.List;

import rx.Observable;

/**
 * Created by mac-204 on 7/13/17.
 */

public class DataManager {

    private DBHelper dbHelper;
    private RestHelper restHelper;

    public DataManager(DBHelper dbHelper, RestHelper restHelper) {
        this.dbHelper = dbHelper;
        this.restHelper = restHelper;
    }

    public Observable<List<LocationRestModel>> getLocations() {
        return restHelper.getLocations();
    }
}

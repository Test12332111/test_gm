package com.example.mac_204.test.data;

import com.example.mac_204.test.data.local.db.DBHelper;
import com.example.mac_204.test.data.local.db.models.LocationRealmModel;
import com.example.mac_204.test.data.remote.helper.RestHelper;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.utils.UIMapper;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

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

    public Observable<List<LocationUIModel>> getLocations() {
        return restHelper.getLocations()
                .map(locationRestModels -> UIMapper.mapLocationModels(locationRestModels))
                .flatMap(locationUIModels -> Observable.from(locationUIModels))
                .observeOn(AndroidSchedulers.mainThread())
                .map(locationUIModel -> {
                    locationUIModel.setFavorite(dbHelper.isExistFavorite(locationUIModel.getId()));
                    return locationUIModel;
                }).toList();

    }


    public void updFavorites(List<LocationUIModel> locationUIModels, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        dbHelper.updFavoriteModels(locationUIModels, onSuccess, onError);
    }

    public Observable<List<LocationUIModel>> getFavoriteLocations() {
        return dbHelper.getFavoriteLocations()
                .map(locationRealmModels -> UIMapper.mapLocationRealmModels(locationRealmModels));
    }
}

package com.example.mac_204.test.data.local.db;

import android.content.Context;


import com.example.mac_204.test.data.local.db.models.LocationRealmModel;
import com.example.mac_204.test.data.local.db.models.SightRealmModel;
import com.example.mac_204.test.data.local.db.utils.RealmMapper;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.remote.models.SightRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.Sort;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mac-204 on 7/13/17.
 */

public class DBHelper {

    private Realm realm;

    public DBHelper(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();
    }

    public void updFavoriteModels(List<LocationUIModel> locationUIModels, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        Observable.just(locationUIModels)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(locations ->
                        realm.executeTransactionAsync(bgRealm -> {
                            List<LocationRealmModel> locationRealmModels
                                    = RealmMapper.mapLocationUIModels(locations);
                            for (LocationRealmModel location : locationRealmModels) {
                                if (location.isFavorite()) {
                                    bgRealm.copyToRealmOrUpdate(RealmMapper.mapLocationUIModels(locations));
                                } else {
                                    RealmObject realmObj = bgRealm.where(LocationRealmModel.class)
                                            .equalTo("id", location.getId())
                                            .findFirst();
                                    if (realmObj != null) {
                                        realmObj.deleteFromRealm();
                                    }
                                }
                            }

                        }, onSuccess, onError)
                ).subscribe();
    }

    public Observable<List<LocationRealmModel>> getFavoriteLocations() {
        return realm.where(LocationRealmModel.class)
                .equalTo("isFavorite", Boolean.TRUE)
                .findAllSortedAsync("createTime", Sort.DESCENDING)
                .asObservable()
                .filter(results -> results.isLoaded())
                .first()
                .concatMap(items -> Observable
                        .from(items)
                        .buffer(50)
                ).onBackpressureBuffer()
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public boolean isExistFavorite(String id) {
        return realm.where(LocationRealmModel.class)
                .equalTo("id", id)
                .equalTo("isFavorite", Boolean.TRUE)
                .findFirst() != null;
    }


}

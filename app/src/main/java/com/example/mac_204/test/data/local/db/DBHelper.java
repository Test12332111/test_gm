package com.example.mac_204.test.data.local.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
}

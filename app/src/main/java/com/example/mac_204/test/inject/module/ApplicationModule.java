package com.example.mac_204.test.inject.module;

import android.app.Application;
import android.content.Context;


import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.local.db.DBHelper;
import com.example.mac_204.test.data.remote.helper.RestHelper;
import com.example.mac_204.test.data.remote.helper.RestService;
import com.example.mac_204.test.inject.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper() {
        return new DBHelper(application);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(RestHelper restHelper, DBHelper dbHelper) {
        return new DataManager(dbHelper, restHelper);
    }



}

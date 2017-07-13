package com.example.mac_204.test;

import android.app.Application;
import android.content.Context;

import com.example.mac_204.test.inject.component.ApplicationComponent;
import com.example.mac_204.test.inject.component.DaggerApplicationComponent;
import com.example.mac_204.test.inject.module.ApplicationModule;
import com.example.mac_204.test.inject.module.NetModule;

/**
 * Created by mac-204 on 7/13/17.
 */

public class App extends Application {

    private static ApplicationComponent mApplicationComponent;

    private static final String BASE_URL = "https://raw.githubusercontent.com/Test12332111/test_rest/master/";


    @Override
    public void onCreate() {
        super.onCreate();

        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .netModule(new NetModule(BASE_URL))
                    .build();
        }




    }

    public  static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}

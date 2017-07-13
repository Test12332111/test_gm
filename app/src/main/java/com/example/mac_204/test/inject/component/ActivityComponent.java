package com.example.mac_204.test.inject.component;



import android.content.Context;

import com.example.mac_204.test.inject.module.ActivityModule;
import com.example.mac_204.test.inject.qualifier.ActivityContext;
import com.example.mac_204.test.inject.qualifier.ApplicationContext;
import com.example.mac_204.test.inject.scope.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    @ActivityContext
    Context context();

}

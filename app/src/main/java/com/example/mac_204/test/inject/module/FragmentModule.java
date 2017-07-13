package com.example.mac_204.test.inject.module;

import android.app.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;


import com.example.mac_204.test.inject.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {


    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return this.fragment;
    }

    @Provides
    Activity provideActivity() {
        return this.fragment.getActivity();
    }

    @Provides
    @ActivityContext
    Context provideActivityContext() {
        return this.fragment.getActivity();
    }

}

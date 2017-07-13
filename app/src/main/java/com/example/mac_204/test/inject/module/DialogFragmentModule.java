package com.example.mac_204.test.inject.module;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;


import com.example.mac_204.test.inject.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;


@Module
public class DialogFragmentModule {


    private DialogFragment fragment;

    public DialogFragmentModule(DialogFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    DialogFragment provideFragment() {
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

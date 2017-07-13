package com.example.mac_204.test.ui.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.mac_204.test.App;
import com.example.mac_204.test.inject.component.ActivityComponent;
import com.example.mac_204.test.inject.module.ActivityModule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;

/**
 * Created by mac-204 on 7/13/17.
 */

public class BaseActivity extends MvpAppCompatActivity{

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ActivityComponent> componentsMap = new HashMap<>();

    private ActivityComponent activityComponent;
    private long activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ActivityComponent mActivityComponent;
        if (!componentsMap.containsKey(activityId)) {
            mActivityComponent = App.get(this)
                    .getApplicationComponent()
                    .activityComponent(new ActivityModule(this));
            componentsMap.put(activityId, mActivityComponent);
        } else {
            mActivityComponent = componentsMap.get(activityId);
        }
        this.activityComponent = mActivityComponent;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            componentsMap.remove(activityId);
        }
        super.onDestroy();
    }


    public ActivityComponent activityComponent() {
        return activityComponent;
    }
}

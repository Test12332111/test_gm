package com.example.mac_204.test.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.mac_204.test.App;
import com.example.mac_204.test.inject.component.FragmentComponent;
import com.example.mac_204.test.inject.module.FragmentModule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mac-204 on 7/13/17.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    protected static final Map<Long, FragmentComponent> componentsMap = new HashMap<>();

    protected Unbinder unbinder;
    private FragmentComponent fragmentComponent;
    private long fragmentId;


    public abstract String getFragmentTag();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(false);
        fragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) : NEXT_ID.getAndIncrement();
        if (!componentsMap.containsKey(fragmentId)) {
            fragmentComponent = App.get(getActivity())
                    .getApplicationComponent()
                    .fragmentComponent(new FragmentModule(this));
            componentsMap.put(fragmentId, fragmentComponent);
        } else {
            fragmentComponent = componentsMap.get(fragmentId);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            componentsMap.remove(fragmentId);
        }
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    protected FragmentComponent fragmentComponent() {
        return fragmentComponent;
    }
}

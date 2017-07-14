package com.example.mac_204.test.ui.activities.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mac_204.test.R;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.favorites.FavoritesFragment;
import com.example.mac_204.test.ui.fragments.list.ListFragment;
import com.example.mac_204.test.ui.fragments.map.MapFragment;

import java.util.HashMap;

/**
 * Created by mac-204 on 7/14/17.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {


    private HashMap<Integer, BaseFragment> fragmentHashMap;
    private int currentFragmentId;

    public MainPresenter() {
        this.fragmentHashMap = new HashMap<>();
        prepareStartFragmentsList();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        currentFragmentId = R.id.navigation_list;
        getViewState().setFirstFragment(fragmentHashMap.get(currentFragmentId));
    }

    public void setFragment(int id) {
        currentFragmentId = id;
        getViewState().setTapFragment(fragmentHashMap.get(currentFragmentId));
    }

    private void prepareStartFragmentsList(){
        this.fragmentHashMap.put(R.id.navigation_list, ListFragment.getInstance());
        this.fragmentHashMap.put(R.id.navigation_map, MapFragment.getInstance());
        this.fragmentHashMap.put(R.id.navigation_favorite, FavoritesFragment.getInstance());
    }
}

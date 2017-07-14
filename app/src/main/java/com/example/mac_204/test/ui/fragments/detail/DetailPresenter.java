package com.example.mac_204.test.ui.fragments.detail;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mac_204.test.App;
import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.inject.qualifier.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by mac-204 on 7/13/17.
 */

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private LocationUIModel bundleLocationUIModel;

    @Inject DataManager dataManager;

    @Inject @ApplicationContext Context context;


    public DetailPresenter(LocationUIModel bundleLocationUIModel) {
        App.getApplicationComponent().inject(this);
        this.bundleLocationUIModel = bundleLocationUIModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().updFavorite(bundleLocationUIModel.isFavorite());
    }

    void getLocation(){
        getViewState().updLocation(bundleLocationUIModel);
    }

    void updFavorite(){
        bundleLocationUIModel.setFavorite(!bundleLocationUIModel.isFavorite());
        List<LocationUIModel> locationUIModels = new ArrayList<>();
        locationUIModels.add(bundleLocationUIModel);
        dataManager.updFavorites(locationUIModels, () -> {
            getViewState().updFavorite(bundleLocationUIModel.isFavorite());
        }, error -> {
            getViewState().onError("Error");
        });
    }

}

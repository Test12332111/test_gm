package com.example.mac_204.test.ui.fragments.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mac_204.test.App;
import com.example.mac_204.test.data.ui.models.LocationUIModel;

/**
 * Created by mac-204 on 7/13/17.
 */

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private LocationUIModel bundleLocationUIModel;


    public DetailPresenter(LocationUIModel bundleLocationUIModel) {
        App.getApplicationComponent().inject(this);
        this.bundleLocationUIModel = bundleLocationUIModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    void getLocation(){
        getViewState().updLocation(bundleLocationUIModel);
    }
}

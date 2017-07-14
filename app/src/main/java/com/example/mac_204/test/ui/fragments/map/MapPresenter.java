package com.example.mac_204.test.ui.fragments.map;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.mac_204.test.App;
import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.utils.UIMapper;
import com.example.mac_204.test.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mac-204 on 7/13/17.
 */
@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    private Subscription getLocationsSubscription;

    @Inject DataManager dataManager;

    public MapPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(getLocationsSubscription);
    }

    void getLocations(){
        RxUtil.unsubscribe(getLocationsSubscription);
        getViewState().showRefresh(true);
        getLocationsSubscription = dataManager.getLocations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<LocationUIModel>>() {
                    @Override
                    public void onCompleted() {
                        getViewState().showRefresh(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showRefresh(false);
                        getViewState().onError("Error");
                    }

                    @Override
                    public void onNext(List<LocationUIModel> locationUIModels) {
                        getViewState().updLocations(locationUIModels);
                    }
                });

    }
}

package com.example.mac_204.test.ui.fragments.list;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mac_204.test.App;
import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.remote.models.SightRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.utils.UIMapper;
import com.example.mac_204.test.util.RxUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

    private Subscription getLocationsSubscription;

    @Inject DataManager dataManager;

    public ListPresenter() {
        App.getApplicationComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getLocations();
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
                .map(new Func1<List<LocationRestModel>, List<LocationUIModel>>() {
                    @Override
                    public List<LocationUIModel> call(List<LocationRestModel> locationRestModels) {
                        return UIMapper.mapLocationModels(locationRestModels);
                    }
                })
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
                        getViewState().onError("gg");  // TODO // FIXME: 7/13/17
                    }

                    @Override
                    public void onNext(List<LocationUIModel> locationUIModels) {
                       getViewState().updLocations(locationUIModels);
                    }
                });

    }



}

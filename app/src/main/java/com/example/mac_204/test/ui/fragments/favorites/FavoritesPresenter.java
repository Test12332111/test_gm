package com.example.mac_204.test.ui.fragments.favorites;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mac_204.test.App;
import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mac-204 on 7/14/17.
 */
@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView> {


    private Subscription getFavoritesSubscription;

    @Inject DataManager dataManager;

    public FavoritesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(FavoritesView view) {
        super.attachView(view);
        getFavoriteLocations();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(getFavoritesSubscription);
    }

    void getFavoriteLocations(){
        getViewState().clearFavoritesLocations();
        RxUtil.unsubscribe(getFavoritesSubscription);
        getFavoritesSubscription = dataManager
                .getFavoriteLocations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<LocationUIModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //NOP
                    }

                    @Override
                    public void onNext(List<LocationUIModel> locationUIModels) {
                        getViewState().addFavoritesLocations(locationUIModels);
                    }
                });
    }

}

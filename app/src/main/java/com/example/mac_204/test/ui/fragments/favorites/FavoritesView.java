package com.example.mac_204.test.ui.fragments.favorites;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.mac_204.test.data.ui.models.LocationUIModel;

import java.util.List;

/**
 * Created by mac-204 on 7/14/17.
 */

public interface FavoritesView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updFavoritesLocations(List<LocationUIModel> locationUIModels);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void addFavoritesLocations(List<LocationUIModel> locationUIModels);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearFavoritesLocations();
}

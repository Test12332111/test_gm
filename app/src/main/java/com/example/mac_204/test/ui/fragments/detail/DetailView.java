package com.example.mac_204.test.ui.fragments.detail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.mac_204.test.data.ui.models.LocationUIModel;

import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public interface DetailView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updLocation(LocationUIModel locationUIModel);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updFavorite(boolean favorite);
}

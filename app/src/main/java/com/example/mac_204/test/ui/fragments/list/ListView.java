package com.example.mac_204.test.ui.fragments.list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;

import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public interface ListView  extends MvpView{

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showRefresh(boolean show);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updLocations(List<LocationUIModel> locationUIModels);
}

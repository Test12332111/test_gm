package com.example.mac_204.test.ui.activities.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.mac_204.test.ui.fragments.BaseFragment;

/**
 * Created by mac-204 on 7/14/17.
 */

public interface MainView extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void setTapFragment(BaseFragment fragment);

    @StateStrategyType(SingleStateStrategy.class)
    void setFirstFragment(BaseFragment fragment);
}

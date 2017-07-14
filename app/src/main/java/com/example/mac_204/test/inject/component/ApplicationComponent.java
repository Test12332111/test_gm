package com.example.mac_204.test.inject.component;



import android.content.Context;

import com.example.mac_204.test.data.DataManager;
import com.example.mac_204.test.data.remote.helper.RestHelper;
import com.example.mac_204.test.inject.module.ActivityModule;
import com.example.mac_204.test.inject.module.ApplicationModule;
import com.example.mac_204.test.inject.module.DialogFragmentModule;
import com.example.mac_204.test.inject.module.FragmentModule;
import com.example.mac_204.test.inject.module.NetModule;
import com.example.mac_204.test.inject.qualifier.ApplicationContext;
import com.example.mac_204.test.ui.fragments.detail.DetailPresenter;
import com.example.mac_204.test.ui.fragments.favorites.FavoritesPresenter;
import com.example.mac_204.test.ui.fragments.list.ListFragment;
import com.example.mac_204.test.ui.fragments.list.ListPresenter;
import com.example.mac_204.test.ui.fragments.map.MapPresenter;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {


    void inject(FavoritesPresenter presenter);

    void inject(DetailPresenter presenter);

    void inject(ListPresenter presenter);

    void inject(MapPresenter presenter);

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

    DialogFragmentComponent dialogComponent(DialogFragmentModule dialogModule);



    @ApplicationContext Context context();

    DataManager dataManager();

    RestHelper restHelper();

}


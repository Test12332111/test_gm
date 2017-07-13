package com.example.mac_204.test.inject.component;




import com.example.mac_204.test.inject.module.FragmentModule;
import com.example.mac_204.test.inject.scope.FragmentScope;
import com.example.mac_204.test.ui.fragments.detail.DetailFragment;
import com.example.mac_204.test.ui.fragments.list.ListFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(ListFragment fragment);

    void inject(DetailFragment fragment);

}

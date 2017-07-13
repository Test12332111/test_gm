package com.example.mac_204.test.inject.component;


import com.example.mac_204.test.inject.module.DialogFragmentModule;
import com.example.mac_204.test.inject.scope.DialogFragmentScope;

import dagger.Subcomponent;


@DialogFragmentScope
@Subcomponent(modules = DialogFragmentModule.class)
public interface DialogFragmentComponent {


}

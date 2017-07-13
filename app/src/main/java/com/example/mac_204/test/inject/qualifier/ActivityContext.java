package com.example.mac_204.test.inject.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;



@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}

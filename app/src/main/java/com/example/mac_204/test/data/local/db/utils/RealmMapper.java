package com.example.mac_204.test.data.local.db.utils;

import com.example.mac_204.test.data.local.db.models.LocationRealmModel;
import com.example.mac_204.test.data.local.db.models.SightRealmModel;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.remote.models.SightRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by mac-204 on 7/13/17.
 */

public class RealmMapper {


    public static SightRealmModel mapSighRestModel(SightRestModel sightRestModel) {
        return new SightRealmModel(
                sightRestModel.getId(),
                sightRestModel.getName(),
                sightRestModel.getIconUrl(),
                sightRestModel.getBackgroundUrl(),
                sightRestModel.getLat(),
                sightRestModel.getLan(),
                sightRestModel.getDescription(),
                sightRestModel.getType());
    }

    public static RealmList<SightRealmModel> mapSighRestModels(List<SightRestModel> sightRestModels) {
        RealmList<SightRealmModel> sightRealmModels = new RealmList<>();
        for (SightRestModel sightRestModel : sightRestModels) {
            sightRealmModels.add(mapSighRestModel(sightRestModel));
        }

        return sightRealmModels;
    }


    public static LocationRealmModel mapLocationRestModel(LocationRestModel locationRestModel) {
        LocationRealmModel locationRealmModel = new LocationRealmModel(
                locationRestModel.getId(),
                locationRestModel.getName(),
                locationRestModel.getIconUrl(),
                locationRestModel.getBackgroundUrl(),
                locationRestModel.getLat(),
                locationRestModel.getLan(),
                false,
                System.currentTimeMillis(),
                mapSighRestModels(locationRestModel.getSights())
        );

        return locationRealmModel;
    }

    public static List<LocationRealmModel> mapLocationModels(List<LocationRestModel> locationRestModels) {
        List<LocationRealmModel> locationRealmModels = new ArrayList<>();
        for (LocationRestModel locationRestModel :locationRestModels) {
            locationRealmModels.add(mapLocationRestModel(locationRestModel));
        }

        return locationRealmModels;
    }



    public static SightRealmModel mapSighUIModel(SightUIModel sightUIModel) {
        return new SightRealmModel(
                sightUIModel.getId(),
                sightUIModel.getName(),
                sightUIModel.getIconUrl(),
                sightUIModel.getBackgroundUrl(),
                sightUIModel.getLat(),
                sightUIModel.getLan(),
                sightUIModel.getDescription(),
                sightUIModel.getType());
    }

    public static RealmList<SightRealmModel> mapSighUIModels(List<SightUIModel> sightUIModels) {
        RealmList<SightRealmModel> sightRealmModels = new RealmList<>();
        for (SightUIModel sightUIModel :sightUIModels) {
            sightRealmModels.add(mapSighUIModel(sightUIModel));
        }

        return sightRealmModels;
    }


    public static LocationRealmModel mapLocationUIModel(LocationUIModel locationUIModel) {
        LocationRealmModel locationRealmModel = new LocationRealmModel(
                locationUIModel.getId(),
                locationUIModel.getName(),
                locationUIModel.getIconUrl(),
                locationUIModel.getBackgroundUrl(),
                locationUIModel.getLat(),
                locationUIModel.getLan(),
                locationUIModel.isFavorite(),
                System.currentTimeMillis(),
                 mapSighUIModels(locationUIModel.getSights())
        );

        return locationRealmModel;
    }

    public static List<LocationRealmModel> mapLocationUIModels(List<LocationUIModel> locationUIModels) {
        List<LocationRealmModel> locationRealmModels = new ArrayList<>();
        for (LocationUIModel locationUIModel :locationUIModels) {
            locationRealmModels.add(mapLocationUIModel(locationUIModel));
        }

        return locationRealmModels;
    }
}

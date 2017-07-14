package com.example.mac_204.test.data.ui.utils;

import com.example.mac_204.test.data.local.db.models.LocationRealmModel;
import com.example.mac_204.test.data.local.db.models.SightRealmModel;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.remote.models.SightRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class UIMapper {


    public static SightUIModel mapSighModel(SightRestModel sightRestModel) {
        return new SightUIModel(
                sightRestModel.getId(),
                sightRestModel.getName() != null ? sightRestModel.getName() : "-",
                sightRestModel.getIconUrl(),
                sightRestModel.getBackgroundUrl(),
                sightRestModel.getLat(),
                sightRestModel.getLan(),
                sightRestModel.getDescription() != null ? sightRestModel.getDescription() : "-",
                sightRestModel.getType() != null ? sightRestModel.getType() : "-");
    }

    public static List<SightUIModel> mapSighModels(List<SightRestModel> sightRestModels) {
        List<SightUIModel> sightUIModels = new ArrayList<>();
        for (SightRestModel sightRestModel : sightRestModels) {
            sightUIModels.add(mapSighModel(sightRestModel));
        }

        return sightUIModels;
    }

    public static LocationUIModel mapLocationModel(LocationRestModel locationRestModel) {
        LocationUIModel uiModel = new LocationUIModel(
                locationRestModel.getId(),
                locationRestModel.getName() != null ? locationRestModel.getName() : "-",
                locationRestModel.getIconUrl(),
                locationRestModel.getBackgroundUrl(),
                locationRestModel.getLat(),
                locationRestModel.getLan(),
                mapSighModels(locationRestModel.getSights())
        );

        return uiModel;
    }

    public static List<LocationUIModel> mapLocationModels(List<LocationRestModel> locationRestModels) {
        List<LocationUIModel> locationUIModels = new ArrayList<>();
        for (LocationRestModel locationRestModel : locationRestModels) {
            locationUIModels.add(mapLocationModel(locationRestModel));
        }

        return locationUIModels;
    }

    public static SightUIModel mapSighModel(SightRealmModel sightRealmModel) {
        return new SightUIModel(
                sightRealmModel.getId(),
                sightRealmModel.getName() != null ? sightRealmModel.getName() : "-",
                sightRealmModel.getIconUrl(),
                sightRealmModel.getBackgroundUrl(),
                sightRealmModel.getLat(),
                sightRealmModel.getLan(),
                sightRealmModel.getDescription() != null ? sightRealmModel.getDescription() : "-",
                sightRealmModel.getType() != null ? sightRealmModel.getType() : "-");
    }

    public static List<SightUIModel> mapSighRealmModels(List<SightRealmModel> sightRealmModels) {
        List<SightUIModel> sightUIModels = new ArrayList<>();
        for (SightRealmModel sightRealmModel : sightRealmModels) {
            sightUIModels.add(mapSighModel(sightRealmModel));
        }

        return sightUIModels;
    }

    public static LocationUIModel mapLocationModel(LocationRealmModel locationRealmModel) {
        LocationUIModel uiModel = new LocationUIModel(
                locationRealmModel.getId(),
                locationRealmModel.getName() != null ? locationRealmModel.getName() : "-",
                locationRealmModel.getIconUrl(),
                locationRealmModel.getBackgroundUrl(),
                locationRealmModel.getLat(),
                locationRealmModel.getLan(),
                mapSighRealmModels(locationRealmModel.getSightRealmModels())
        );

        uiModel.setFavorite(locationRealmModel.isFavorite());
        return uiModel;
    }

    public static List<LocationUIModel> mapLocationRealmModels(List<LocationRealmModel> LocationRealmModels) {
        List<LocationUIModel> locationUIModels = new ArrayList<>();
        for (LocationRealmModel locationRealmModel : LocationRealmModels) {
            locationUIModels.add(mapLocationModel(locationRealmModel));
        }

        return locationUIModels;
    }
}

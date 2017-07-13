package com.example.mac_204.test.data.ui.utils;

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
        return new SightUIModel(sightRestModel.getName(),
                sightRestModel.getIconUrl(),
                sightRestModel.getBackgroundUrl(),
                sightRestModel.getLat(),
                sightRestModel.getLan(),
                sightRestModel.getDescription(),
                sightRestModel.getType());
    }

    public static List<SightUIModel> mapSighModels(List<SightRestModel> sightRestModels) {
        List<SightUIModel> sightUIModels = new ArrayList<>();
        for (SightRestModel sightRestModel :sightRestModels) {
            sightUIModels.add(mapSighModel(sightRestModel));
        }

        return sightUIModels;
    }


    public static LocationUIModel mapLocationModel(LocationRestModel locationRestModel) {
        LocationUIModel uiModel = new LocationUIModel(locationRestModel.getName(),
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
        for (LocationRestModel locationRestModel :locationRestModels) {
            locationUIModels.add(mapLocationModel(locationRestModel));
        }

        return locationUIModels;
    }
}

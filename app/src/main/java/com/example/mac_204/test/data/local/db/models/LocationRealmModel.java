package com.example.mac_204.test.data.local.db.models;

import com.example.mac_204.test.data.ui.models.SightUIModel;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mac-204 on 7/14/17.
 */

public class LocationRealmModel extends RealmObject {

    @PrimaryKey
    private String id;

    private String name;

    private String iconUrl;

    private String backgroundUrl;

    private long createTime;

    private double lat;

    private double lan;

    private boolean isFavorite;

    private RealmList<SightRealmModel> sightRealmModels;

    public LocationRealmModel() {
    }

    public LocationRealmModel(String id, String name, String iconUrl, String backgroundUrl,
                              double lat, double lan, boolean isFavorite,
                              long createTime, RealmList<SightRealmModel> sightRealmModels) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
        this.lat = lat;
        this.lan = lan;
        this.createTime = createTime;
        this.isFavorite = isFavorite;
        this.sightRealmModels = sightRealmModels;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public double getLat() {
        return lat;
    }

    public double getLan() {
        return lan;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public RealmList<SightRealmModel> getSightRealmModels() {
        return sightRealmModels;
    }

    public void setSightRealmModels(RealmList<SightRealmModel> sightRealmModels) {
        this.sightRealmModels = sightRealmModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

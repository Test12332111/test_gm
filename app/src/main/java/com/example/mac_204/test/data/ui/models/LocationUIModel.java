package com.example.mac_204.test.data.ui.models;

import com.example.mac_204.test.data.remote.models.SightRestModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class LocationUIModel implements Serializable{

    private String name;

    private String iconUrl;

    private String backgroundUrl;

    private double lat;

    private double lan;

    private List<SightUIModel> sights;

    private boolean isFavorite;

    public LocationUIModel(String name, String iconUrl, String backgroundUrl, double lat,
                           double lan, List<SightUIModel> sights) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
        this.lat = lat;
        this.lan = lan;
        this.sights = sights;
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

    public List<SightUIModel> getSights() {
        return sights;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

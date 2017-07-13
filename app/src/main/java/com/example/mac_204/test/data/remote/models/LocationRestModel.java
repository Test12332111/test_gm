package com.example.mac_204.test.data.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class LocationRestModel {

    @SerializedName("name")
    private String name;

    @SerializedName("icon")
    private String iconUrl;

    @SerializedName("background_image")
    private String backgroundUrl;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lan")
    private double lan;

    @SerializedName("sights")
    private List<SightRestModel> sights;



    public LocationRestModel(String name, String iconUrl, String backgroundUrl, double lat, double lan, List<SightRestModel> sights) {
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

    public List<SightRestModel> getSights() {
        return sights;
    }
}

package com.example.mac_204.test.data.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class SightRestModel extends LocationRestModel{

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;


    public SightRestModel(String id, String name, String iconUrl, String backgroundUrl,
                          double lat, double lan, String description, String type) {
        super(id, name, iconUrl, backgroundUrl, lat, lan, null);
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}

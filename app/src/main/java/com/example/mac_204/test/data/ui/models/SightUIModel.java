package com.example.mac_204.test.data.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class SightUIModel extends LocationUIModel implements Serializable {

    private String description;

    private String type;

    public SightUIModel(String name, String iconUrl, String backgroundUrl, double lat, double lan, String description, String type) {
        super(name, iconUrl, backgroundUrl, lat, lan, null);
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

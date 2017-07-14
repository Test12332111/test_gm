package com.example.mac_204.test.data.local.db.models;

import com.example.mac_204.test.data.remote.models.LocationRestModel;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mac-204 on 7/14/17.
 */

public class SightRealmModel extends RealmObject {

    @PrimaryKey
    private String id;

    private String description;

    private String type;

    private String name;

    private String iconUrl;

    private String backgroundUrl;

    private double lat;

    private double lan;

    public SightRealmModel() {
    }

    public SightRealmModel(String id, String name, String iconUrl, String backgroundUrl, double lat, double lan, String description, String type) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
        this.lat = lat;
        this.lan = lan;
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }
}

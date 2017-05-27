package com.sncss.haemtravel.Bean;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Created by developer1 on 30/3/16.
 * Holds the data on the meinFavorites
 */
public class MeinFavorites implements Item{

    private int id;
    private String center_id;
    private String center_name;
    private String country_iso;
    private String country_name;

     public MeinFavorites(int id, String center_id, String center_name, String country_iso, String country_name) {
        this.id = id;
        this.center_id = center_id;
        this.center_name = center_name;
        this.country_iso = country_iso;
        this.country_name = country_name;
    }
    public MeinFavorites(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getCountry_iso() {
        return country_iso;
    }

    public void setCountry_iso(String country_iso) {
        this.country_iso = country_iso;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return "MeinFavorites{"+
                "id="+id+
                ", country_iso='" + country_iso + '\'' +
                ", center_id='" + center_id + '\'' +
                ", center_name='" + center_name+'\'' +
                ", country_id='" + country_name+'\'' +
                '}';
    }

    @Override
    public boolean isSection() {
        return false;
    }
}

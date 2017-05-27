package com.sncss.haemtravel.Bean;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Bean class for holding the center data
 */
public class CenterListBean {

    public  String title="";
    public  String distance="";
    public String centerCode;
    public String lat="";
    public String logi="";
    private String address;
    private String stadt;
    public String strasse;

    /*public final String flag_ref;*/

    public void setTitle(String title) {
        this.title =title;
    }

    public void setDistance(String distance) {
        this.distance =distance;
    }
    public void setCenteCode(String cc){this.centerCode=cc;}

    public String getTitle() {
        return title;
    }
    public String  getCenterCode(){
        return centerCode;
    }

    public String getDistance() {
        return distance;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLogi() {
        return logi;
    }

    public void setLogi(String logi) {
        this.logi = logi;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }
    public String getStadt() {
        return stadt;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStrasse() {
        return strasse;
    }
}

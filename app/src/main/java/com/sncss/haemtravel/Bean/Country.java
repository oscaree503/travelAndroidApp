package com.sncss.haemtravel.Bean;

import android.graphics.drawable.Drawable;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Created by developer1 on 17/3/16.
 *  Bean class for holding the country data with the section indexer
 */
public class Country implements Item {
    private String name;
    private String code;
    private String flag;
    public Country(String name,String flag, String code ){
        this.name = name;
        this.code = code;
        this.flag = flag;
    }

    public Country() {

    }

    public String getName() {
        return name;
    }
    public String getFlag() {
        return flag;
    }
    public String getCode() {
        return code;
    }


    @Override
    public boolean isSection() {
        return false;
    }
}



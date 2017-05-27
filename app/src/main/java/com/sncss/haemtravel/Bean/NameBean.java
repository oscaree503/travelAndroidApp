package com.sncss.haemtravel.Bean;

import java.io.Serializable;

/**
 * Created by SONY on 21-04-2016.
 * it holds the multiple name of the center.
 */
public class NameBean implements Serializable {
    public  String name="";

    public void setName(String namen) {
        name = namen;
    }

    public String getName() {
        return name;
    }
}

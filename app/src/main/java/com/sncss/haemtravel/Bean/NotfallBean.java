package com.sncss.haemtravel.Bean;

import java.io.Serializable;

/**
 * Created by SONY on 21-04-2016.
 * Used to store the notfall number
 */
public class NotfallBean implements Serializable {
    public  String Notfalls="";

    public void setNotfalls(String notfalls) {
        Notfalls = notfalls;
    }

    public String getNotfalls() {
        return Notfalls;
    }
}

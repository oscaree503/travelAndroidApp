package com.sncss.haemtravel.Bean;

import java.io.Serializable;

/**
 * Created by SONY on 21-04-2016.
 * Use to store the email used by email adapter
 */
public class EmailBean implements Serializable {
    public  String Email="";

    public void setEmail(String email) {
        Email = email;
    }

    public String getEamil() {
        return Email;
    }
}

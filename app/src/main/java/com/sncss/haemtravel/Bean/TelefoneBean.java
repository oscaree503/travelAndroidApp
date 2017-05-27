package com.sncss.haemtravel.Bean;

import java.io.Serializable;

/**
 * Created by SONY on 21-04-2016.
 * use tohold the telefone number
 */
public class TelefoneBean implements Serializable {
    public  String Telefone="";

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getTelefone() {
        return Telefone;
    }
}

package com.sncss.haemtravel.Bean;

import android.view.View;

/**
 * Created by SONY on 26-03-2016.
 * Holds the data added by the user on cityplae detail activity
 */
public class DoctorBean  {
    public int docId;
    public  String docName="";
    public  String telefoneCode="";
    public String telefone="";
    public String email="";

    /*public final String flag_ref;*/

    public void setDocName(String docName) {
        this.docName =docName;
    }
    public void setDocId(int docId) {
        this.docId = docId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefoneCode(String plzNum) {
        this.telefoneCode =plzNum;
    }
    public void setTelefone(String telefone){this.telefone=telefone;}

    public int getDocId() {
       return docId ;
    }
    public String getDocName() {
        return this.docName;
    }
    public String  getTelefoneCode(){
        return this.telefoneCode;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public String getEmail() {
        return this.email;
    }


}

package com.sncss.haemtravel.Bean;

/**
 * Created by developer1 on 11/3/16.
 * It holds the data on seeting screen tab 1 that is meineDaten
 */
public class MeineDatenGetSet {

    private int id;
    private String name;
    private String strabe;
    private String plz_ort_code;
    private String plz_ort_main;
    private String telefon_code;
    private String telefon_main;
    private String e_mail;
    private String geburtstag;

    public MeineDatenGetSet(){}

    public MeineDatenGetSet(int id, String name, String strabe, String plz_ort_code, String plz_ort_main, String telefon_code, String telefon_main, String e_mail, String geburtstag) {
        this.id = id;
        this.name = name;
        this.strabe = strabe;
        this.plz_ort_code = plz_ort_code;
        this.plz_ort_main = plz_ort_main;
        this.telefon_code = telefon_code;
        this.telefon_main = telefon_main;
        this.e_mail = e_mail;
        this.geburtstag = geburtstag;
    }

    @Override
    public String toString() {
        return "MeineDatenGetSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strabe='" + strabe + '\'' +
                ", plz_ort_code='" + plz_ort_code + '\'' +
                ", plz_ort_main='" + plz_ort_main + '\'' +
                ", telefon_code='" + telefon_code + '\'' +
                ", telefon_main='" + telefon_main + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", geburtstag='" + geburtstag + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrabe() {
        return strabe;
    }

    public void setStrabe(String strabe) {
        this.strabe = strabe;
    }

    public String getPlz_ort_code() {
        return plz_ort_code;
    }

    public void setPlz_ort_code(String plz_ort_code) {
        this.plz_ort_code = plz_ort_code;
    }

    public String getPlz_ort_main() {
        return plz_ort_main;
    }

    public void setPlz_ort_main(String plz_ort_main) {
        this.plz_ort_main = plz_ort_main;
    }

    public String getTelefon_code() {
        return telefon_code;
    }

    public void setTelefon_code(String telefon_code) {
        this.telefon_code = telefon_code;
    }

    public String getTelefon_main() {
        return telefon_main;
    }

    public void setTelefon_main(String telefon_main) {
        this.telefon_main = telefon_main;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }
}
package com.sncss.haemtravel.Bean;

/**
 * Created by developer1 on 11/3/16.
 * it holds the data on hemizentrum inside the setting screen
 */
public class HeimatzentrumBean {

    private int id;
    private String klinik;
    private String strabe;
    private String plz_ort_code;
    private String plz_ort_main;
    private String telefon_ort_code;
    private String telefon_ort_main;
    private String telefon_ort_code2;
    private String telefon_ort_main2;
    private String kontaktperson_name;
    private String telefon_ort_code3;
    private String telefon_ort_main3;
    private String telefon_ort_code4;
    private String telefon_ort_main4;
    private String kommentar;
    private String email;
    public String centerID;

    public HeimatzentrumBean(){}

    public HeimatzentrumBean(int id, String klinik, String strabe, String plz_ort_code, String plz_ort_main, String telefon_ort_main, String telefon_ort_main2, String email, String kontaktperson_name, String telefon_ort_main3, String telefon_ort_main4, String kommentar, String centerIDq) {
        this.id = id;
        this.klinik = klinik;
        this.strabe = strabe;
        this.plz_ort_code = plz_ort_code;
        this.plz_ort_main = plz_ort_main;
       // this.telefon_ort_code = telefon_ort_code;
        this.telefon_ort_main = telefon_ort_main;
       // this.telefon_ort_code2 = telefon_ort_code2;
        this.telefon_ort_main2 = telefon_ort_main2;
        this.email = email;
        this.kontaktperson_name = kontaktperson_name;
       // this.telefon_ort_code3 = telefon_ort_code3;
        this.telefon_ort_main3 = telefon_ort_main3;
       // this.telefon_ort_code4 = telefon_ort_code4;
        this.telefon_ort_main4 = telefon_ort_main4;
        this.kommentar = kommentar;
        this.centerID = centerIDq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKlinik() {
        return klinik;
    }

    public void setKlinik(String klinik) {
        this.klinik = klinik;
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

    public String getTelefon_ort_code() {
        return telefon_ort_code;
    }

    public void setTelefon_ort_code(String telefon_ort_code) {
        this.telefon_ort_code = telefon_ort_code;
    }

    public String getTelefon_ort_main() {
        return telefon_ort_main;
    }

    public void setTelefon_ort_main(String telefon_ort_main) {
        this.telefon_ort_main = telefon_ort_main;
    }

    public String getTelefon_ort_code2() {
        return telefon_ort_code2;
    }

    public void setTelefon_ort_code2(String telefon_ort_code2) {
        this.telefon_ort_code2 = telefon_ort_code2;
    }

    public String getTelefon_ort_main2() {
        return telefon_ort_main2;
    }

    public void setTelefon_ort_main2(String telefon_ort_main2) {
        this.telefon_ort_main2 = telefon_ort_main2;
    }

    public String getKontaktperson_name() {
        return kontaktperson_name;
    }

    public void setKontaktperson_name(String kontaktperson_name) {
        this.kontaktperson_name = kontaktperson_name;
    }

    public String getTelefon_ort_code3() {
        return telefon_ort_code3;
    }

    public void setTelefon_ort_code3(String telefon_ort_code3) {
        this.telefon_ort_code3 = telefon_ort_code3;
    }

    public String getTelefon_ort_main3() {
        return telefon_ort_main3;
    }

    public void setTelefon_ort_main3(String telefon_ort_main3) {
        this.telefon_ort_main3 = telefon_ort_main3;
    }

    public String getTelefon_ort_code4() {
        return telefon_ort_code4;
    }

    public void setTelefon_ort_code4(String telefon_ort_code4) {
        this.telefon_ort_code4 = telefon_ort_code4;
    }

    public String getTelefon_ort_main4() {
        return telefon_ort_main4;
    }

    public void setTelefon_ort_main4(String telefon_ort_main4) {
        this.telefon_ort_main4 = telefon_ort_main4;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getCenterID() {
        return centerID;
    }

    public void setCenterID(String centerID) {
        this.centerID = centerID;
    }
}

package com.sncss.haemtravel.Bean;

/**
 * Created by developer1 on 11/3/16.
 * Bean class for the data on Setting page tab 2 that is DiagnoseBehandlung
 */
public class DiagnoseBehandlungGetSet {

    private int id;
    private String diagnose;
    private String restaktivitat;
    private String therapieart;
    private String meinfaktor;
    private String dosierung;
    private String dosierungsfrequen;

    public DiagnoseBehandlungGetSet(){}

    public DiagnoseBehandlungGetSet(int id, String diagnose, String restaktivitat, String therapieart, String meinfaktor, String dosierung, String dosierungsfrequen) {
        this.id = id;
        this.diagnose = diagnose;
        this.restaktivitat = restaktivitat;
        this.therapieart = therapieart;
        this.meinfaktor = meinfaktor;
        this.dosierung = dosierung;
        this.dosierungsfrequen = dosierungsfrequen;
    }

    @Override
    public String toString() {
        return "DiagnoseBehandlungGetSet{" +
                "id=" + id +
                ", diagnose='" + diagnose + '\'' +
                ", restaktivitat='" + restaktivitat + '\'' +
                ", therapieart='" + therapieart + '\'' +
                ", meinfaktor='" + meinfaktor + '\'' +
                ", dosierung='" + dosierung + '\'' +
                ", dosierungsfrequen='" + dosierungsfrequen + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getRestaktivitat() {
        return restaktivitat;
    }

    public void setRestaktivitat(String restaktivitat) {
        this.restaktivitat = restaktivitat;
    }

    public String getTherapieart() {
        return therapieart;
    }

    public void setTherapieart(String therapieart) {
        this.therapieart = therapieart;
    }

    public String getMeinfaktor() {
        return meinfaktor;
    }

    public void setMeinfaktor(String meinfaktor) {
        this.meinfaktor = meinfaktor;
    }

    public String getDosierung() {
        return dosierung;
    }

    public void setDosierung(String dosierung) {
        this.dosierung = dosierung;
    }

    public String getDosierungsfrequen() {
        return dosierungsfrequen;
    }

    public void setDosierungsfrequen(String dosierungsfrequen) {
        this.dosierungsfrequen = dosierungsfrequen;
    }
}

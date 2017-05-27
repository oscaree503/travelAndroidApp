package com.sncss.haemtravel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sncss.haemtravel.Bean.CenterListBean;
import com.sncss.haemtravel.Bean.DoctorBean;
import com.sncss.haemtravel.Bean.EmailBean;
import com.sncss.haemtravel.Bean.HeimatzentrumBean;
import com.sncss.haemtravel.Bean.MeinFavorites;
import com.sncss.haemtravel.Bean.NameBean;
import com.sncss.haemtravel.Bean.NotfallBean;
import com.sncss.haemtravel.Bean.TelefoneBean;
import com.sncss.haemtravel.adapter.DoctorListAdapter;
import com.sncss.haemtravel.adapter.EmailAdapter;
import com.sncss.haemtravel.adapter.NotfallAdapter;
import com.sncss.haemtravel.adapter.TelefoneAdapter;
import com.sncss.haemtravel.database.SettingDatabase;
import com.sncss.haemtravel.utility.LocationPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer1 on 25/2/16.
 */
public class CityPlaceDetail extends Activity implements View.OnClickListener {

    private static TextView fullAddress, adressanderung_mitteilen, toemail, centerName, back_img, home_img, title_topbar_txt, em_call, left1, center1, left2, center2, left3, center3, telefon_number, emergency_num;
    private static ImageView go_to_map, telefone, emergencyNumber, emailcenter;
    private static LinearLayout addContact;
    String country_name, center_name, ISO, lat="0", logii="0";
    String center_ID;
    String jsonString,address,name;
    JSONArray jsonArrayCenter;
    private static final String MyEmergency = "HaemTravel";
    private static final String Number = "numberKey";
    String TagJson = "Haemophiliezentren";
    JSONObject jsonObject;
    Bundle bundle;
    private static String number;
    private static SharedPreferences shared;
    Map<String, String> detail = new HashMap<String, String>();
    ArrayList<DoctorBean> docB;
    ListView doctorList,telfone_list,notfall_list,email_list;
    Location location;
    ArrayList<TelefoneBean> tB;
    ArrayList<NotfallBean> tBN;
    ArrayList<EmailBean> tBE;
    ArrayList<NameBean> tNB;
    double latitude_cur=0, longitude_cur =0;
    MeinFavorites meinFavoritesGetSet;
    SettingDatabase settingDatabase;
    private int HDC;
    File laender, myDir;
    InputStream is;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_place_details);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        settingDatabase = new SettingDatabase(this);
        shared = getSharedPreferences(MyEmergency, MODE_PRIVATE);
        bundle= getIntent().getExtras();
        try {
            country_name = bundle.getString("country_name");
            center_name = bundle.getString("center_name");
            ISO = bundle.getString("ISO");
            center_ID = bundle.getString("center_ID");
            lat = bundle.getString("lat");
            logii = bundle.getString("longii");

        } catch (Exception e) {
            country_name = "India";
            center_name = "Indore";
            ISO = "IN";

        }
        myDir = getFilesDir();
        laender = new File(myDir+"/Haemophiliezentren.json");
        byte[] buffer;
        try {
            if (laender.exists()) {
                is = new FileInputStream(laender);
            } else {
                is = getAssets().open("Haemophiliezentren.json");
            }
            int size = is.available();

            buffer = new byte[size];

            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }

        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        centerName = (TextView) findViewById(R.id.centerName);
        adressanderung_mitteilen = (TextView) findViewById(R.id.adressanderung_mitteilen);
        doctorList = (ListView) findViewById(R.id.doctorList);
        em_call = (TextView) findViewById(R.id.em_call);
        left1 = (TextView) findViewById(R.id.left1);
        telefon_number = (TextView) findViewById(R.id.telefon_number);
        telefon_number = (TextView) findViewById(R.id.telefon_number);
        go_to_map = (ImageView) findViewById(R.id.go_to_map);
        telfone_list = (ListView)findViewById(R.id.telefone_list);
        notfall_list = (ListView)findViewById(R.id.notfall_list);
        email_list = (ListView)findViewById(R.id.email_list);
        telefone = (ImageView) findViewById(R.id.telefone);
        addContact = (LinearLayout) findViewById(R.id.addContact);
        tNB = new ArrayList<NameBean>();
        left2 = (TextView) findViewById(R.id.left2);
        left3 = (TextView) findViewById(R.id.left3);
        fullAddress = (TextView) findViewById(R.id.fullAddres);
        centerName.setText(center_name);
        detail = getCenterDetail();
        tB = new ArrayList<TelefoneBean>();
        tB = getTelefoneNumber();
        TelefoneAdapter tA = new TelefoneAdapter( CityPlaceDetail.this, tB, CityPlaceDetail.this);
        telfone_list.setAdapter(tA);
        tBN = new ArrayList<NotfallBean>();
        tBN= getNotfallNumber();
        NotfallAdapter tAN = new NotfallAdapter(CityPlaceDetail.this, tBN, CityPlaceDetail.this);
        notfall_list.setAdapter(tAN);
        tBE = new ArrayList<EmailBean>();
        tBE= getEmail();
        EmailAdapter tAE = new EmailAdapter(CityPlaceDetail.this, tBE);
        email_list.setAdapter(tAE);
        fullAddress.setText(detail.get("Strasse") + "\n" + detail.get("PLZ") + " " + detail.get("Stadt") + "\n" + detail.get("Land"));
        if(tBN.size()!=0) {
            for (int k = 0; k < tBN.size(); k++) {
                if (tBN.get(k).getNotfalls().matches("")) {
                    left2.setTextColor(getResources().getColor(R.color.grey));
                    left2.setEnabled(false);
                }else if(shared.getString(Number, "").equals(tBN.get(k).getNotfalls())){
                    left2.setText(R.string.fa_check_circle_o);
                }
            }
        }
        if(tB.size()!=0) {
            for (int k = 0; k < tBN.size(); k++) {
                if (tBN.get(k).getNotfalls().matches("")) {
                    left2.setTextColor(getResources().getColor(R.color.grey));
                    left2.setEnabled(false);
                }else if(shared.getString(Number, "").equals(tBN.get(k).getNotfalls())){
                    left2.setText(R.string.fa_check_circle_o);
                }
            }
        }
        if (settingDatabase.checkDataMeinfavoriten(center_ID)) {
            left3.setText(R.string.fa_check_circle_o);
        }
        if (settingDatabase.checkHeimatzentrum(center_ID)) {
            left1.setText(R.string.fa_check_circle_o);
        }
        docB = getDocList();
        DoctorListAdapter myDocAdapter = new DoctorListAdapter(this, docB, center_ID, country_name, center_name, ISO);
        doctorList.setAdapter(myDocAdapter);
        go_to_map.setOnClickListener(this);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        left1.setOnClickListener(this);
        left2.setOnClickListener(this);
        left3.setOnClickListener(this);
        addContact.setOnClickListener(this);
        adressanderung_mitteilen.setOnClickListener(this);
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(country_name);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        em_call.setTypeface(font);
        left1.setTypeface(font);
        left2.setTypeface(font);
        left3.setTypeface(font);
        int totalHeight = 0;
        for (int size = 0; size < myDocAdapter.getCount(); size++) {
            View listItem = myDocAdapter.getView(size, null, doctorList);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = doctorList.getLayoutParams();
        params.height = totalHeight + (doctorList.getDividerHeight() * (myDocAdapter.getCount() - 1));
        doctorList.setLayoutParams(params);

        int totalHeightT = 0;
        for (int size = 0; size < tA.getCount(); size++) {
            View listItem = tA.getView(size, null, telfone_list);
            listItem.measure(0, 0);
            totalHeightT += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams paramsT = telfone_list.getLayoutParams();
        paramsT.height = totalHeightT + (telfone_list.getDividerHeight()*(tA.getCount() - 1));
        telfone_list.setLayoutParams(paramsT);
        int totalHeightN = 0;
        for (int size = 0; size < tAN.getCount(); size++) {
            View listItem = tAN.getView(size, null, notfall_list);
            listItem.measure(0, 0);
            totalHeightN += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams paramsN = notfall_list.getLayoutParams();
        paramsN.height = totalHeightN + (notfall_list.getDividerHeight() * (tAN.getCount() - 1));
        notfall_list.setLayoutParams(paramsN);
        int totalHeightE = 0;

        for (int size = 0; size < tAE.getCount(); size++) {
            View listItem = tAE.getView(size, null, email_list);
            listItem.measure(0, 0);
            totalHeightE += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams paramsE = email_list.getLayoutParams();
        paramsE.height = totalHeightE + (email_list.getDividerHeight() * (tAE.getCount() - 1));
        email_list.setLayoutParams(paramsE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CityPlaceDetail.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", title_topbar_txt.getText().toString());
                in.putExtra("ISO", ISO);
                in.putExtra("center_name", center_name);
                in.putExtra("center_ID", center_ID);
                in.putExtra("longii",logii);
                in.putExtra("lat",lat);

                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CityPlaceDetail.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", title_topbar_txt.getText().toString());
                in.putExtra("ISO", ISO);
                in.putExtra("center_name", center_name);
                in.putExtra("center_ID", center_ID);
                in.putExtra("longii",logii);
                in.putExtra("lat",lat);
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CityPlaceDetail.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", title_topbar_txt.getText().toString());
                in.putExtra("ISO", ISO);
                in.putExtra("center_name", center_name);
                in.putExtra("center_ID", center_ID);
                in.putExtra("longii",logii);
                in.putExtra("lat",lat);
                startActivity(in);
            }
        });

    }

    /**
     * crets the doctor list if have multiple doctor
     * @return
     */
    private ArrayList<DoctorBean> getDocList() {
        ArrayList<DoctorBean> dcBean = new ArrayList<DoctorBean>();
        int c = settingDatabase.getDocCount();
        if (c != 0) {
            dcBean = settingDatabase.getAllDoc(center_ID);
        } else {
            dcBean = null;
        }
        return dcBean;
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                String input = title_topbar_txt.getText().toString();
                Intent intent = new Intent(CityPlaceDetail.this, OnClickCountryName.class);
                intent.putExtra("country_name", input);
                intent.putExtra("ISO", ISO);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(CityPlaceDetail.this, MainActivity.class));
                finish();
                break;
            case R.id.left1:
                checkUncheck(left1);
                break;
            case R.id.left2:
                checkUncheck(left2);
                break;
            case R.id.left3:
                checkUncheck(left3);
                break;
            case R.id.go_to_map:
                goMap();
                break;
            case R.id.addContact:
                Intent addNewContact = new Intent(CityPlaceDetail.this, AddNewContact.class);
                addNewContact.putExtra("center_name", center_name);
                addNewContact.putExtra("country_name", country_name);
                addNewContact.putExtra("center_ID", center_ID);
                addNewContact.putExtra("ISO", ISO);
                addNewContact.putExtra("do_contact", "add_Contact");
                addNewContact.putExtra("longii",logii);
                addNewContact.putExtra("lat",lat);
                startActivity(addNewContact);
                finish();
                break;
            case R.id.adressanderung_mitteilen:
                Intent am = new Intent(CityPlaceDetail.this, AddressMitte.class);
                am.putExtra("center_name", center_name);
                am.putExtra("strabe",detail.get("Strasse"));
                am.putExtra("PLZ",detail.get("PLZ"));
                am.putExtra("Ort",detail.get("Stadt"));
                am.putExtra("nameS",tNB);
                am.putExtra("telefone",tB);
                am.putExtra("telefon_not",tBN);
                am.putExtra("E-mail",tBE);
                am.putExtra("country_name", country_name);
                am.putExtra("center_ID", center_ID);
                am.putExtra("ISO", ISO);
                am.putExtra("longii",logii);
                am.putExtra("lat", lat);
                startActivity(am);
                finish();
                break;
         }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String input = title_topbar_txt.getText().toString();
        Intent intent = new Intent(CityPlaceDetail.this, OnClickCountryName.class);
        intent.putExtra("country_name", country_name);
        intent.putExtra("ISO", ISO);
        startActivity(intent);
        finish();
    }

    /**
     * setting the check and uncheck mark on the bottom circle
     * @param view
     */
    private void checkUncheck(TextView view) {
        String s = view.getText().toString();
        if (s.matches(getResources().getString(R.string.fa_circle_o))) {
            switch(view.getId()){
                case R.id.left1:
                    left1Dialog();
                    break;
                case R.id.left2:
                    left2Dialog();
                    break;
                case R.id.left3:
                    left3Dialog();
                    break;
            }
        } else {
            switch(view.getId()) {
                case R.id.left1:
                    unsetSettingHemiData();
                    break;
                case R.id.left2:
                    unsetNotFallShared();
                    break;
                case R.id.left3:
                    unsetManiefavratoine();
                    break;
            }
        }
    }

    /**
     *retrving the detail os the center from the json
     * @return
     */
    private Map<String, String> getCenterDetail() {
        Map<String, String> ct_D = new HashMap<String, String>();
        ArrayList<CenterListBean> center = new ArrayList<CenterListBean>();
        try {
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagJson);
            jsonArrayCenter = jsonObject.optJSONArray("Adresse");
            if (jsonArrayCenter != null) {
                for (int i = 0; i < jsonArrayCenter.length(); i++) {
                    JSONObject objectCountry = jsonArrayCenter.getJSONObject(i);
                    if (objectCountry.optString("ID").matches(center_ID)) {
                        JSONArray namei = objectCountry.optJSONArray("Name");
                        if (namei == null) {
                            NameBean n = new NameBean();
                            address = null;
                            name = objectCountry.optString("Name");
                            address = name;
                            n.setName(name);
                            tNB.add(n);
                            ct_D.put("center_name", name.toString());
                        } else {
                            address = null;
                            for (int a = 0; a < namei.length(); a++) {
                                NameBean n = new NameBean();
                                n.setName(namei.getString(a));
                                tNB.add(n);
                                if (a == 0) {
                                    name = namei.getString(a);
                                } else {
                                    name += "\n"+namei.getString(a);
                                }
                            }
                            ct_D.put("center_name",name);
                            center_name=name;
                            centerName.setText(name);
                        }
                        ct_D.put("Strasse", objectCountry.optString("Strasse"));
                        ct_D.put("Stadt", objectCountry.optString("Stadt"));
                        ct_D.put("PLZ", objectCountry.optString("PLZ"));
                        ct_D.put("Land", objectCountry.optString("Land"));
                    }
                }

            }
        } catch (Exception e) {
                    e.printStackTrace();
        }
        return ct_D;
    }
    public String getData(JSONObject j, String s) {
        JSONArray namei = j.optJSONArray(s);
        if (namei == null) {
            String name = j.optString(s);
            return name.toString();
        } else {
            try {
                return namei.getString(0);
            } catch (JSONException e) {
            }
        }
        return null;
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CityPlaceDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.sncss.haemtravel/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CityPlaceDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.sncss.haemtravel/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * shows the dialog on first left click of the circles
     */
    public void left1Dialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_white);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        TextView add = (TextView) dialog.findViewById(R.id.add);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        text.setText("Adresse wird als Heimat-Hämophiliezentrum in My HaemTravel übernommen. Vorhandene Daten werden überschrieben.");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long i = saveSettingHemiiData();
                dialog.cancel();
                left1.setText(R.string.fa_check_circle_o);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * seeting the center inside the seeting activitu to hemizentrum
     * @return
     */
    public long saveSettingHemiiData(){

        long i = 0;
        String name = "";
        String number = "";
        String email = "";
        try {

           ArrayList<DoctorBean> ald = settingDatabase.getFirstDoc(center_ID);
            if(ald.size()!=0) {
                name = (ald.get(0).getDocName());
                number = (ald.get(0).getTelefone());
                email = (ald.get(0).getEmail());
            }
            String t="";
            for(int j=0;j<tB.size();j++) {
                t += tB.get(j).getTelefone()+"\n";

            }
            String tN = "";
            for(int j=0;j<tBN.size();j++) {
                tN += tBN.get(j).getNotfalls()+"\n";

            }
            String e= "";
            for(int j=0;j<tBE.size();j++) {
                e += tBE.get(j).getEamil()+"\n";
            }
            HDC = settingDatabase.getHDCount();
            if(HDC != 0) {
                Log.e("Update", "yes");
              i =  settingDatabase.updateHeimatzentrum(new HeimatzentrumBean(1,
                        centerName.getText().toString(),
                        detail.get("Strasse"),
                        detail.get("PLZ"),
                        detail.get("Stadt"),
                        t,
                        tN,
                        e, name, number, email,"",center_ID));
            }
            else{
                HeimatzentrumBean heimatzentrumBean = new HeimatzentrumBean(1,
                        centerName.getText().toString(),
                        detail.get("Strasse"),
                        detail.get("PLZ"),
                        detail.get("Stadt"),
                        t,
                        tN,
                        e, name, number, email, "",center_ID);
                i = settingDatabase.heimatzentrum_add(heimatzentrumBean);

            }

        }catch (Exception ex){

            ex.printStackTrace();

        }
        return i;
    }
    /**
     * removing the data from hemizetrum inside setting activity
     */
    public void unsetSettingHemiData(){
        HDC = settingDatabase.getHDCount();
        if(HDC != 0) {
            settingDatabase.deletHeimatzentrum(center_ID);
            left1.setText(R.string.fa_circle_o);
        }
    }
    /**
     * shows the dialog on  second circle
     */
    public void left2Dialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_white);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        TextView add = (TextView) dialog.findViewById(R.id.add);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        text.setText("Telefonnummer wird als Notfallrufnummer eingerichtet. Vorhandene Daten werden überschrieben.");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                setNotfallShared();
                left2.setText(R.string.fa_check_circle_o);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
    /**
     * code to unset the notfall number
     */
    private void unsetNotFallShared() {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(Number, null);
        editor.commit();
        left2.setText(R.string.fa_circle_o);
    }
    /**
     * code to set the notfall number
     */
    private void setNotfallShared() {
        number = (shared.getString(Number, ""));
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(Number, tBN.get(0).getNotfalls());
        editor.commit();
    }
    /**
     * function to perfrom the activity on the third bottom circle
     */
    public void left3Dialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_white);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        TextView add = (TextView) dialog.findViewById(R.id.add);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        text.setText("Adresse wird zu den Favoriten hinzugefügt.");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                saveToMeinfavoriten();
                left3.setText(R.string.fa_check_circle_o);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
    /**
     * saves the data to meinfavoriten
     */
    private void saveToMeinfavoriten() {
        meinFavoritesGetSet = new MeinFavorites(0,center_ID,bundle.getString("center_name"),ISO,country_name);
        settingDatabase.insert_meinfavoriten(meinFavoritesGetSet);
    }
    /**
     * deletes or remove  the data to meinfavoriten
     */
    private void unsetManiefavratoine(){
        settingDatabase.delet_row_meinfavoriten(center_ID);
        left3.setText(R.string.fa_circle_o);

    }
    /**
     *function to check the permission to access location and moving to map
     */
    public void goMap(){
        boolean gps_enabled = false;
        boolean network_enabled = false;
        Location gps_loc=null,net_loc=null, finalLoc;
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            boolean checkLoc = LocationPermission.checkPermission(getBaseContext());
            if (!checkLoc) {
                LocationPermission.requestPermission(CityPlaceDetail.this);

            } else {
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latitude_cur = location.getLatitude();
                longitude_cur = location.getLongitude();
                callMap();
            }
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                net_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.getAccuracy() >= net_loc.getAccuracy()){
                    finalLoc = gps_loc;
                latitude_cur = gps_loc.getLatitude();
                longitude_cur  = gps_loc.getLongitude();
                callMap();
                }
                else {
                    finalLoc = net_loc;
                    latitude_cur = gps_loc.getLatitude();
                    longitude_cur  = gps_loc.getLongitude();
                    callMap();
                }

            } else {
                if (gps_loc != null) {
                    finalLoc = gps_loc;
                    latitude_cur = gps_loc.getLatitude();
                    longitude_cur  = gps_loc.getLongitude();
                    callMap();
                } else if (net_loc != null) {
                    finalLoc = net_loc;
                    latitude_cur = net_loc.getLatitude();
                    longitude_cur  = net_loc.getLongitude();
                    callMap();
                }
            }

        }
    }

    /**
     *function to call map intent
     */
    public void callMap(){
        Intent intent1;
        if(lat.matches("0") && logii.matches("0")){
            String cn = detail.get("center_name").replaceAll("\n", "+");
            String dname = detail.get("Strasse").replaceAll(" ", "+");
            String std = detail.get("Stadt").replaceAll(" ", "+");
            intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="
                    + latitude_cur + "," + longitude_cur + "&daddr=" + cn.replaceAll(" ", "+") + "+" + dname + "+" + std + "+" +country_name));
        }else{
            intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="
                    + latitude_cur + "," + longitude_cur + "&daddr=" + lat+","+logii));
        }
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.addCategory(Intent.CATEGORY_LAUNCHER);
        intent1.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent1);

    }

    /**
     * function to ge the multiple telefoone numbers
     * @return
     */
    public ArrayList<TelefoneBean> getTelefoneNumber(){
        try {
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagJson);
            jsonArrayCenter = jsonObject.optJSONArray("Adresse");
            if (jsonArrayCenter != null) {
                for (int i = 0; i < jsonArrayCenter.length(); i++) {
                    JSONObject objectCountry = jsonArrayCenter.getJSONObject(i);
                    if (objectCountry.optString("ID").matches(center_ID)) {
                        JSONArray namei = objectCountry.optJSONArray("Telefon");
                        if (namei == null) {
                            TelefoneBean tlB = new TelefoneBean();
                            String name = objectCountry.optString("Telefon");
                            tlB.setTelefone(name);
                            tB.add(tlB);
                            return tB;
                        } else {
                              for(int j=0;j<namei.length();j++){
                                  TelefoneBean tlB = new TelefoneBean();
                                  String s = namei.optString(j);
                                  tlB.setTelefone(s);
                                  tB.add(tlB);
                              }
                            return tB;
                        }
                    }
                }
            }
        }catch(Exception e){
        }
        return tB;
    }
    /**
     * function to ge the multiple notfall numbers
     * @return
     */
    public ArrayList<NotfallBean> getNotfallNumber(){
        try {
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagJson);
            jsonArrayCenter = jsonObject.optJSONArray("Adresse");
            if (jsonArrayCenter != null) {
                for (int i = 0; i < jsonArrayCenter.length(); i++) {
                    JSONObject objectCountry = jsonArrayCenter.getJSONObject(i);
                    if (objectCountry.optString("ID").matches(center_ID)) {
                        JSONArray namei = objectCountry.optJSONArray("Telefon_Not");
                        if (namei == null) {
                            NotfallBean tlB = new NotfallBean();
                            String name = objectCountry.optString("Telefon_Not");
                                tlB.setNotfalls(name);
                                tBN.add(tlB);
                                return tBN;

                        } else {
                            for(int j=0;j<namei.length();j++){
                                NotfallBean tlB = new NotfallBean();
                                String s = namei.optString(j);
                                tlB.setNotfalls(s);
                                tBN.add(tlB);
                            }
                            return tBN;
                        }
                    }
                }
            }
        }catch(Exception e){
        }
        return tBN;
    }
    /**
     * function to ge the multiple emails
     * @return
     */
    public ArrayList<EmailBean> getEmail(){
        try {
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagJson);
            jsonArrayCenter = jsonObject.optJSONArray("Adresse");
            if (jsonArrayCenter != null) {
                for (int i = 0; i < jsonArrayCenter.length(); i++) {
                    JSONObject objectCountry = jsonArrayCenter.getJSONObject(i);
                    if (objectCountry.optString("ID").matches(center_ID)) {
                        JSONArray namei = objectCountry.optJSONArray("Email");
                        if (namei == null) {
                            EmailBean tlE = new EmailBean();
                            String name = objectCountry.optString("Email");
                            tlE.setEmail(name);
                            tBE.add(tlE);
                            return tBE;
                        } else {
                            for(int j=0;j<namei.length();j++){
                                EmailBean tlE = new EmailBean();
                                String s = namei.optString(j);
                                tlE.setEmail(s);
                                tBE.add(tlE);
                            }
                            return tBE;
                        }
                    }
                }
            }
        }catch(Exception e){
        }
        return tBE;
    }

}


package com.sncss.haemtravel;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.Country;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.adapter.CountryListArrayAdapter;

import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener {

    private static TextView btn_setting, title_topbar_txt,
            check_icon, earth_icon, plan_icon, hospital_icon, setting_myapp,btn_main_heam_setting,btn_info;
    private static ImageView  btn_em_call_icon;
    private static final String MyEmergency = "HaemTravel";
    private static final String Number = "numberKey";
    private static String number,d;

    private static SharedPreferences shared;
    Date newDate,oldDate;
    File laender, myDir,hemiDE;
    ProgressDialog dialog;
    public int e;
    SharedPreferences.Editor ed;

    JSONObject jsonObject,jsonObjecth;
    InputStream is,hs;
    String jsonString,jsonStringh ;
    String data = "";
    String startingcode = null;
    JSONArray jsonArrayCountry;


    String[] countries = new String[36];
    JSONArray jsonArrayCenter;
    private static final String TagLander = "Laender";
    String TagJson = "Haemophiliezentren";
    private static final String Tag_Sprache  = "Sprache";
    private static final String Tag_Id = "ID";
    private static final String Tag_Land = "Land";
    private static final String Tag_Flag = "Flag";

    ArrayList<Country> items = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_with_scroll_view);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        FooterFrg fg = new FooterFrg();
        shared = getSharedPreferences(MyEmergency, MODE_PRIVATE);
        //date in shared preference
        String myDateS = shared.getString("currTime", "");
        e = shared.getInt("firstInstall",0);
        ed = shared.edit();
        Date now = new Date();
        /** current date */
        Calendar calendar = Calendar.getInstance(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        //logi for seven days data check
        try {
            d = sdf.format(now);
            DateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy",  Locale.US);
            oldDate = format1.parse(d);
            Date myDate = format1.parse(myDateS);
            calendar.setTime(myDate);
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            newDate = calendar.getTime();
           } catch (ParseException e) {
            e.printStackTrace();
        }
        if(oldDate.after(newDate) || oldDate.equals(newDate) || e==0){
            boolean b = shared.getBoolean("Downloaded",false);

            if(!b){
                getFilesFromServer();
                ed.putInt("firstInstall",1);
                ed.putBoolean("Downloaded", true);
            }
        }else{
            ed.putBoolean("Downloaded",false);
            byte[] buffer, bufferh;
            try {


                    is = getAssets().open("laender.json");
                    hs = getAssets().open("Haemophiliezentren.json");
                    String[] f1 = getAssets().list("laender.json");
//                    for (int k = 1; k <= f1.length; k++) {
//                        Log.e("TAG", "" + f1);
//                    }

                int sizeh = hs.available();
                int size = is.available();
                bufferh = new byte[sizeh];
                buffer = new byte[size];
                hs.read(bufferh);
                is.read(buffer);
                is.close();
                hs.close();
                jsonStringh = new String(bufferh, "UTF-8");
                jsonString = new String(buffer, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //JSON Pasring
            try {
                JSONObject json = new JSONObject(jsonString);
                jsonObject = json.getJSONObject(TagLander);
                //for centerlist
                JSONObject jsonh = new JSONObject(jsonStringh);
                jsonObjecth = jsonh.getJSONObject(TagJson);
                jsonArrayCenter = jsonObjecth.optJSONArray("Adresse");
                String sprache = jsonObject.getString(Tag_Sprache).toString();
                char a = 'A';
                char z = 'Z';
                int ia = 0;
                for (char i1 = a; i1 <= z; i1++) {
//                    char ab = i1;
//                    String a1 = String.valueOf(i1);
                    jsonArrayCountry = jsonObject.optJSONArray(String.valueOf(i1));
                    if (jsonArrayCountry!=null) {
                        items.add(new SectionItem((String.valueOf(i1))));
                        startingcode += String.valueOf(i1);
                        for (int i = 0; i < jsonArrayCountry.length(); i++) {
                            JSONObject objectCountry = jsonArrayCountry.getJSONObject(i);
                            //int id1 = Integer.parseInt(objectCountry.optString(Tag_Id).toString());
                            String land1 = objectCountry.optString(Tag_Land).toString();
                            String flag1 = objectCountry.optString(Tag_Flag).toString().toLowerCase();
                            if (flag1.matches("do")) {
                                flag1 = "dor";
                            }
                            String ISO = objectCountry.optString(Tag_Flag).toString();
                            //data += flag1;
                            if (jsonArrayCenter != null) {
                                for (int j = 0; j < jsonArrayCenter.length(); j++) {
                                    JSONObject objectCountryh = jsonArrayCenter.getJSONObject(j);
                                    if (objectCountryh.optString("ISO_3166_2").toString().matches(ISO)) {
                                        Country cnt = new Country(land1, flag1, ISO);
                                        items.add(cnt);
                                        countries[ia] = land1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    ia++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StaticJsonData.countryItems=items;

        }
            ed.commit();
        number = (shared.getString(Number, ""));
        check_icon = (TextView) findViewById(R.id.checkliste_icon);
        earth_icon = (TextView) findViewById(R.id.lander_info_icon);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        plan_icon = (TextView) findViewById(R.id.plane_icon);
        hospital_icon = (TextView) findViewById(R.id.hospital_icon);
        btn_info = (TextView) findViewById(R.id.btn_right_topbar);
        btn_em_call_icon = (ImageView) findViewById(R.id.em_call);
        btn_setting = (TextView) findViewById(R.id.btn_left_topbar);
        setting_myapp = (TextView) findViewById(R.id.setting_myapp);
        btn_main_heam_setting= (TextView) findViewById(R.id.btn_main_heam_setting);
        btn_setting.setOnClickListener(this);
        btn_info.setOnClickListener(this);
        btn_info.setVisibility(View.INVISIBLE);
        btn_setting.setText(R.string.no_text_value);
        title_topbar_txt.setText(Html.fromHtml(getString(R.string.app_name)));
        setting_myapp.setText(R.string.fa_gear);
        btn_main_heam_setting.setText(Html.fromHtml(getString(R.string.my_heam_setting)));
        setting_myapp.setTypeface(font);
        btn_setting.setTypeface(font);
        check_icon.setTypeface(font);
        earth_icon.setTypeface(font);
        plan_icon.setTypeface(font);
        hospital_icon.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });


    }

    public void roundbtn1(View view) {
        String checkListe = "1";
        Intent checkListeIntent = new Intent(MainActivity.this, CheckListe.class);
        checkListeIntent.putExtra("country_name", checkListe);
        startActivity(checkListeIntent);
        finish();
    }

    public void roundbtn2(View view) {
        startActivity(new Intent(MainActivity.this, Landerinformationen.class));
        finish();
    }

    public void roundbtn3(View view) {
        startActivity(new Intent(MainActivity.this, IchPlanEineResie.class));
        finish();
    }

    public void roundbtn4(View view) {
        startActivity(new Intent(MainActivity.this, HamophiliezentrumSuchen.class));
        finish();
    }

    public void setting_haemTravel(View settingView) {
        startActivity(new Intent(MainActivity.this, Setting.class));
        finish();
    }

    public void emergencyCall(View settingView) {
        if (number == null || number.length() == 0) {
            startActivity(new Intent(MainActivity.this,NotFall.class));
            finish();
        } else {
            Intent intenta = new Intent(Intent.ACTION_CALL);
            intenta.setData(Uri.parse("tel:" + number));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intenta);
      }
    }
    public void notfall(View settingView){
        startActivity(new Intent(MainActivity.this,NotFall.class));
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_right_topbar:
                startActivity(new Intent(MainActivity.this, Inform.class));
                finish();
                break;
        }
    }

    /**
     * funntion to fetch the files from ftp server
     */
    public void getFilesFromServer(){
        myDir = getFilesDir();
        laender = new File(myDir+"/laender.json");
        hemiDE = new File(myDir+"/Haemophiliezentren.json");
       dialog = ProgressDialog.show(MainActivity.this, "", "", true);
        try{
                final FTPClient fm = new FTPClient();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //dialog = ProgressDialog.show(MainActivity.this, "", "", true);
                            ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
                            // ARE WE CONNECTED TO THE NET
                            if (conMgr.getActiveNetworkInfo() != null
                                    && conMgr.getActiveNetworkInfo().isAvailable()
                                    && conMgr.getActiveNetworkInfo().isConnected()) {
                                fm.connect("www.crm.de");
                                if (fm.login("oxington", "Oxi-CRM!")) {
                                    fm.enterLocalPassiveMode();
                                    // get details of a file or directory
                                    String remoteFilePath = "laender.json";
                                    // File f = getFileStreamPath("haemtravel/laender.json");
                                    OutputStream outputStream = null;
                                    OutputStream outputStream1 = null;
                                    outputStream = new BufferedOutputStream(new FileOutputStream(laender));
                                    fm.retrieveFile(remoteFilePath, outputStream);
                                    String remoteFilePath1 = "Haemophiliezentren_DE.json";
                                    outputStream1 = new BufferedOutputStream(new FileOutputStream(hemiDE));
                                    fm.retrieveFile(remoteFilePath1,outputStream1);
                                }

                            }else{

                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ed.putString("currTime",d);
                                    ed.commit();
                                    byte[] buffer, bufferh;
                                    try {
                                        if (laender.exists()) {
                                            is = new FileInputStream(laender);
                                            hs = new FileInputStream(hemiDE);
                                        } else {
                                            is = getAssets().open("laender.json");
                                            hs = getAssets().open("Haemophiliezentren.json");
                                            String[] f1 = getAssets().list("laender.json");
//                    for (int k = 1; k <= f1.length; k++) {
//                        Log.e("TAG", "" + f1);
//                    }
                                        }
                                        int sizeh = hs.available();
                                        int size = is.available();
                                        bufferh = new byte[sizeh];
                                        buffer = new byte[size];
                                        hs.read(bufferh);
                                        is.read(buffer);
                                        is.close();
                                        hs.close();
                                        jsonStringh = new String(bufferh, "UTF-8");
                                        jsonString = new String(buffer, "UTF-8");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //JSON Pasring
                                    try {
                                        JSONObject json = new JSONObject(jsonString);
                                        jsonObject = json.getJSONObject(TagLander);
                                        //for centerlist
                                        JSONObject jsonh = new JSONObject(jsonStringh);
                                        jsonObjecth = jsonh.getJSONObject(TagJson);
                                        jsonArrayCenter = jsonObjecth.optJSONArray("Adresse");
                                        String sprache = jsonObject.getString(Tag_Sprache).toString();
                                        char a = 'A';
                                        char z = 'Z';
                                        int ia = 0;
                                        for (char i1 = a; i1 <= z; i1++) {
//                    char ab = i1;
//                    String a1 = String.valueOf(i1);
                                            jsonArrayCountry = jsonObject.optJSONArray(String.valueOf(i1));
                                            if (jsonArrayCountry!=null) {
                                                items.add(new SectionItem((String.valueOf(i1))));
                                                startingcode += String.valueOf(i1);
                                                for (int i = 0; i < jsonArrayCountry.length(); i++) {
                                                    JSONObject objectCountry = jsonArrayCountry.getJSONObject(i);
                                                    //int id1 = Integer.parseInt(objectCountry.optString(Tag_Id).toString());
                                                    String land1 = objectCountry.optString(Tag_Land).toString();
                                                    String flag1 = objectCountry.optString(Tag_Flag).toString().toLowerCase();
                                                    if (flag1.matches("do")) {
                                                        flag1 = "dor";
                                                    }
                                                    String ISO = objectCountry.optString(Tag_Flag).toString();
                                                    //data += flag1;
                                                    if (jsonArrayCenter != null) {
                                                        for (int j = 0; j < jsonArrayCenter.length(); j++) {
                                                            JSONObject objectCountryh = jsonArrayCenter.getJSONObject(j);
                                                            if (objectCountryh.optString("ISO_3166_2").toString().matches(ISO)) {
                                                                Country cnt = new Country(land1, flag1, ISO);
                                                                items.add(cnt);
                                                                countries[ia] = land1;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            ia++;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    dialog.dismiss();
                                    StaticJsonData.countryItems=items;

                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            t.start();

    }catch (Exception e){

    }
//        -----------------------------------------


}

}
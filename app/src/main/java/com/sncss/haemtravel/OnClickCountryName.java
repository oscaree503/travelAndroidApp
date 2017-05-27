package com.sncss.haemtravel;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import com.sncss.haemtravel.Bean.CenterListBean;

import com.sncss.haemtravel.adapter.CenterListAdapter;
import com.sncss.haemtravel.map.GeocodeJSONParser;
import com.sncss.haemtravel.utility.LocationPermission;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.james.mime4j.field.mimeversion.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Created by developer1 on 23/2/16.
 */
public class OnClickCountryName extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    GoogleMap mmap;
    Context context;
    private static TextView back_img, home_img, title_topbar_txt;
    private static ListView city_collection;
    private GoogleMap mMap;
    private CenterListAdapter clientDetailAdapter;
    private ArrayList<CenterListBean> center;
    CenterListBean clb;
    String addresss;
    String jsonString;
    JSONArray jsonArrayCenter;
    String TagJson = "Haemophiliezentren";
    JSONObject jsonObject;
    MarkerOptions markerOptions;
    MapFragment supportMapFragment;
    String ISO;
    LatLng point;
    LatLng latLng;
    GoogleMap googleMap;
    String mapId = null;
    double latee, longii, myLat, myLong;
    ArrayList<Double> late;
    ArrayList<Double> lngi;
    int iden = 0;
    ArrayList<CenterListBean> cl,centerList;
    private static final int PERMISSION_REQ = 0;
    AQuery aQuery;
    ProgressDialog pd;
    HashMap<String,String> mapname;
    String country_name;
    private static final String DISTANCE_KM_POSTFIX = "km";
    private static final String DISTANCE_M_POSTFIX = "m";
    ConnectivityManager conMgr;
    File Hamemo, myDir;
    boolean success;
    InputStream is;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aQuery = new AQuery(this);
        pd = new ProgressDialog(this);
        cl = new ArrayList<CenterListBean>();
        mapname = new HashMap<String,String>();
        setContentView(R.layout.on_click_contry_name);
        Bundle bundle = getIntent().getExtras();
        country_name = bundle.getString("country_name");
        late = new ArrayList<Double>();
        lngi = new ArrayList<Double>();
        ISO = bundle.getString("ISO");
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        title_topbar_txt.setText(country_name);
        city_collection = (ListView) findViewById(R.id.city_collection);

        get_loc();
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        supportMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map1);
        if (!isGooglePlayServicesAvailable()) {
            Toast.makeText(OnClickCountryName.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
        }
        myDir = getFilesDir();
        Hamemo = new File(myDir+"/Haemophiliezentren.json");
        centerListView();
        city_collection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = city_collection.getItemAtPosition(position);
                CenterListBean fullObject = (CenterListBean) o;
                Intent intent = new Intent(OnClickCountryName.this, CityPlaceDetail.class);
                intent.putExtra("center_name", fullObject.getTitle());
                intent.putExtra("country_name", country_name);
                intent.putExtra("ISO", ISO);
                intent.putExtra("center_ID", fullObject.getCenterCode());
                try {
                    intent.putExtra("lat", cl.get(position).getLat());
                    intent.putExtra("longii", cl.get(position).getLogi());
                } catch (Exception e) {
                    intent.putExtra("lat", "0");
                    intent.putExtra("longii", "0");
                }
                startActivity(intent);
                finish();
            }
        });

        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OnClickCountryName.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", country_name);
                in.putExtra("ISO", ISO);
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OnClickCountryName.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", country_name);
                in.putExtra("ISO", ISO);
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OnClickCountryName.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("country_name", country_name);
                in.putExtra("ISO", ISO);
                startActivity(in);
            }
        });
    }

    /**
     * geting the center
     */
    public void centerListView(){
        byte[] buffer;
            try {
                if (Hamemo.exists()) {
                    is = new FileInputStream(Hamemo);
//                    is = getAssets().open("Haemophiliezentren.json");
                }else {
                    is = getAssets().open("Haemophiliezentren.json");
                }
                int size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                jsonString = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        centerList = getCenterList();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                city_collection.setAdapter(new CenterListAdapter(OnClickCountryName.this, centerList));

            }
        });
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
           // get_lat_long(centerList);
            new fetchLatLongFromService(centerList).execute();
        }
    }
    public void showDialouge(){
        if(!pd.isShowing()){
            pd.show();
        }
    }
    public void hideDialouge(){
        if(pd.isShowing()){
            pd.dismiss();
//            city_collection.setAdapter(new CenterListAdapter(this, cl));

        }
    }
    private ArrayList<CenterListBean> getCenterList() {
        ArrayList<CenterListBean> center = new ArrayList<CenterListBean>();
        try {
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagJson);
            jsonArrayCenter = jsonObject.optJSONArray("Adresse");
            if (jsonArrayCenter != null) {
                for (int i = 0; i < jsonArrayCenter.length(); i++) {
                    JSONObject objectCountry = jsonArrayCenter.getJSONObject(i);
                    if (objectCountry.optString("ISO_3166_2").toString().matches(ISO)) {
                        String strasse = objectCountry.optString("Strasse");
                        //String plz=objectCountry.optString("PLZ");
                        String stadt = objectCountry.optString("Stadt");
                        String land = objectCountry.optString("Land");
                        String name="";
                        clb = new CenterListBean();
                        JSONArray namei = objectCountry.optJSONArray("Name");
                        if (namei == null) {
                            addresss = null;
                            name = objectCountry.optString("Name");
                            addresss = name;
                            clb.setTitle(name.toString());
                        } else {
                            addresss = null;
                            name = namei.getString(0);
                            addresss = name;

                        }
                        clb.setStadt(stadt);
                        clb.setTitle(name);
                        clb.setStrasse(strasse);
                        addresss = addresss + " " + strasse + " " + stadt + " " + land;
                        iden = i;
                        clb.setCenteCode(objectCountry.getString("ID"));
                        clb.setAddress(addresss);

                        center.add(clb);


                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return center;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                startActivity(new Intent(OnClickCountryName.this, HamophiliezentrumSuchen.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(OnClickCountryName.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OnClickCountryName.this, HamophiliezentrumSuchen.class));
        finish();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
       // Async Class for lat Long Fetching
    public class fetchLatLongFromService extends AsyncTask<Void, Void, ArrayList<CenterListBean>> {
        ArrayList<CenterListBean> cenb;

        public fetchLatLongFromService(ArrayList<CenterListBean> cenb) {
            super();
            this.cenb = cenb;
            dialog = ProgressDialog.show(OnClickCountryName.this, "", "", true);

            dialog.show();
        }
        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            this.cancel(true);
        }
           //fetching lat and long from the google
        @Override
        protected ArrayList<CenterListBean> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            for(int i=0; i<cenb.size();i++) {
                final int finalI = i;
                  CenterListBean cl1 = new CenterListBean();
                        try {
                            HttpURLConnection conn = null;
                            StringBuilder jsonResults = new StringBuilder();
                            String googleMapUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+cenb.get(i).getAddress().replaceAll(" ", "%20");
                            URL url = new URL(googleMapUrl);
                            conn = (HttpURLConnection) url.openConnection();
                            InputStreamReader in = new InputStreamReader(conn.getInputStream());
                            int read;
                            char[] buff = new char[1024];
                            while ((read = in.read(buff)) != -1)
                            {
                                jsonResults.append(buff, 0, read);
                            }
                            JSONObject jsonObj = new JSONObject(jsonResults.toString());
                            if (!(jsonObj == null)) {
                                googleMap = supportMapFragment.getMap();
                                JSONArray resultJsonArray = jsonObj.getJSONArray("results");
                                // Extract the Place descriptions from the results
                                // resultList = new ArrayList<String>(resultJsonArray.length());
                                JSONObject before_geometry_jsonObj = resultJsonArray.getJSONObject(0);
                                JSONObject geometry_jsonObj = before_geometry_jsonObj.getJSONObject("geometry");
                                JSONObject location_jsonObj = geometry_jsonObj.getJSONObject("location");
                                String lat_helper = location_jsonObj.getString("lat");
                                double lat = Double.valueOf(lat_helper);
                                latee = lat;
                                String lng_helper = location_jsonObj.getString("lng");
                                double lng = Double.valueOf(lng_helper);
                                longii = lng;
                                cl1.setLat(lat_helper);
                                cl1.setLogi(lng_helper);
                                cl1.setTitle(cenb.get(finalI).getTitle());
                                cl1.setStadt(cenb.get(finalI).getStadt());
                                LatLng p1 = new LatLng(myLat, myLong);
                                LatLng p2 = new LatLng(lat, lng);
                                String d = formatDistanceBetween(p1 , p2);
                                //double d =  distance(myLat, myLong, lat, lng);
                                cl1.setDistance(d);
                                cl1.setCenteCode(cenb.get(finalI).getCenterCode());
                                cl.add(cl1);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        city_collection.setAdapter(new CenterListAdapter(OnClickCountryName.this, cl));
                                        point = new LatLng(latee, longii);

                                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                                        Marker m = googleMap.addMarker(new MarkerOptions().position(point));
                                        mapId = m.getId();
                                        m.setTitle(cenb.get(finalI).getTitle());
                                        m.setSnippet(cenb.get(finalI).getStadt());
                                        mapname.put(mapId, cenb.get(finalI).getCenterCode());
                                        googleMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
                                        m.showInfoWindow();
                                        googleMap.setOnInfoWindowClickListener(OnClickCountryName.this);

                                    }
                                });

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            cl1.setLat("0");
                            cl1.setLogi("0");
                            cl1.setTitle(cenb.get(finalI).getTitle());
                            cl1.setStadt(cenb.get(finalI).getStadt());
                            LatLng p1 = new LatLng(myLat, myLong);
                            LatLng p2 = new LatLng(0, 0);
                            String d = formatDistanceBetween(p1 , p2);
                            //double d =  distance(myLat, myLong, lat, lng);
                            cl1.setDistance(d);
                            cl1.setCenteCode(cenb.get(finalI).getCenterCode());
                            cl.add(cl1);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                city_collection.setAdapter(new CenterListAdapter(OnClickCountryName.this, cl));
                            }
                        });

                    }
               /* });
            }*/
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<CenterListBean> cenb) {
            // TODO Auto-generated method stub
            super.onPostExecute(cenb);
            dialog.dismiss();
        }
    }
    private void get_loc() {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            enableLocation();
        } else {

        }

    }

    public void enableLocation()
    {
        buildAlertMessageNoGps();
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,  final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        get_current_loc();

    }

    /**
     * fething the current location of the user to claulate the distance
     */
    public void get_current_loc(){
        boolean gps_enabled = false;
        boolean network_enabled = false;
        Location gps_loc=null,net_loc=null, finalLoc;
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            boolean checkLoc = LocationPermission.checkPermission(getBaseContext());
            if (!checkLoc) {
                LocationPermission.requestPermission(OnClickCountryName.this);


            } else {
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                myLat = location.getLatitude();
                myLong = location.getLongitude();

/*
                Toast.makeText(OnClickCountryName.this,"latitude"+myLat+"longitude"+myLong,Toast.LENGTH_LONG).show();
*/
            }
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                net_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (gps_loc != null && net_loc != null) {

                if (gps_loc.getAccuracy() >= net_loc.getAccuracy()) {
                    finalLoc = gps_loc;
                    myLat = gps_loc.getLatitude();
                    myLong = gps_loc.getLongitude();
                }
                else {
                    finalLoc = net_loc;
                    myLat = gps_loc.getLatitude();
                    myLong = gps_loc.getLongitude();
                }
            } else {

                if (gps_loc != null) {
                    finalLoc = net_loc;
                    myLat = gps_loc.getLatitude();
                    myLong  = gps_loc.getLongitude();
                } else if (net_loc != null) {
                    finalLoc = gps_loc;
                    myLat = net_loc.getLatitude();
                    myLong  = net_loc.getLongitude();
                }
            }

        }

    }

    /**
     * calculatiing the distance between the two points
     * @param point1
     * @param point2
     * @return
     */
    public String formatDistanceBetween(LatLng point1, LatLng point2) {
        if (point1 == null || point2 == null) {
            return null;
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        double distance = Math.round(SphericalUtil.computeDistanceBetween(point1, point2));
        if (distance >= 1000) {
            numberFormat.setMaximumFractionDigits(0);
            numberFormat.setParseIntegerOnly(true);
            return numberFormat.format(distance / 1000).toString() +" "+ DISTANCE_KM_POSTFIX;
        }
        return numberFormat.format(distance) +" "+ DISTANCE_M_POSTFIX;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(OnClickCountryName.this, CityPlaceDetail.class);
        intent.putExtra("country_name", country_name);
        intent.putExtra("center_name",marker.getTitle());
        intent.putExtra("ISO", ISO);
        intent.putExtra("center_ID",mapname.get(marker.getId()));
        try {
            intent.putExtra("lat", String.valueOf(marker.getPosition().latitude));
            intent.putExtra("longii",String.valueOf(marker.getPosition().longitude));
        } catch (Exception e) {
            intent.putExtra("lat", "0");
            intent.putExtra("longii", "0");
        }
        startActivity(intent);
        finish();
    }

    /**
     * inner class to show the information window on map pointers
     */
    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final  View myContentsView;

        MyInfoWindowAdapter(){
            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myContentsView = inflater.inflate(R.layout.custom_infowindow_map, null);
           /* new EasyDialog(OnClickCountryName.this)
                    // .setLayoutResourceId(R.layout.layout_tip_content_horizontal)//layout resource id
                    .setLayout(myContentsView)
                    .setBackgroundColor(OnClickCountryName.this.getColor(R.color.grey))
                    .show();*/
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;

        }
        @Override
        public View getInfoWindow(Marker marker) {
            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.center_name));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.city_name));
            tvSnippet.setText(marker.getSnippet());
            return myContentsView;
        }

    }
}
package com.sncss.haemtravel.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sncss.haemtravel.CityPlaceDetail;
import com.sncss.haemtravel.MainActivity;
import com.sncss.haemtravel.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng myPosition;
    private Location location;
    private LocationManager locationManager;
    private Criteria criteria;
    private String provider;
    private double latitude, longitude;
    private  LatLng latLng;
    private static TextView back_img, home_img,title_topbar_txt;
    private static Bundle bundle;
    private static String back_press;
    private static Intent intent;
    private static Class c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText("Zentrum");

        back_img.setTypeface(font);
        home_img.setTypeface(font);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        location = locationManager.getLastKnownLocation(provider);
        if(location!=null) {
            // Getting latitude of the current location
            latitude = location.getLatitude();

            // Getting longitude of the current location
            longitude = location.getLongitude();
        }
        latLng = new LatLng(latitude, longitude);
        myPosition = new LatLng(latitude, longitude);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));

        Intent intent=getIntent();
        LatLng latLngCustom = intent.getParcelableExtra("location");


        mMap.addMarker(new MarkerOptions().position(latLngCustom).title("Tango HOme ASASasas"));
        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            back_press = bundle.getString("back_press");
            c = Class.forName(back_press);
            Intent i = new Intent(MapsActivity.this, c);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            intent = new Intent(MapsActivity.this, CityPlaceDetail.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
           case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(MapsActivity.this, MainActivity.class));
                finish();
                break;
        }
    }
}

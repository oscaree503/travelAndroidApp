package com.sncss.haemtravel.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.sncss.haemtravel.R;

/**
 * Created by developer1 on 11/3/16.
 */
public class TestLatLong extends Activity {

    double latitude = 24.12;
    double longitude = 29.29;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_lat_long_intent);

        Button button = (Button)findViewById(R.id.send_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestLatLong.this,MapsActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("location", new LatLng(latitude, longitude));
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });

    }
}

package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CenterMapActivity extends Activity {
    TextView title_topbar_txt, back_img, home_img;
    String center_name,country_name,center_ID,ISO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_map);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        Bundle bundle = getIntent().getExtras();
        try {
            country_name = bundle.getString("country_name");
            center_name = bundle.getString("center_name");
            center_ID= bundle.getString("center_ID");
            ISO = bundle.getString("ISO");

        } catch (Exception e) {
            country_name = "India";
            center_name = "Indore";
        }
        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        title_topbar_txt.setText(center_name);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String input = title_topbar_txt.getText().toString();
        Intent intent = new Intent(CenterMapActivity.this, CityPlaceDetail.class);
        intent.putExtra("country_name", country_name);
        intent.putExtra("center_name", center_name);
        intent.putExtra("center_ID", center_ID);
        intent.putExtra("ISO", ISO);

        startActivity(intent);
        finish();
    }
}

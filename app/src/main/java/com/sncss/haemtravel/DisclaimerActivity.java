package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.spec.ECField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DisclaimerActivity extends Activity {

    private static TextView goToHomeActivity;
    private static TextView back_img, home_img,title_topbar_txt,bullet1,bullet2,bullet3;
    private static final String HaemTravelSP = "HaemTravel" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);

        back_img.setText(R.string.no_text_value);
        home_img.setText(R.string.no_text_value);
        title_topbar_txt.setText(Html.fromHtml(getString(R.string.app_name)));

        back_img.setTypeface(font);
        home_img.setTypeface(font);
        final SharedPreferences pref = getSharedPreferences(HaemTravelSP, Context.MODE_PRIVATE);

        goToHomeActivity = (TextView) findViewById(R.id.goToHome);
        /**
         * onclick confirm button the data and the inital values are set from here
         */
        goToHomeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisclaimerActivity.this, MainActivity.class));
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("HaemTravel", true);
                ed.putInt("firstInstall",0);
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

                try {

                    String  d = sdf.format(now);
                    ed.putString("currTime",d);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 8; i++) {
                    String s = "arrow" + i;
                    ed.putBoolean(s, false);
                }
                ed.putBoolean("Downloaded",false);
                ed.commit();
                finish();
            }
        });
        if(pref.getBoolean("HaemTravel",false)){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {

        }

        bullet1 = (TextView)findViewById(R.id.bullet1);
        bullet2 = (TextView)findViewById(R.id.bullet2);
        bullet3 = (TextView)findViewById(R.id.bullet3);
        bullet1.setText(Html.fromHtml(getString(R.string.bullet_head)));
        bullet2.setText(Html.fromHtml(getString(R.string.bullet_head)));
        bullet3.setText(Html.fromHtml(getString(R.string.bullet_head)));
    }
}

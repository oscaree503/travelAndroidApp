package com.sncss.haemtravel;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.frag.Allgemeine;
import com.sncss.haemtravel.frag.Spezielle;


/**
 * Created by developer1 on 29/2/16.
 */
public class Reiseapotheke extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt, btn1, btn2;

    private static Fragment fr;

    private static Intent intent;
    private static String back1 = "1";

    private static String back_press;
    private static Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reiseapotheke);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        bundle = getIntent().getExtras();

        btn1=(TextView)findViewById(R.id.btn1);
        btn2=(TextView)findViewById(R.id.btn2);
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);


        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.reiseapotheke);

        back_img.setTypeface(font);
        home_img.setTypeface(font);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reiseapotheke.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reiseapotheke.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reiseapotheke.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    public void fragReisepotheke(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                fr = new Spezielle();
                sector(btn1);
                unSector(btn2);
                break;
            case R.id.btn2:
                fr = new Allgemeine();
                sector(btn2);
                unSector(btn1);
                break;
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_reisepotheke, fr);
        fragmentTransaction.commit();

    }
    private void unSector(TextView btn2) {
        btn2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        btn2.setTextColor(getResources().getColor(R.color.colorPrimary));}

    private void sector(TextView btn1) {
        btn1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btn1.setTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(Reiseapotheke.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(Reiseapotheke.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(Reiseapotheke.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
}

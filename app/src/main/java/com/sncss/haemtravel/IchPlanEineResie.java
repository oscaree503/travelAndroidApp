package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by developer1 on 26/2/16.
 */
public class IchPlanEineResie extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt,btn_1_ich,btn_2_ich,btn_3_ich,btn_4_ich,btn_5_ich,btn_6_ich,btn_7_ich,btn_8_ich,btn_9_ich;

    private static Intent intent;
    private static String back_press;
    private static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lch_plane_eine_reise);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        bundle = getIntent().getExtras();

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        btn_1_ich = (TextView)findViewById(R.id.btn_1_ich);
        btn_2_ich = (TextView)findViewById(R.id.btn_2_ich);
        btn_3_ich = (TextView)findViewById(R.id.btn_3_ich);
        btn_4_ich = (TextView)findViewById(R.id.btn_4_ich);
        btn_5_ich = (TextView)findViewById(R.id.btn_5_ich);
        btn_6_ich = (TextView)findViewById(R.id.btn_6_ich);
        btn_7_ich = (TextView)findViewById(R.id.btn_7_ich);
        btn_8_ich = (TextView)findViewById(R.id.btn_8_ich);
        btn_9_ich = (TextView)findViewById(R.id.btn_9_ich);


        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.ich_plane_eine_reise);

        back_img.setTypeface(font);
        home_img.setTypeface(font);
        btn_1_ich.setTypeface(font);
        btn_1_ich.setTypeface(font);
        btn_2_ich.setTypeface(font);
        btn_3_ich.setTypeface(font);
        btn_4_ich.setTypeface(font);
        btn_5_ich.setTypeface(font);
        btn_6_ich.setTypeface(font);
        btn_7_ich.setTypeface(font);
        btn_8_ich.setTypeface(font);
        btn_9_ich.setTypeface(font);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IchPlanEineResie.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IchPlanEineResie.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IchPlanEineResie.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                intent = new Intent(IchPlanEineResie.this, MainActivity.class);
                startActivity(intent);
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
            Intent i  = new Intent(IchPlanEineResie.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(IchPlanEineResie.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    public void btn1(View view) {
        intent = new Intent(IchPlanEineResie.this, FachartlicheBeratung.class);
        startActivity(intent);
        finish();
    }
    public void btn2(View view) {
        intent = new Intent(IchPlanEineResie.this, ReisemedizinischeBeratung.class);
        startActivity(intent);
        finish();
    }
    public void btn3(View view) {
        startActivity(new Intent(IchPlanEineResie.this, Reiseapotheke.class));
        finish();
    }
    public void btn4(View view) {
        startActivity(new Intent(IchPlanEineResie.this,VersorgungImReiseland.class));
        finish();
    }

    public void btn5(View view) {
        startActivity(new Intent(IchPlanEineResie.this, Reisedoumente.class));
        finish();
    }
    public void btn6(View view) {
        startActivity(new Intent(IchPlanEineResie.this, Versicherungen.class));

        finish();

    }
    public void btn7(View view) {
        startActivity(new Intent(IchPlanEineResie.this, NutzliheTipps.class));

        finish();
    }
    public void btn8(View view) {
        startActivity(new Intent(IchPlanEineResie.this, ImNotfall.class));
        finish();
    }
    public void btn9(View view) {
        startActivity(new Intent(IchPlanEineResie.this, ReiseMitKindern.class));
        finish();
    }
}

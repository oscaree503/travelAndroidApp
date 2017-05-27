package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by developer1 on 26/2/16.
 */
public class ReisemedizinischeBeratung extends Activity implements View.OnClickListener{
    private static TextView absitingdual, back_img, home_img, title_topbar_txt, circular_arrow_1,circular_arrow_2, weitere_information_zu,bullet, bullet1;
    private static Bundle bundle;
    private static String back_press;
    private static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reisemedizninischeberatung);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        bundle = getIntent().getExtras();
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        circular_arrow_1 = (TextView)findViewById(R.id.circular_arrow_1);
        circular_arrow_2 = (TextView)findViewById(R.id.circular_arrow_2);
        bullet = (TextView)findViewById(R.id.bullet);
        bulletStyle(bullet);
        bullet1 = (TextView)findViewById(R.id.bullet1);
        bulletStyle(bullet1);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        circular_arrow_1.setOnClickListener(this);
        circular_arrow_2.setOnClickListener(this);
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.reisemedizinische_beratung);
        circular_arrow_1.setTypeface(font);
        circular_arrow_2.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReisemedizinischeBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReisemedizinischeBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReisemedizinischeBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
               onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(ReisemedizinischeBeratung.this, MainActivity.class));
                finish();
                break;
            case R.id.circular_arrow_1:
               intent = new Intent(ReisemedizinischeBeratung.this, Reiseapotheke.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.circular_arrow_2:
                intent = new Intent(ReisemedizinischeBeratung.this, Landerinformationen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
        }

    }
    public void bulletStyle(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_string)));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(ReisemedizinischeBeratung.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(ReisemedizinischeBeratung.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
}

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
 * Created by developer1 on 29/2/16.
 */
public class Reisedoumente extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt, arrow_circle_o,personalauswei_s, visum,
                            notfallzertifikat, zollzertifikat,versicherungsunterlagen,aktueller,substitutionstagebuch,
            travel_card,Impfpass,internationaler,schwerbehindertenausweis,notfallnummern,europaische,kran;

    private static String back_press;
    private static Bundle bundle;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reisedokumente);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        bundle = getIntent().getExtras();

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        arrow_circle_o = (TextView)findViewById(R.id.circlar_arrow_btn);
        home_img.setOnClickListener(this);
        back_img.setOnClickListener(this);
        arrow_circle_o.setOnClickListener(this);
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.reisedokumente);
        arrow_circle_o.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        personalauswei_s =(TextView)findViewById(R.id.personalauswei_s);
                visum    =(TextView)findViewById(R.id.visum);
        notfallzertifikat=(TextView)findViewById(R.id.notfallzertifikat);
           zollzertifikat=(TextView)findViewById(R.id.zollzertifikat);
versicherungsunterlagen  =(TextView)findViewById(R.id.versicherungsunterlagen);
               aktueller =(TextView)findViewById(R.id.aktueller);
   substitutionstagebuch =(TextView)findViewById(R.id.substitutionstagebuch);
             travel_card =(TextView)findViewById(R.id.travel_card);
        Impfpass         =(TextView)findViewById(R.id.Impfpass);
        internationaler  =(TextView)findViewById(R.id.internationaler);
schwerbehindertenausweis =(TextView)findViewById(R.id.schwerbehindertenausweis);
        notfallnummern   =(TextView)findViewById(R.id.notfallnummern);
        europaische   =(TextView)findViewById(R.id.europaische);
               personalauswei_s .setText(Html.fromHtml(getString(R.string.personalauswei_s )));
                       visum    .setText(Html.fromHtml(getString(R.string.visum)));
               notfallzertifikat.setText(Html.fromHtml(getString(R.string.notfallzertifikat)));
                  zollzertifikat.setText(Html.fromHtml(getString(R.string.zollzertifikat)));
       versicherungsunterlagen  .setText(Html.fromHtml(getString(R.string.versicherungsunterlagen  )));
                      aktueller .setText(Html.fromHtml(getString(R.string.aktueller )));
          substitutionstagebuch .setText(Html.fromHtml(getString(R.string.substitutionstagebuch )));
                    travel_card .setText(Html.fromHtml(getString(R.string.travel_card )));
               Impfpass         .setText(Html.fromHtml(getString(R.string.Impfpass)));
               internationaler  .setText(Html.fromHtml(getString(R.string.internationaler)));
       schwerbehindertenausweis .setText(Html.fromHtml(getString(R.string.schwerbehindertenausweis )));
               notfallnummern   .setText(Html.fromHtml(getString(R.string. notfallnummern)));
                 europaische.setText(Html.fromHtml(getString(R.string.europaische)));
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reisedoumente.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reisedoumente.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Reisedoumente.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
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
                startActivity(new Intent(Reisedoumente.this, MainActivity.class));
                finish();
                break;
            case R.id.circlar_arrow_btn:
                intent = new Intent(Reisedoumente.this,Versicherungen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
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
            Intent i  = new Intent(Reisedoumente.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(Reisedoumente.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
}

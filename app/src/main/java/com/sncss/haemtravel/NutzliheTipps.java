package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by developer1 on 29/2/16.
 */
public class NutzliheTipps extends Activity implements View.OnClickListener{

    private static TextView fa_hand_o_right_top, back_img, home_img, title_topbar_txt, fa_hand_o_right_1, fa_hand_o_right_2, fa_hand_o_right_3, fa_hand_o_right_4, fa_hand_o_right_5, fa_hand_o_right_6, fa_hand_o_right_7;
    private static Intent commonIntent;
    private static String back_press;
    private static Bundle bundle;

    private static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutzlihe_tipps);
        setContentView(R.layout.nutzlihe_tipps);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);

        bundle = getIntent().getExtras();

        fa_hand_o_right_1 = (TextView)findViewById(R.id.fa_hand_o_right_1);
        fa_hand_o_right_2 = (TextView)findViewById(R.id.fa_hand_o_right_2);
        fa_hand_o_right_3 = (TextView)findViewById(R.id.fa_hand_o_right_3);
        fa_hand_o_right_4 = (TextView)findViewById(R.id.fa_hand_o_right_4);
        fa_hand_o_right_5 = (TextView)findViewById(R.id.fa_hand_o_right_5);
        fa_hand_o_right_6 = (TextView)findViewById(R.id.fa_hand_o_right_6);
        fa_hand_o_right_7 = (TextView)findViewById(R.id.fa_hand_o_right_7);
        fa_hand_o_right_top = (TextView)findViewById(R.id.fa_hand_o_right_top);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);

         fa_hand_o_right_1.setOnClickListener(this);
         fa_hand_o_right_2.setOnClickListener(this);
         fa_hand_o_right_3.setOnClickListener(this);
         fa_hand_o_right_4.setOnClickListener(this);
         fa_hand_o_right_5.setOnClickListener(this);
         fa_hand_o_right_6.setOnClickListener(this);
         fa_hand_o_right_7.setOnClickListener(this);
        fa_hand_o_right_top.setOnClickListener(this);



        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.nutzlihe_tipps);

        back_img.setTypeface(font);
        home_img.setTypeface(font);

        fa_hand_o_right_1.setTypeface(font);
        fa_hand_o_right_2.setTypeface(font);
        fa_hand_o_right_3.setTypeface(font);
        fa_hand_o_right_4.setTypeface(font);
        fa_hand_o_right_5.setTypeface(font);
        fa_hand_o_right_6.setTypeface(font);
        fa_hand_o_right_7.setTypeface(font);
        fa_hand_o_right_top.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NutzliheTipps.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NutzliheTipps.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NutzliheTipps.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                startActivity(new Intent(NutzliheTipps.this, IchPlanEineResie.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(NutzliheTipps.this, MainActivity.class));
                finish();
                break;
            case R.id.fa_hand_o_right_1:
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.dhg.de/"));
                startActivity(i);
                break;
            case R.id.fa_hand_o_right_2:
                Intent i2 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.igh.info/"));
                startActivity(i2);
                break;
            case R.id.fa_hand_o_right_3:
                Intent i3 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.bluter.at/"));
                startActivity(i3);
                break;
            case R.id.fa_hand_o_right_4:
                Intent i4 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.shg.ch/"));
                startActivity(i4);
                break;
            case R.id.fa_hand_o_right_5:
                Intent i5 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.ehc.eu/"));
                startActivity(i5);
                break;
            case R.id.fa_hand_o_right_6:
                Intent i6 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.wfh.org/"));
                startActivity(i6);
                break;
            case R.id.fa_hand_o_right_7:
                Intent i7 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.hemophilia.org/"));
                startActivity(i7);
                break;
            case R.id.fa_hand_o_right_top:
                intent = new Intent(NutzliheTipps.this, ReiseMitKindern.class);
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
            Intent i  = new Intent(NutzliheTipps.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(NutzliheTipps.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
}

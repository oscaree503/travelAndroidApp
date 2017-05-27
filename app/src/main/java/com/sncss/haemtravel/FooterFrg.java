package com.sncss.haemtravel;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.EmailBean;
import com.sncss.haemtravel.Bean.NotfallBean;
import com.sncss.haemtravel.Bean.TelefoneBean;
import com.sncss.haemtravel.frag.FragmentDatenschutz;
import com.sncss.haemtravel.frag.FragmentKontakt;
import com.sncss.haemtravel.frag.FragmentRechtliches;

import java.util.ArrayList;

/**
 * Created by SONY on 23-04-2016.
 */
public class FooterFrg extends Activity implements View.OnClickListener {
    public static TextView kontakt;
    private static TextView datenschutz;
    private static TextView rechtliches,btn_right_topbar,title_topbar_txt,btn_left_topbar;
    private static android.app.Fragment fr;
    public int currentFrag = 1;
    private static String back_press;
    private static Intent intent;

    Bundle i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footer_layout);
        title_topbar_txt= (TextView)findViewById(R.id.title_topbar_txt);
        btn_right_topbar= (TextView)findViewById(R.id.btn_right_topbar);
        btn_left_topbar = (TextView)findViewById(R.id.btn_left_topbar);
        kontakt = (TextView)findViewById(R.id.kontakt);
        datenschutz = (TextView)findViewById(R.id.datenschutz);
        rechtliches = (TextView)findViewById(R.id.rechtliches);
        btn_right_topbar.setVisibility(View.INVISIBLE);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        i = getIntent().getExtras();
        try{
            String s = i.getString("Frag");
            title_topbar_txt.setText(s.substring(0, 1).toUpperCase() + s.substring(1));
            int resID = getResources().getIdentifier(s,"id", getPackageName());
            View addButton = findViewById(resID);
            selectFrag(addButton);
        }catch(Exception e){

        }
        btn_left_topbar.setTypeface(font);
        btn_left_topbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              selectFrag(v);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

    }

    public void selectFrag(View view) {
        switch (view.getId()) {
            case R.id.kontakt:
                fr = new FragmentKontakt();
                title_topbar_txt.setText("Kontakt");
                break;
            case R.id.datenschutz:
                fr = new FragmentDatenschutz();
                title_topbar_txt.setText("Datenschutz");
                break;
            case R.id.rechtliches:
                fr = new FragmentRechtliches();
                title_topbar_txt.setText("Rechtliches");
                break;
        }
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
            fragmentTransaction.replace(R.id.fragment_place_footer, fr);
            fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            back_press = i.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i1 = new Intent(FooterFrg.this, c);
            if(back_press.matches("com.sncss.haemtravel.LanderWebView")){
                String s = i.getString("countryName");
                String s1 = i.getString("countryFlagCode");
                i1.putExtra("countryName",s);
                i1.putExtra("countryFlagCode", s1);
            }
            if(back_press.matches("com.sncss.haemtravel.CityPlaceDetail")){
                i1.putExtra("center_name", i.getString("center_name"));
                i1.putExtra("country_name", i.getString("country_name"));
                i1.putExtra("center_ID", i.getString("center_ID"));
                i1.putExtra("ISO", i.getString("ISO"));
                i1.putExtra("lat",i.getString("lat"));
                i1.putExtra("longii",i.getString("longii"));
            }
            if(back_press.matches("com.sncss.haemtravel.AddressMitte")){
                i1.putExtra("center_name", i.getString("center_name"));
                i1.putExtra("country_name", i.getString("country_name"));
                i1.putExtra("center_ID", i.getString("center_ID"));
                i1.putExtra("ISO", i.getString("ISO"));
                i1.putExtra("lat",i.getString("lat"));
                i1.putExtra("longii",i.getString("longii"));
                i1.putExtra("strabe",i.getString("strabe"));
                i1.putExtra("PLZ",i.getString("PLZ"));
                i1.putExtra("Ort",i.getString("Ort"));
                i1.putExtra("telefone",(ArrayList<TelefoneBean>) getIntent().getSerializableExtra("telefone"));
                i1.putExtra("telefon_not",(ArrayList<NotfallBean>) getIntent().getSerializableExtra("telefon_not"));
                i1.putExtra("E-mail",(ArrayList<EmailBean>) getIntent().getSerializableExtra("E-mail"));
            }
            if(back_press.matches("com.sncss.haemtravel.OnClickCountryName")) {
                i1.putExtra("country_name", i.getString("country_name"));
                i1.putExtra("ISO", i.getString("ISO"));
            }

            startActivity(i1);
            finish();
        } catch (Exception e) {
            intent = new Intent(FooterFrg.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
    @Override
    public void onClick(View v) {

    }
}

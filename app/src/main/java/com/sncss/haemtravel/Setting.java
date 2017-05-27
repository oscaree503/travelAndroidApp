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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.sncss.haemtravel.frag.FragmentOne;
import com.sncss.haemtravel.frag.FragmentThree;
import com.sncss.haemtravel.frag.FragmentTwo;

/**
 * Created by developer1 on 18/2/16.
 */
public class Setting extends Activity implements OnClickListener{
    private static TextView back_img, home_img, title_topbar_txt,  btn1, btn2, btn3, edit_icon;
    private static Fragment fr;
    private static LinearLayout linearLayout,angry_btn,linearLayout2,linearLayout3;
    public int currentFrag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Intent i = getIntent();

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        linearLayout = (LinearLayout)findViewById(R.id.meindaten);

        angry_btn = (LinearLayout)findViewById(R.id.angry_btn);

        btn1=(TextView)findViewById(R.id.btn1);
        btn2=(TextView)findViewById(R.id.btn2);
        btn3=(TextView)findViewById(R.id.btn3);
        try{
            String s = i.getStringExtra("FRAG");
            int resID = getResources().getIdentifier(s,"id", getPackageName());
            View addButton = findViewById(resID);
            selectFrag(addButton);

        }catch(Exception e){

        }
        edit_icon = (TextView)findViewById(R.id.edit_icon);

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(Html.fromHtml(getString(R.string.my_heam_setting)));
        edit_icon.setText(R.string.fa_pencil);

        edit_icon.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Setting.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Setting.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Setting.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    public void selectFrag(View view) {
        switch (view.getId()){
            case R.id.btn1:
                fr = new FragmentOne();
                currentFrag = 1;
                sector(btn1);
                unSector(btn2, btn3);
                break;
            case R.id.btn2:
                fr = new FragmentTwo();
                currentFrag = 2;
                sector(btn2);
                unSector(btn1, btn3);
                break;
            case R.id.btn3:
                fr = new FragmentThree();
                currentFrag = 3;
                sector(btn3);
                unSector(btn1, btn2);
                break;
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }

    private void unSector(TextView btn2, TextView btn3) {
        btn2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        btn3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        btn2.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn3.setTextColor(getResources().getColor(R.color.colorPrimary));}

    private void sector(TextView btn1) {
        btn1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btn1.setTextColor(getResources().getColor(android.R.color.white));}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
                startActivity(new Intent(Setting.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(Setting.this, MainActivity.class));
                finish();
                break;
            case R.id.meindaten:
                if (currentFrag==1){
                    startActivity(new Intent(Setting.this, Andern.class));
                    finish();
                }
                else if(currentFrag==2){
                    startActivity(new Intent(Setting.this, DiagnoseBehandlung.class));
                    finish();
                }
                else if(currentFrag==3){
                    startActivity(new Intent(Setting.this, Heimatzentrunm.class));
                    finish();
                }
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Setting.this, MainActivity.class));
        finish();
    }
}

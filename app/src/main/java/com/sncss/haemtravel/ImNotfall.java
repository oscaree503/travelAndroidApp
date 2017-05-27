package com.sncss.haemtravel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by developer1 on 29/2/16.
 */
public class ImNotfall extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt,ja,level2,textView4,textview5,ja2, nein1, nein2;
    private static LinearLayout imageView1,textView2,imageView3,imageView5,level1;
    private static ImageView triangle7,triangle5,triangle8,triangle6,triangle9,triangle3,triangle4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_notfall);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        triangle5 = (ImageView)findViewById(R.id.triangle5);
        triangle7 = (ImageView)findViewById(R.id.triangle7);
        triangle8 = (ImageView)findViewById(R.id.triangle8);
        triangle9 = (ImageView)findViewById(R.id.triangle9);
        triangle6 = (ImageView)findViewById(R.id.triangle6);
        triangle3 =(ImageView)findViewById(R.id.triangle3);

        imageView1 = (LinearLayout)findViewById(R.id.imageView1);
        textView2  = (LinearLayout)findViewById(R.id.textView2);
        imageView3 = (LinearLayout)findViewById(R.id.imageView3);
        triangle4 = (ImageView)findViewById(R.id.triangle4);
        textView4 = (TextView)findViewById(R.id.textView4);
        nein1 = (TextView)findViewById(R.id.nein1);
        nein2 = (TextView)findViewById(R.id.nein2);
        ja2 = (TextView)findViewById(R.id.ja2);
        level1 = (LinearLayout)findViewById(R.id.level1);
        imageView5 = (LinearLayout)findViewById(R.id.imageView5);
        textview5 = (TextView)findViewById(R.id.textview5);
        level2 = (TextView)findViewById(R.id.level2);
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        ja = (TextView)findViewById(R.id.ja);


        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        ja.setOnClickListener(this);
        nein1.setOnClickListener(this);
        ja2.setOnClickListener(this);
        nein2.setOnClickListener(this);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.im_notafll);

        back_img.setTypeface(font);
        home_img.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ImNotfall.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ImNotfall.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ImNotfall.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
                startActivity(new Intent(ImNotfall.this, IchPlanEineResie.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(ImNotfall.this, MainActivity.class));
                finish();
            case R.id.ja:
                level2.setVisibility(View.VISIBLE);
                level2.setBackground(getResources().getDrawable(R.color.imnofall));
                level2.setText(R.string.behandlung_um);
                imageView3.setVisibility(View.VISIBLE);
                triangle9.setVisibility(View.VISIBLE);
                textview5.setVisibility(View.VISIBLE);
                triangle3.setVisibility(View.VISIBLE);

                triangle5.setVisibility(View.GONE);
                triangle6.setVisibility(View.GONE);
                ja2.setVisibility(View.GONE);
                nein2.setVisibility(View.GONE);
                triangle4.setVisibility(View.INVISIBLE);
                textView4.setVisibility(View.GONE);
                triangle7.setVisibility(View.GONE);
                triangle8.setVisibility(View.GONE);
                break;
            case R.id.nein1:
                level2.setVisibility(View.VISIBLE);
                level2.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                level2.setText(R.string.komme_ich_selbst);
                triangle4.setVisibility(View.VISIBLE);
                triangle5.setVisibility(View.VISIBLE);
                triangle6.setVisibility(View.VISIBLE);
                ja2.setVisibility(View.VISIBLE);
                nein2.setVisibility(View.VISIBLE);

                triangle3.setVisibility(View.INVISIBLE);
                textview5.setVisibility(View.INVISIBLE);
                triangle9.setVisibility(View.INVISIBLE);
                break;
            case R.id.ja2:
                textView4.setBackground(getResources().getDrawable(R.color.imnofall));
                textView4.setText(R.string.behandlung);
                triangle7.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textview5.setVisibility(View.VISIBLE);
                triangle9.setVisibility(View.VISIBLE);

                triangle8.setVisibility(View.INVISIBLE);
                break;
            case R.id.nein2:
                textView4.setBackground(getResources().getDrawable(R.color.imnofall));
                textView4.setText(R.string.hama);
                textView4.setVisibility(View.VISIBLE);
                triangle8.setVisibility(View.VISIBLE);
                triangle9.setVisibility(View.VISIBLE);
                textview5.setVisibility(View.VISIBLE);

                triangle7.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ImNotfall.this, MainActivity.class));
        finish();
    }
}
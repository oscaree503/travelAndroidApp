package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by developer1 on 9/3/16.
 */
public class ReiseMitKindern extends Activity implements View.OnClickListener{
    private static TextView back_img, home_img, title_topbar_txt,arrow_with_circle_1,arrow_with_circle_2,arrow_with_circle_3,
            bullet1,
            bullet2,
            bullet3,
            bullet4,
            bullet5,
            bullet6,
            bullet7,
            bullet8,
            bullet9,
            bullet10,
            bullet11,
            bullet12,
            bullet13,
            bullet14,
            bullet15,
            bullet16,
            bullet17,
            bullet18,
            bullet19,
            bullet20,
            bullet21,
            bullet22,
            bullet23,
            bullet24,
            bullet30,
            bullet31,
            bullet32,
            bullet33,
            bullet36,
            bullet37,
            bullet38,
            bullet39;
    private static Intent intent;
    private static String back_press;
    private static Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resie_mit_kindern);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        arrow_with_circle_1 = (TextView)findViewById(R.id.arrow_with_circle_1);
        arrow_with_circle_2 = (TextView)findViewById(R.id.arrow_with_circle_2);
        arrow_with_circle_3 = (TextView)findViewById(R.id.arrow_with_circle_3);

        home_img.setTypeface(font);
        back_img.setTypeface(font);
        title_topbar_txt.setText(R.string.reisen_mit_kindern);

        arrow_with_circle_1.setTypeface(font);
        arrow_with_circle_2.setTypeface(font);
        arrow_with_circle_3.setTypeface(font);
        arrow_with_circle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ReiseMitKindern.this, ReisemedizinischeBeratung.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
            }
        });
        arrow_with_circle_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ReiseMitKindern.this, Reiseapotheke.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
            }
        });
        arrow_with_circle_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ReiseMitKindern.this, Versicherungen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
            }
        });

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        bullet1 = (TextView)findViewById(R.id.bullet1);
        bulletStyleHead(bullet1);
        bullet2 = (TextView)findViewById(R.id.bullet2);
        bulletStyleHead(bullet2);
        bullet3 = (TextView)findViewById(R.id.bullet3);
        bulletStyleHead(bullet3);
        bullet4 = (TextView)findViewById(R.id.bullet4);
        bulletStyleHead(bullet4);
        bullet5 = (TextView)findViewById(R.id.bullet5);
        bulletStyleHead(bullet5);
        bullet6 = (TextView)findViewById(R.id.bullet6);
        bulletStyleHead(bullet6);
        bullet7 = (TextView)findViewById(R.id.bullet7);
        bulletStyleHead(bullet7);
        bullet8 = (TextView)findViewById(R.id.bullet8);
        bulletStyleHead(bullet8);
        bullet9 = (TextView)findViewById(R.id.bullet9);
        bulletStyleHead(bullet9);
        bullet10 = (TextView)findViewById(R.id.bullet10);
        bulletStyleHead(bullet10);
        bullet11 = (TextView)findViewById(R.id.bullet11);
        bulletStyleHead(bullet11);
        bullet12 = (TextView)findViewById(R.id.bullet12);
        bulletStyleHead(bullet12);
        bullet13 = (TextView)findViewById(R.id.bullet13);
        bulletStyleHead(bullet13);
        bullet14 = (TextView)findViewById(R.id.bullet14);
        bulletStyleHead(bullet14);
        bullet15 = (TextView)findViewById(R.id.bullet15);
        bulletStyleHead(bullet15);
        bullet16 = (TextView)findViewById(R.id.bullet16);
        bulletStyleHead(bullet16);
        bullet17 = (TextView)findViewById(R.id.bullet17);
        bulletStyleHead(bullet17);
        bullet18 = (TextView)findViewById(R.id.bullet18);
        bulletStyleHead(bullet18);
        bullet19 = (TextView)findViewById(R.id.bullet19);
        bulletStyleHead(bullet19);
        bullet20 = (TextView)findViewById(R.id.bullet20);
        bulletStyleHead(bullet20);
        bullet21 = (TextView)findViewById(R.id.bullet21);
        bulletStyleHead(bullet21);
        bullet22 = (TextView)findViewById(R.id.bullet22);
        bulletStyleHead(bullet22);
        bullet23 = (TextView)findViewById(R.id.bullet23);
        bulletStyleHead(bullet23);
        bullet24 = (TextView)findViewById(R.id.bullet24);
        bulletStyleHead(bullet24);
        bullet30 = (TextView)findViewById(R.id.bullet30);
        bulletStyle2(bullet30);
        bullet31 = (TextView)findViewById(R.id.bullet31);
        bulletStyle2(bullet31);
        bullet32 = (TextView)findViewById(R.id.bullet32);
        bulletStyle2(bullet32);
        bullet33 = (TextView)findViewById(R.id.bullet33);
        bulletStyle2(bullet33);
        bullet36 = (TextView)findViewById(R.id.bullet36);
        bulletStyleHead(bullet36);
        bullet37 = (TextView)findViewById(R.id.bullet37);
        bulletStyle2(bullet37);
        bullet38 = (TextView)findViewById(R.id.bullet38);
        bulletStyle2(bullet38);
        bullet39 = (TextView)findViewById(R.id.bullet39);
        bulletStyle2(bullet39);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReiseMitKindern.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReiseMitKindern.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ReiseMitKindern.this, FooterFrg.class);
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
                startActivity(new Intent(ReiseMitKindern.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i = new Intent(ReiseMitKindern.this, c);
            startActivity(i);
            finish();
        } catch (Exception e) {
            intent = new Intent(ReiseMitKindern.this, IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
    public void bulletStyleHead(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_head)));

    }
    public void bulletStyle2(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_string)));

    }
}

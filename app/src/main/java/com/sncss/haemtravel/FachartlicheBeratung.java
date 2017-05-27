package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * Created by developer1 on 24/2/16.
 */
public class FachartlicheBeratung extends Activity implements OnClickListener{
    private static TextView span8move,span4move,span3move,back_img, home_img, title_topbar_txt, right_arrow_circle, add_btn1,add_btn2,add_btn3,add_btn4,add_btn5,add_btn6,add_btn7,add_btn8, blog1,blog2,blog3,blog4,blog5,blog6,blog7,blog8;
    private static LinearLayout span1, span2, span3, span4, span5, span6, span7, span8;
    private static String back_press;
    private static Bundle bundle;
    private static Intent intent;
    private static ScrollView scrlview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fachartliche_beratung);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        bundle = getIntent().getExtras();
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        add_btn1 = (TextView)findViewById(R.id.add_btn1);
        add_btn2 = (TextView)findViewById(R.id.add_btn2);
        add_btn3 = (TextView)findViewById(R.id.add_btn3);
        add_btn4 = (TextView)findViewById(R.id.add_btn4);
        add_btn5 = (TextView)findViewById(R.id.add_btn5);
        add_btn6 = (TextView)findViewById(R.id.add_btn6);
        add_btn7 = (TextView)findViewById(R.id.add_btn7);
        add_btn8 = (TextView)findViewById(R.id.add_btn8);
        span3move = (TextView)findViewById(R.id.span3move);
        span4move = (TextView)findViewById(R.id.span4move);
        span8move = (TextView)findViewById(R.id.span8move);
        right_arrow_circle = (TextView)findViewById(R.id.right_arrow_circle);
        span1 = (LinearLayout)findViewById(R.id.span1);
        span2 = (LinearLayout)findViewById(R.id.span2);
        span3 = (LinearLayout)findViewById(R.id.span3);
        span4 = (LinearLayout)findViewById(R.id.span4);
        span5 = (LinearLayout)findViewById(R.id.span5);
        span6 = (LinearLayout)findViewById(R.id.span6);
        span7 = (LinearLayout)findViewById(R.id.span7);
        span8 = (LinearLayout)findViewById(R.id.span8);
        scrlview = (ScrollView)findViewById(R.id.scrlview);
        blog1 = (TextView)findViewById(R.id.blog1);
        blog2 = (TextView)findViewById(R.id.blog2);
        blog3 = (TextView)findViewById(R.id.blog3);
        blog4 = (TextView)findViewById(R.id.blog4);
        blog5 = (TextView)findViewById(R.id.blog5);
        blog6 = (TextView)findViewById(R.id.blog6);
        blog7 = (TextView)findViewById(R.id.blog7);
        blog8 = (TextView)findViewById(R.id.blog8);
        /**
         * click listern set
         */
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        add_btn1.setOnClickListener(this);
        add_btn2.setOnClickListener(this);
        add_btn3.setOnClickListener(this);
        add_btn4.setOnClickListener(this);
        add_btn5.setOnClickListener(this);
        add_btn6.setOnClickListener(this);
        add_btn7.setOnClickListener(this);
        add_btn8.setOnClickListener(this);
        blog8.setOnClickListener(this);
        right_arrow_circle.setOnClickListener(this);
        span3move.setOnClickListener(this);
        span4move.setOnClickListener(this);
        span8move.setOnClickListener(this);
        /**
         * typeface setting
         */
        add_btn1.setTypeface(font);
        add_btn2.setTypeface(font);
        add_btn3.setTypeface(font);
        add_btn4.setTypeface(font);
        add_btn5.setTypeface(font);
        add_btn6.setTypeface(font);
        add_btn7.setTypeface(font);
        add_btn8.setTypeface(font);
        home_img.setTypeface(font);
        back_img.setTypeface(font);
        right_arrow_circle.setTypeface(font);
        span3move.setTypeface(font);
        span4move.setTypeface(font);
        span8move.setTypeface(font);
        title_topbar_txt.setText(R.string.facharztliche_beratung);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FachartlicheBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FachartlicheBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FachartlicheBeratung.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });



    }

    /**
     * all clicks funtionality defiend below
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(FachartlicheBeratung.this, MainActivity.class));
                finish();
                break;
            case R.id.add_btn1:
                checkUncheck(add_btn1, span1);
                focusOnView(span1);
                break;
            case R.id.add_btn2:
                checkUncheck(add_btn2, span2);
                focusOnView(span2);
                break;
            case R.id.add_btn3:
                checkUncheck(add_btn3, span3);
                focusOnView(span3);
                break;
            case R.id.add_btn4:
                checkUncheck(add_btn4, span4);
                focusOnView(span4);
                break;
            case R.id.add_btn5:
                checkUncheck(add_btn5, span5);
                focusOnView(span5);
                break;
            case R.id.add_btn6:
                checkUncheck(add_btn6, span6);
                focusOnView(span6);
                break;
            case R.id.add_btn7:
                checkUncheck(add_btn7, span7);
                focusOnView(span7);

                break;
            case R.id.add_btn8:
                checkUncheck(add_btn8,span8);
                focusOnView(span8);
                break;
            case R.id.right_arrow_circle:
                intent = new Intent(FachartlicheBeratung.this, Reiseapotheke.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.span3move:
                intent = new Intent(FachartlicheBeratung.this, HamophiliezentrumSuchen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.span4move:
                intent = new Intent(FachartlicheBeratung.this, HamophiliezentrumSuchen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.span8move:
                intent = new Intent(FachartlicheBeratung.this, Reiseapotheke.class);
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
            Intent i  = new Intent(FachartlicheBeratung.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(FachartlicheBeratung.this, IchPlanEineResie.class);
            startActivity(intent);
            finish();

        }
    }

    /**
     * auto scrol functionality
     * @param lx
     */
    private final void focusOnView(final LinearLayout lx){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrlview.scrollTo(0, lx.getBottom());
            }
        });
    }

    /**
     * plus icon click and mius icon click setting
     * @param view
     * @param span
     */
    private void checkUncheck(TextView view, LinearLayout span) {
        String s= view.getText().toString();
        if(s.matches(getResources().getString(R.string.fa_plus_square))){
            view.setText(R.string.fa_minus_square);
            span.setVisibility(View.VISIBLE);
        }
        else {
            view.setText(R.string.fa_plus_square);
            span.setVisibility(View.GONE);
        }
    }
}

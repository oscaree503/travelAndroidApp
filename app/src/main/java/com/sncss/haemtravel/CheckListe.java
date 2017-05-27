package com.sncss.haemtravel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/** Created by developer1 on 18/2/16.
* Checkliste Activity
*/

public class CheckListe extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt,
            left1, center1, right1, left2, center2, right2, left3, center3, right3,
            left4, center4, right4, left5, center5, right5, left6, center6, right6,
            left7, center7, right7, left8, center8, right8;

    private static Intent intent;
    private static Bundle bundle;
    private static String back_press;
    private static ImageView sun, cloudi,bird,tree,chairs,ball1,star_fish,ball2;
    SharedPreferences pref;
    SharedPreferences.Editor ed;
    TextView[] et = new TextView[9];
    ImageView[] im = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklite);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
/*
        bundle = getIntent().getExtras();
*/
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.check_list);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        pref = getSharedPreferences("HaemTravel", Context.MODE_PRIVATE);
        for(int i=1;i<=8;i++) {
            String s = "arrow"+i;
            try {
                if(pref.getBoolean(s,true)){
                    String s1= "left"+i;
                    et[i]= (TextView) findViewById(getResources().getIdentifier(s1, "id", getApplicationContext().getPackageName()));
                    et[i].setText(R.string.fa_check_circle_o);
                    String m1= "im"+i;
                    im[i]= (ImageView) findViewById(getResources().getIdentifier(m1, "id", getApplicationContext().getPackageName()));
                    if(i==2){
                        im[i].setBackgroundResource(R.drawable.imagethree);
                    }
                    im[i].setVisibility(View.VISIBLE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ed = pref.edit();
        left1   = (TextView)findViewById(R.id.left1  );
        center1 = (TextView)findViewById(R.id.center1);
        right1  = (TextView)findViewById(R.id.right1 );
        left2   = (TextView)findViewById(R.id.left2  );
        center2 = (TextView)findViewById(R.id.center2);
        right2  = (TextView)findViewById(R.id.right2 );
        left3   = (TextView)findViewById(R.id.left3  );
        center3 = (TextView)findViewById(R.id.center3);
        right3  = (TextView)findViewById(R.id.right3 );
        left4   = (TextView)findViewById(R.id.left4  );
        center4 = (TextView)findViewById(R.id.center4);
        right4  = (TextView)findViewById(R.id.right4 );
        left5   = (TextView)findViewById(R.id.left5  );
        center5 = (TextView)findViewById(R.id.center5);
        right5  = (TextView)findViewById(R.id.right5 );
        left6   = (TextView)findViewById(R.id.left6  );
        center6 = (TextView)findViewById(R.id.center6);
        right6  = (TextView)findViewById(R.id.right6 );
        left7   = (TextView)findViewById(R.id.left7  );
        center7 = (TextView)findViewById(R.id.center7);
        right7  = (TextView)findViewById(R.id.right7 );
        left8   = (TextView)findViewById(R.id.left8  );
        center8 = (TextView)findViewById(R.id.center8);
        right8  = (TextView)findViewById(R.id.right8 );

        sun = (ImageView)findViewById(R.id.im1);
        cloudi = (ImageView)findViewById(R.id.im2);
        bird = (ImageView)findViewById(R.id.im3);
        tree = (ImageView)findViewById(R.id.im4);
        chairs = (ImageView)findViewById(R.id.im5);
        ball1 = (ImageView)findViewById(R.id.im6);
        star_fish = (ImageView)findViewById(R.id.im7);
        ball2 = (ImageView)findViewById(R.id.im8);

        left1.setTypeface(font);
        left2.setTypeface(font);
        left3.setTypeface(font);
        left4.setTypeface(font);
        left5.setTypeface(font);
        left6.setTypeface(font);
        left7.setTypeface(font);
        left8.setTypeface(font);
        right1.setTypeface(font);
        right2.setTypeface(font);
        right3.setTypeface(font);
        right4.setTypeface(font);
        right5.setTypeface(font);
        right6.setTypeface(font);
        right7.setTypeface(font);
        right8.setTypeface(font);
/**
 * Onclick funtion on the right arrows
 */
        right1.setOnClickListener(this);
        right2.setOnClickListener(this);
        right3.setOnClickListener(this);
        right4.setOnClickListener(this);
        right5.setOnClickListener(this);
        right6.setOnClickListener(this);
        right7.setOnClickListener(this);
        right8.setOnClickListener(this);
        left1.setOnClickListener(this);
        left2.setOnClickListener(this);
        left3.setOnClickListener(this);
        left4.setOnClickListener(this);
        left5.setOnClickListener(this);
        left6.setOnClickListener(this);
        left7.setOnClickListener(this);
        left8.setOnClickListener(this);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CheckListe.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CheckListe.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CheckListe.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

    }


    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(CheckListe.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(CheckListe.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(CheckListe.this, MainActivity.class));
                finish();
                break;
            /**
             * Left button click functions start here
             */
            case R.id.left1:
                checkUncheck(left1, sun);
                break;
            case R.id.left2:
                if(left2.getText().toString().matches(getResources().getString(R.string.fa_circle_o))){
                   try{
                        cloudi.setBackgroundResource(R.drawable.imagethree);
                       }catch (OutOfMemoryError  e){
                       }
                }
                checkUncheck(left2,cloudi);
                break;
            case R.id.left3:
                checkUncheck(left3,bird);
                /*if(left3.getText().toString().matches(getResources().getString(R.string.fa_check_circle_o))){
                    bird.setImageResource(R.drawable.imagefour);
                }*/
                break;
            case R.id.left4:
                checkUncheck(left4, tree);
                break;
            case R.id.left5:
                checkUncheck(left5,chairs);
                break;
            case R.id.left6:
                checkUncheck(left6, ball1);
                break;
            case R.id.left7:
                checkUncheck(left7, star_fish);
                break;
            case R.id.left8:
                checkUncheck(left8,ball2);
                break;
            /**
             * right button check uncheck functions defins here
             */
            case R.id.right1:

                intent = new Intent(CheckListe.this, FachartlicheBeratung.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right2:

                intent = new Intent(CheckListe.this, ReisemedizinischeBeratung.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right3:

                intent = new Intent(CheckListe.this,ReisemedizinischeBeratung.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right4:

                intent = new Intent(CheckListe.this,Reiseapotheke.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right5:

                intent = new Intent(CheckListe.this,ImNotfall.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right6:

                intent = new Intent(CheckListe.this,HamophiliezentrumSuchen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right7:

                intent = new Intent(CheckListe.this,Landerinformationen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case R.id.right8:

                intent = new Intent(CheckListe.this,Reisedoumente.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;

        }


    }

    /**
     * function to perfrom activity on check and uncheck of the list
     * @param view
     * @param imageView
     */
    private void checkUncheck(TextView view, ImageView imageView) {
        String s= view.getText().toString();
        if(s.matches(getResources().getString(R.string.fa_circle_o))){
            view.setText(R.string.fa_check_circle_o);
            imageView.setVisibility(View.VISIBLE);
            setCheck(view);
        }
        else {
            view.setText(R.string.fa_circle_o);
            imageView.setVisibility(View.GONE);
            unSetCheck(view);
            ed.commit();

        }
    }

    /**
     * use to remeber the checks done by the user
     * @param v
     */
    public void setCheck(View v){
        switch (v.getId()){
            case R.id.left1:
                ed.putBoolean("arrow1",true);
                ed.commit();
                break;
            case R.id.left2:
                ed.putBoolean("arrow2",true);
                ed.commit();
                break;
            case R.id.left3:
                ed.putBoolean("arrow3",true);
                ed.commit();
                break;
            case R.id.left4:
                ed.putBoolean("arrow4",true);
                ed.commit();
                break;
            case R.id.left5:
                ed.putBoolean("arrow5",true);
                ed.commit();
                break;
            case R.id.left6:
                ed.putBoolean("arrow6",true);
                ed.commit();
                break;
            case R.id.left7:
                ed.putBoolean("arrow7",true);
                ed.commit();
                break;
            case R.id.left8:
                ed.putBoolean("arrow8",true);
                ed.commit();
                break;
        }

    }

    /**
     * Unset the check and remember the user action
     * @param v
     */
    public void unSetCheck(View v){
        switch (v.getId()){
            case R.id.left1:
                ed.putBoolean("arrow1",false);
                break;
            case R.id.left2:
                ed.putBoolean("arrow2",false);
                break;
            case R.id.left3:
                ed.putBoolean("arrow3",false);
                break;
            case R.id.left4:
                ed.putBoolean("arrow4",false);
                break;
            case R.id.left5:
                ed.putBoolean("arrow5",false);
                break;
            case R.id.left6:
                ed.putBoolean("arrow6",false);
                break;
            case R.id.left7:
                ed.putBoolean("arrow7",false);
                break;
            case R.id.left8:
                ed.putBoolean("arrow8",false);
                break;
        }

    }
}

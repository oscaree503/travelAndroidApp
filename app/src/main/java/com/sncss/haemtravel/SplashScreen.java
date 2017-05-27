package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends Activity {
    private static TextView back_img, home_img, title_topbar_txt;
    private static int SPLASH_TIME_OUT =5500;
    private static ImageView movingImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Animation move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sliding1);
        (movingImg = (ImageView)findViewById(R.id.movingImg)).startAnimation(move);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupWindowAnimations();
            }
        }, SPLASH_TIME_OUT);

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        back_img.setText(R.string.no_text_value);
        home_img.setText(R.string.no_text_value);
        title_topbar_txt.setText(Html.fromHtml(getString(R.string.app_name)));
        back_img.setTypeface(font);
        home_img.setTypeface(font);
    }

    private void setupWindowAnimations() {
        startActivity(new Intent(SplashScreen.this, DisclaimerActivity.class));
        finish();
    }
}

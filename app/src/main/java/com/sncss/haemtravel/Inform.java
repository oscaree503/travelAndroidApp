package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by developer1 on 18/2/16.
 */
public class Inform extends Activity implements OnClickListener {
    private static TextView  back_img, home_img, title_topbar_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.nutzungsbedingungen);

        back_img.setTypeface(font);
        home_img.setTypeface(font);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Inform.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Inform.this, MainActivity.class));
        finish();
    }
}

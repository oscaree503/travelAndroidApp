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
 * Created by developer1 on 22/2/16.
 */
public class  NotFall extends Activity implements View.OnClickListener{

    private static TextView left_icon, top_title_text,right_icon;
    private static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notfall_screen);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        right_icon = (TextView)findViewById(R.id.btn_right_topbar);
        right_icon.setOnClickListener(this);
        left_icon = (TextView)findViewById(R.id.btn_left_topbar);
        left_icon.setOnClickListener(this);
        top_title_text = (TextView)findViewById(R.id.title_topbar_txt);

        right_icon.setText(R.string.fa_home);
        left_icon.setText(R.string.fa_angle_left);
        top_title_text.setText(Html.fromHtml(getString(R.string.app_name)));

        right_icon.setTypeface(font);
        left_icon.setTypeface(font);
        top_title_text.setText(R.string.notfallrufnummer);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFall.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFall.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFall.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(NotFall.this, MainActivity.class));
        finish();
    }

    /**
     *
     * @param view
     */
    public void notfallrunummerEingebenTelefon(View view){
        intent = new Intent(NotFall.this, NotFallTelefon.class);
        intent.putExtra("back_press", getIntent().getComponent().getClassName());
        startActivity(intent);
        finish();
    }

    /**
     *
     * @param view
     */
    public void hamophiliezentrum_suchen(View view){
        intent = new Intent(NotFall.this, HamophiliezentrumSuchen.class);
        intent.putExtra("back_press", getIntent().getComponent().getClassName());
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NotFall.this, MainActivity.class));
        finish();
    }
}

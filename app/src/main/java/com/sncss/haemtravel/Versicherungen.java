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
 * Created by developer1 on 29/2/16.
 */
public class Versicherungen extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt,
            versicherungen_screen_content,
            kranke,
            privatere,
            ruck,
            reise,
            beilang,
            haft,
            unfal,
            haus,
            tipp_versicherungen,bullet2;
    private static String back_press;
    private static Bundle bundle;

    private static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.versicherungen);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        bundle = getIntent().getExtras();

        versicherungen_screen_content = (TextView)findViewById(R.id.versicherungen_screen_content);
        versicherungen_screen_content.setText(Html.fromHtml(getString(R.string.versicherungen_screen_content)));

        kranke = (TextView)findViewById(R.id.kranke);
        kranke.setText(Html.fromHtml(getString(R.string.kranke)));

        privatere = (TextView)findViewById(R.id.privatere);
        privatere.setText(Html.fromHtml(getString(R.string.privatere)));

        ruck = (TextView)findViewById(R.id.ruck);
        ruck.setText(Html.fromHtml(getString(R.string.ruck)));

        reise = (TextView)findViewById(R.id.reise);
        reise.setText(Html.fromHtml(getString(R.string.reise)));

        beilang = (TextView)findViewById(R.id.beilang);
        beilang.setText(Html.fromHtml(getString(R.string.beilang)));

        haft = (TextView)findViewById(R.id.haft);
        haft.setText(Html.fromHtml(getString(R.string.haft)));

        unfal = (TextView)findViewById(R.id.unfal);
        unfal.setText(Html.fromHtml(getString(R.string.unfal)));

        haus = (TextView)findViewById(R.id.haus);
        haus.setText(Html.fromHtml(getString(R.string.haus)));

        tipp_versicherungen = (TextView)findViewById(R.id.tipp_versicherungen);
        tipp_versicherungen.setText(Html.fromHtml(getString(R.string.tipp_versicherungen)));

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        bullet2 = (TextView)findViewById(R.id.bullet1);
        bullet2.setText(Html.fromHtml(getString(R.string.bullet_head)));

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.versicherungen);

        back_img.setTypeface(font);
        home_img.setTypeface(font);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Versicherungen.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Versicherungen.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Versicherungen.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
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
                startActivity(new Intent(Versicherungen.this, MainActivity.class));
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
            Intent i = new Intent(Versicherungen.this, c);
            startActivity(i);
            finish();
        } catch (Exception e) {
            intent = new Intent(Versicherungen.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }
}

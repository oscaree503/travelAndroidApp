package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by developer1 on 29/2/16.
 */
public class VersorgungImReiseland extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt,arrow_with_circle_1, checken_s, mach_ie_a, weitere_information_zu,
            arrow_with_circle_2,fa_hand_o_right_1,fa_hand_o_right_2,mach_ie, nehmen, machen_s, notieren_s,
            bullet1,
            bullet2,
            bullet3,
            bullet4,
            bullet5,
            bullet6,
            bullet7;

    private static String back_press;
    private static Bundle bundle;

    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.versorgung_im_reiseland);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        bundle = getIntent().getExtras();

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);

        arrow_with_circle_1 = (TextView)findViewById(R.id.arrow_with_circle_1);
        arrow_with_circle_2 = (TextView)findViewById(R.id.arrow_with_circle_2);
        fa_hand_o_right_1 = (TextView)findViewById(R.id.fa_hand_o_right_1);
        fa_hand_o_right_2 = (TextView)findViewById(R.id.fa_hand_o_right_2);

        bullet1 = (TextView)findViewById(R.id. bullet1);
        bulletStyle(bullet1);
        bullet2 = (TextView)findViewById(R.id. bullet2);
        bulletStyle(bullet2);
        bullet3 = (TextView)findViewById(R.id. bullet3);
        bulletStyle(bullet3);
        bullet4 = (TextView)findViewById(R.id. bullet4);
        bulletStyle(bullet4);
        bullet5 = (TextView)findViewById(R.id. bullet5);
        bulletStyle(bullet5);
        bullet6 = (TextView)findViewById(R.id. bullet6);
        bulletStyle(bullet6);
        /*bullet7 = (TextView)findViewById(R.id. bullet7);
        bulletStyle(bullet7); deleted*/

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);


        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.versorgung_im_reiseland);

        fa_hand_o_right_1.setOnClickListener(this);
        fa_hand_o_right_2.setOnClickListener(this);
        arrow_with_circle_1.setOnClickListener(this);
        arrow_with_circle_2.setOnClickListener(this);

        home_img.setTypeface(font);
        back_img.setTypeface(font);
        arrow_with_circle_1.setTypeface(font);
        arrow_with_circle_2.setTypeface(font);
        fa_hand_o_right_1.setTypeface(font);
        fa_hand_o_right_2.setTypeface(font);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(VersorgungImReiseland.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(VersorgungImReiseland.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(VersorgungImReiseland.this, FooterFrg.class);
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
                startActivity(new Intent(VersorgungImReiseland.this, IchPlanEineResie.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(VersorgungImReiseland.this, MainActivity.class));
                finish();
                break;
            case R.id.arrow_with_circle_1:
                intent = new Intent(VersorgungImReiseland.this, HamophiliezentrumSuchen.class);
                intent.putExtra("back_press", getIntent().getComponent().getClassName());
                startActivity(intent);
                finish();
                break;
            case  R.id.fa_hand_o_right_1:
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.auswaertiges-amt.de/DE/Laenderinformationen/LaenderReiseinformationen_node.html/"));
                startActivity(i1);
                break;
            case R.id.fa_hand_o_right_2:
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.auswaertiges-amt.de/DE/Laenderinformationen/03-WebseitenAV/Uebersicht_node.html/"));
                startActivity(i2);
                break;
            case R.id.arrow_with_circle_2:
                intent = new Intent(VersorgungImReiseland.this, Landerinformationen.class);
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
            Intent i  = new Intent(VersorgungImReiseland.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(VersorgungImReiseland.this,IchPlanEineResie.class);
            startActivity(intent);
            finish();
        }
    }

    public void bulletStyle(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_head)));

    }
}

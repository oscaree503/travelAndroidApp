package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by developer1 on 22/2/16.
 */
public class NotFallTelefon extends Activity implements View.OnClickListener{
    private static TextView left_icon, top_title_text,right_icon, save_icon_telefon;
    private static EditText code_edt, number_edt;

    private static final String MyEmergency = "HaemTravel" ;
    private static final String Number = "numberKey", MainNumber="mainNumber";
    private static SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notfall_telefon);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        sharedpreferences = getSharedPreferences(MyEmergency, Context.MODE_PRIVATE);
        right_icon = (TextView)findViewById(R.id.btn_right_topbar);
        left_icon = (TextView)findViewById(R.id.btn_left_topbar);
        top_title_text = (TextView)findViewById(R.id.title_topbar_txt);
        save_icon_telefon = (TextView)findViewById(R.id.save_btn);

        right_icon.setText(R.string.no_text_value);
        left_icon.setText(R.string.no_text_value);
        save_icon_telefon.setText(R.string.fa_floppy_o);
        top_title_text.setText(R.string.notfallrufnummer);

        right_icon.setTypeface(font);
        left_icon.setTypeface(font);
        save_icon_telefon.setTypeface(font);

        number_edt = (EditText)findViewById(R.id.number_edt);

        save_icon_telefon.setOnClickListener(this);
        String num = sharedpreferences.getString(Number,"");
        if(!num.matches("")){
            number_edt.setText(num);
        }
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFallTelefon.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFallTelefon.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NotFallTelefon.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

    }

    /**
     * cancel the action
     * @param view
     */
    public void abbrechen(View view){
        startActivity(new Intent(NotFallTelefon.this, MainActivity.class));
        finish();
    }

    /**
     * saing the notfall number
     * @param view
     */
    public void save_btn_telefon(View view){
        startActivity(new Intent(NotFallTelefon.this, MainActivity.class));
        String number = number_edt.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Number, number);
        editor.putString(MainNumber,number);
        editor.commit();
        //Toast.makeText(NotFallTelefon.this,"Thanks"+code+"Success"+number, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}

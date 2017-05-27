package com.sncss.haemtravel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.MeineDatenGetSet;
import com.sncss.haemtravel.database.MeineDaten;
import com.sncss.haemtravel.database.SettingDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by developer1 on 20/2/16.
 */
public class Andern extends Activity implements View.OnClickListener{

    private static TextView back_img, home_img, title_topbar_txt, save_btn,geburtstag,edit_tf;
    private static Intent intent;
    private static Bundle bundle;
    private static String back_press;
    private static String name_sdb, strabe_sdb,plz_ort_code_sdb, plz_ort_main_sdb,telefon_code_sdb, telefon_main_sdb, email_sdb, geburtstag_sdb;
    private static EditText name, strabe, plz_ort_code, plz_ort_main, telefon_code, telefon_main, eMail;
    SettingDatabase meineDatendb;
    List<MeineDatenGetSet> meineDatenGetSets;
    private int MDC;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andern);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        meineDatendb = new SettingDatabase(this);

        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        save_btn = (TextView) findViewById(R.id.save_btn);
        edit_tf = (TextView) findViewById(R.id.edit_tf);
        name = (EditText) findViewById(R.id.name);
        strabe = (EditText) findViewById(R.id.strabe);
        plz_ort_code = (EditText) findViewById(R.id.plz_ort_code);
        plz_ort_main = (EditText) findViewById(R.id.plz_ort_number);
        telefon_code = (EditText) findViewById(R.id.telefon_code);
        telefon_main = (EditText) findViewById(R.id.telefon_number);
        eMail = (EditText) findViewById(R.id.e_mail);
        geburtstag = (TextView) findViewById(R.id.geburtstag);

        back_img.setText(R.string.no_text_value);
        home_img.setText(R.string.no_text_value);
        title_topbar_txt.setText(R.string.setting);
        edit_tf.setTypeface(font);
        save_btn.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        MDC = meineDatendb.getMDCount();
        try {

            if (MDC != 0) {
                meineDatenGetSets = meineDatendb.getAllDetails_MD();

                for (int i = 0; i < meineDatenGetSets.size(); i++) {
                    name_sdb = (meineDatenGetSets.get(i).getName());
                    strabe_sdb = (meineDatenGetSets.get(i).getStrabe());
                    plz_ort_code_sdb = (meineDatenGetSets.get(i).getPlz_ort_code());
                    plz_ort_main_sdb = (meineDatenGetSets.get(i).getPlz_ort_main());
                    telefon_code_sdb = (meineDatenGetSets.get(i).getTelefon_code());
                    telefon_main_sdb = (meineDatenGetSets.get(i).getTelefon_main());
                    email_sdb = (meineDatenGetSets.get(i).getE_mail());
                    geburtstag_sdb = (meineDatenGetSets.get(i).getGeburtstag());
                }

                name.setText(name_sdb);
                strabe.setText(strabe_sdb);
                plz_ort_code.setText(plz_ort_code_sdb);
                plz_ort_main.setText(plz_ort_main_sdb);
                telefon_code.setText(telefon_code_sdb);
                telefon_main.setText(telefon_main_sdb);
                eMail.setText(email_sdb);
                geburtstag.setText(geburtstag_sdb);

            }
        }
        catch (Exception e)
        {

        }

        setDateTimeField();
        save_btn.setOnClickListener(this);
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Andern.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Andern.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Andern.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(Andern.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(Andern.this, Setting.class);
            startActivity(intent);
            finish();
        }
    }

    public void abbrechen(View view){
        intent = new Intent(Andern.this, Setting.class);
        intent.putExtra("back_press", getIntent().getComponent().getClassName());
        startActivity(intent);
        finish();
    }

    public void saveData(){
        try{
            meineDatendb.updateMeinDaten(new MeineDatenGetSet(1,
                    name.getText().toString(),
                    strabe.getText().toString(),
                    plz_ort_code.getText().toString(),
                    plz_ort_main.getText().toString(),
                    telefon_code.getText().toString(),
                    telefon_main.getText().toString(),
                    eMail.getText().toString(),
                    geburtstag.getText().toString()));
            intent = new Intent(Andern.this, Setting.class);
            startActivity(intent);
            finish();

        }catch (Exception ex){
            ex.printStackTrace();
            intent = new Intent(Andern.this, Setting.class);
            intent.putExtra("back_press", getIntent().getComponent().getClassName());
            startActivity(intent);
            finish();
            Toast.makeText(Andern.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void addData()
    {
        try {

            MeineDatenGetSet meineDatenGetSet = new MeineDatenGetSet(
                    1,
                    name.getText().toString(),
                    strabe.getText().toString(),
                    plz_ort_code.getText().toString(),
                    plz_ort_main.getText().toString(),
                    telefon_code.getText().toString(),
                    telefon_main.getText().toString(),
                    eMail.getText().toString(),
                    geburtstag.getText().toString());
           long i = meineDatendb.meineDaten_add(meineDatenGetSet);
            if (i != 0) {
                intent = new Intent(Andern.this, Setting.class);
                startActivity(intent);
                finish();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            intent = new Intent(Andern.this, Setting.class);
            intent.putExtra("back_press", getIntent().getComponent().getClassName());
            startActivity(intent);
            finish();
            Toast.makeText(Andern.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
        }
    }


    private void setDateTimeField() {
        geburtstag.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                geburtstag.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == geburtstag) {
            fromDatePickerDialog.show();
        }
        if (view == save_btn)
        {

            try{
                if (MDC != 0)
                {
                    saveData();

                }
                else {
                    addData();

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

}

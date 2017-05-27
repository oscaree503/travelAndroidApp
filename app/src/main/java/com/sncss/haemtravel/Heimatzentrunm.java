package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.HeimatzentrumBean;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.List;

/**
 * Created by developer1 on 2/3/16.
 */
public class Heimatzentrunm extends Activity implements View.OnClickListener{
    private static TextView back_img, home_img, title_topbar_txt, save_btn;
    private static String
            klink_sdb,
            strabe_sdb,
            plz_ort_code_sdb,
            plz_ort_number_sdb,
            telefon_code_sdb,
            telefon_number_sdb,
            telefon_ort_code2_sdb,
            telefon_ort_number2_sdb,
            kontaktperson_name_sdb,
            kontaktperson_telefon_code_sdb,
            kontaktperson_telefon_number_sdb,
            kontaktperson_telefon_code2_sdb,
            kontaktperson_telefon_number2_sdb,
            kommentar_sdb, centerID;
    private static EditText
            klink,
            strabe,
            plz_ort_code,
            plz_ort_number,
            telefon_code,
            telefon_number,
            telefon_ort_code2,
            telefon_ort_number2,
            kontaktperson_name,
            kontaktperson_telefon_code,
            kontaktperson_telefon_number,
            kontaktperson_telefon_code2,
            kontaktperson_telefon_number2,
            kommentar, email;
    private static SharedPreferences sharedpreferences;
    private  String emailnew;
    private  String Number = "numberKey";
    private  String MyEmergency = "HeamTravel" ;

    SettingDatabase settingDatabase;
    List<HeimatzentrumBean> heimatzentrumBeansList;

    private int HDC;

    int test = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heimatzentrunm);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        settingDatabase = new SettingDatabase(this);
        sharedpreferences = getSharedPreferences(MyEmergency, Context.MODE_PRIVATE);
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        save_btn = (TextView)findViewById(R.id.save_btn);
        email = (EditText)findViewById(R.id.email);
        klink = (EditText)findViewById(R.id.klink);
        strabe = (EditText)findViewById(R.id.strabe);
        plz_ort_code = (EditText)findViewById(R.id.plz_ort_code);
        plz_ort_number = (EditText)findViewById(R.id.plz_ort_number);
        telefon_code = (EditText)findViewById(R.id.telefon_code);
        telefon_number = (EditText)findViewById(R.id.telefon_number);
        telefon_ort_number2 = (EditText)findViewById(R.id.telefon_ort_number2);
        kontaktperson_name = (EditText)findViewById(R.id.kontaktperson_name);
        kontaktperson_telefon_number = (EditText)findViewById(R.id.kontaktperson_telefon_number);
        kontaktperson_telefon_number2 = (EditText)findViewById(R.id.kontaktperson_telefon_number2);
        kommentar = (EditText)findViewById(R.id.kommentar);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        back_img.setText(R.string.no_text_value);
        home_img.setText(R.string.no_text_value);
        title_topbar_txt.setText(R.string.setting);
        save_btn.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        HDC = settingDatabase.getHDCount();
        try {
            heimatzentrumBeansList = settingDatabase.getAllDetails_H();
            if (heimatzentrumBeansList.size() != 0) {
                for (int i = 0; i < heimatzentrumBeansList.size(); i++) {
                    klink_sdb = (heimatzentrumBeansList.get(i).getKlinik());
                    strabe_sdb = heimatzentrumBeansList.get(i).getStrabe();
                    plz_ort_code_sdb = heimatzentrumBeansList.get(i).getPlz_ort_code();
                    plz_ort_number_sdb = heimatzentrumBeansList.get(i).getPlz_ort_main();
                    telefon_number_sdb = heimatzentrumBeansList.get(i).getTelefon_ort_main();
                    telefon_ort_number2_sdb = heimatzentrumBeansList.get(i).getTelefon_ort_main2();
                    emailnew = heimatzentrumBeansList.get(i).getEmail();
                    kontaktperson_name_sdb = heimatzentrumBeansList.get(i).getKontaktperson_name();
                    kontaktperson_telefon_number_sdb = heimatzentrumBeansList.get(i).getTelefon_ort_main3();
                    kontaktperson_telefon_number2_sdb = heimatzentrumBeansList.get(i).getTelefon_ort_main4();
                    kommentar_sdb = heimatzentrumBeansList.get(i).getKommentar();
                    centerID = heimatzentrumBeansList.get(i).getCenterID();
                }
                klink.setText(klink_sdb);
                strabe.setText(strabe_sdb);
                plz_ort_code.setText(plz_ort_code_sdb);
                plz_ort_number.setText(plz_ort_number_sdb);
                telefon_number.setText(telefon_number_sdb);
                telefon_ort_number2.setText(telefon_ort_number2_sdb);
                kontaktperson_name.setText(kontaktperson_name_sdb);
                kontaktperson_telefon_number.setText(kontaktperson_telefon_number_sdb);
                kontaktperson_telefon_number2.setText(kontaktperson_telefon_number2_sdb);
                kommentar.setText(kommentar_sdb);
                email.setText(emailnew);
            }
        }
        catch (Exception e)
        {
        }
        try{
            String eml = sharedpreferences.getString(emailnew,"");
            if(!eml.matches("")){
                email.setText(eml);
            }
        }catch(Exception e){
            email.setText("mail@domain.de");
        }
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Heimatzentrunm.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Heimatzentrunm.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Heimatzentrunm.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
                startActivity(new Intent(Heimatzentrunm.this, Setting.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(Heimatzentrunm.this, MainActivity.class));
                finish();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Heimatzentrunm.this, Setting.class));
        finish();
    }

    public void abbrechen(View view){
        startActivity(new Intent(Heimatzentrunm.this, Setting.class));
        finish();
    }

    /**
     * function to save the data
     * @param view
     */
    public void saveData(View view){
        try {
            if(HDC != 0) {
                Log.e("Update","yes");
                long i =  settingDatabase.updateHeimatzentrum(new HeimatzentrumBean(1,
                            klink.getText().toString(),
                            strabe.getText().toString(),
                            plz_ort_code.getText().toString(),
                            plz_ort_number.getText().toString(),
                            telefon_number.getText().toString(),
                            telefon_ort_number2.getText().toString(),
                            email.getText().toString(),
                            kontaktperson_name.getText().toString(),
                            kontaktperson_telefon_number.getText().toString(),
                            kontaktperson_telefon_number2.getText().toString(),
                            kommentar.getText().toString(),centerID));
                Intent I1 = new Intent(Heimatzentrunm.this, Setting.class);
                I1.putExtra("FRAG","btn3");
                startActivity(I1);
                finish();
            }
            else{
                String snew = email.getText().toString();
               HeimatzentrumBean heimatzentrumBean = new HeimatzentrumBean(1,
                                                    klink.getText().toString(),
                                                    strabe.getText().toString(),
                                                    plz_ort_code.getText().toString(),
                                                    plz_ort_number.getText().toString(),
                                                    telefon_number.getText().toString(),
                                                    telefon_ort_number2.getText().toString(),
                                                    email.getText().toString(),
                                                    kontaktperson_name.getText().toString(),
                                                    kontaktperson_telefon_number.getText().toString(),
                                                    kontaktperson_telefon_number2.getText().toString(),
                                                    kommentar.getText().toString(),centerID);
                long i = settingDatabase.heimatzentrum_add(heimatzentrumBean);
                if(i != 0)
                {
                    Intent I1 = new Intent(Heimatzentrunm.this, Setting.class);
                    I1.putExtra("FRAG","btn3");
                    startActivity(I1);
                    finish();
                }

            }

        }catch (Exception ex){

            ex.printStackTrace();
            startActivity(new Intent(Heimatzentrunm.this, Setting.class));
            finish();
        }
    }
}

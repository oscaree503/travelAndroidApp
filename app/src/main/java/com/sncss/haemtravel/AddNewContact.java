package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.DoctorBean;
import com.sncss.haemtravel.database.SettingDatabase;
/*
*This activity is used to add new contact to the center
*/
public class AddNewContact extends Activity {
    String country_name, center_name, do_task,center_ID,ISO;
    LinearLayout  save_btn, cancel_btn;
    TextView title_topbar_txt, back_img, home_img,save_btn_icon,add_contact_head, btn_right_topbar, docTeleMain,docName, docEmail;
    int docID;
    SettingDatabase sDb;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        sDb = new SettingDatabase(this);

            bundle = getIntent().getExtras();
            country_name = bundle.getString("country_name");
            center_name = bundle.getString("center_name");
            center_ID = bundle.getString("center_ID");
            ISO = bundle.getString("ISO");

        do_task= bundle.getString("do_contact");
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        add_contact_head = (TextView) findViewById(R.id.add_contact_head);
        add_contact_head.setText(center_name);
        title_topbar_txt.setText(getResources().getString(R.string.new_contact_head));

        save_btn=(LinearLayout)findViewById(R.id.save_btn_cnt);
            cancel_btn=(LinearLayout)findViewById(R.id.cancel_btn);
            Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

            home_img = (TextView) findViewById(R.id.btn_right_topbar);
           // docTeleCode = (TextView) findViewById(R.id.docTeleCode);
            docTeleMain = (TextView) findViewById(R.id.docTeleMain);
            docName = (TextView) findViewById(R.id.docName);
            docEmail = (TextView) findViewById(R.id.docEmail);
                if(do_task.matches("edit_Contact")){
                    title_topbar_txt.setText(getResources().getString(R.string.edit_contact_head));

                    docName.setText(bundle.getString("name"));
                        docEmail.setText(bundle.getString("email"));
                       // docTeleCode.setText("+"+bundle.getString("telephoneCode"));
                        docTeleMain.setText(bundle.getString("telephone"));
                        docID = bundle.getInt("docId");
                }


        back_img = (TextView) findViewById(R.id.btn_left_topbar);
            save_btn_icon = (TextView) findViewById(R.id.save_btn_icon);
            back_img.setTypeface(font);
            home_img.setTypeface(font);
            save_btn_icon.setTypeface(font);
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    do_func();
                }
            });
            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();

                }
            });
            back_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            home_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AddNewContact.this, MainActivity.class));
                    finish();
                }
            });

        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddNewContact.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddNewContact.this,FooterFrg.class);
                in.putExtra("Frag","datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddNewContact.this,FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
 }

    private DoctorBean getDocDetail() {
        DoctorBean bean = new DoctorBean();
        //bean.setTelefoneCode(docTeleCode.getText().toString());
        bean.setTelefone(docTeleMain.getText().toString());
        bean.setDocName(docName.getText().toString());
        bean.setEmail(docEmail.getText().toString());
        return bean;
    }
/*function to stoere the doctor details in to database */
    public void do_func(){
        if(do_task.matches("edit_Contact")){
            sDb.updateDocDetail(getDocDetail(),docID);
            onBackPressed();
        } else{
            sDb.setDocDetail(getDocDetail(), center_ID);
            onBackPressed();
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddNewContact.this, CityPlaceDetail.class);
        intent.putExtra("country_name", country_name);
        intent.putExtra("center_name", center_name);
        intent.putExtra("center_ID", center_ID);
        intent.putExtra("ISO", ISO);
        intent.putExtra("longii", bundle.getString("longii"));
        intent.putExtra("lat", bundle.getString("lat"));
        startActivity(intent);
        finish();
    }
}

package com.sncss.haemtravel;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.EmailBean;
import com.sncss.haemtravel.Bean.NameBean;
import com.sncss.haemtravel.Bean.NotfallBean;
import com.sncss.haemtravel.Bean.TelefoneBean;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
/*
*This activity sends the data to the server for address chenge request
 */
public class AddressMitte extends Activity {
    String country_name, center_name, center_ID,ISO,strabe,plz,ort;
    LinearLayout cancel_btn;
    TextView save_btn,title_topbar_txt, back_img, home_img,telefon_number,notfall_edit,e_mail,name,strabeE,plz_ort_code,plz_ort_number;
    Bundle bundle;
    ArrayList<TelefoneBean> tB;
    ArrayList<NotfallBean> tBN;
    ArrayList<EmailBean> tBE;
    ArrayList<NameBean> tNB;
    Boolean st= false;
    ProgressDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_mitte);
        save_btn=(TextView)findViewById(R.id.save_btn);
        telefon_number=(TextView)findViewById(R.id.telefon_number);
        notfall_edit=(TextView)findViewById(R.id.notfall_edit);
        e_mail=(TextView)findViewById(R.id.e_mail);
        name = (TextView)findViewById(R.id.name);
        strabeE= (TextView)findViewById(R.id.strabe);
        plz_ort_code= (TextView)findViewById(R.id.plz_ort_code);;
        plz_ort_number= (TextView)findViewById(R.id.plz_ort_number);;
        cancel_btn=(LinearLayout)findViewById(R.id.cancel_btn);
        title_topbar_txt = (TextView) findViewById(R.id.title_topbar_txt);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        home_img = (TextView) findViewById(R.id.btn_right_topbar);
        back_img = (TextView) findViewById(R.id.btn_left_topbar);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        bundle = getIntent().getExtras();
        country_name = bundle.getString("country_name");
        center_name = bundle.getString("center_name");
        center_ID= bundle.getString("center_ID");
        ISO = bundle.getString("ISO");
        strabe = bundle.getString("strabe");
        plz = bundle.getString("PLZ");
        ort = bundle.getString("Ort");
        tB =  (ArrayList<TelefoneBean>) getIntent().getSerializableExtra("telefone");
        tBN = (ArrayList<NotfallBean>) getIntent().getSerializableExtra("telefon_not");
        tNB = (ArrayList<NameBean>) getIntent().getSerializableExtra("nameS");
        tBE =(ArrayList<EmailBean>) getIntent().getSerializableExtra("E-mail");
        title_topbar_txt.setText(country_name);
        name.setText(center_name);
        strabeE.setText(strabe);
        plz_ort_code.setText(plz);
        plz_ort_number.setText(ort);
        String t="";
        for(int j=0;j<tB.size();j++) {
            t += tB.get(j).getTelefone()+"\n";
        }
        telefon_number.setText(t);
        String tN = "";
        for(int j=0;j<tBN.size();j++) {
            tN += tBN.get(j).getNotfalls()+"\n";

        }
        notfall_edit.setText(tN);
        String e= "";
        for(int j=0;j<tBE.size();j++) {
            e += tBE.get(j).getEamil()+"\n";
        }
        e_mail.setText(e);
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
                startActivity(new Intent(AddressMitte.this, MainActivity.class));
                finish();
            }
        });



    }
    public void do_func(){
        SendDataToServer sDS = new SendDataToServer();
        sDS.execute();

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddressMitte.this, CityPlaceDetail.class);
        intent.putExtra("country_name", country_name);
        intent.putExtra("center_name", center_name);
        intent.putExtra("center_ID", center_ID);
        intent.putExtra("ISO", ISO);
        intent.putExtra("longii", bundle.getString("longii"));
        intent.putExtra("lat", bundle.getString("lat"));
        startActivity(intent);
        finish();
    }

    /***
     * AsyncTask activity to send the data to the server and fetch the response and showing to user
     */
    private class SendDataToServer extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog1 = ProgressDialog.show(AddressMitte.this, "", "", true);

        }

        @Override
        protected String doInBackground(String... params) {
           try {
               String n="";
               for(int j=0;j<tNB.size();j++) {
                   if(j==tNB.size()-1){
                       n += "Name="+tNB.get(j).getName();
                   }else{
                       n += "Name="+tNB.get(j).getName()+"&";
                   }
               }
               String t="";
               for(int j=0;j<tB.size();j++) {
                   if(j==tB.size()-1){
                       t += "Telefon="+tB.get(j).getTelefone();
                   }else{
                       t += "Telefon="+tB.get(j).getTelefone()+"&";

                   }
               }
               String tN = "";
               for(int j=0;j<tBN.size();j++) {
                   if(j==tB.size()-1){
                       tN += "Telefon_Not="+tBN.get(j).getNotfalls();
                   }else{
                       tN += "Telefon_Not="+tBN.get(j).getNotfalls()+"&";
                   }
               }
               String e= "";
               for(int j=0;j<tBE.size();j++) {
                   if(j==tBE.size()-1){
                       e += "Email="+tBE.get(j).getEamil();
                   }else{
                       e += "Email="+tBE.get(j).getEamil()+"&";
                   }
               }
               String UrlParam = "Token=BzKB7t2sRUyWSMkw&"+n+"&Strasse="+strabe+"&PLZ="+plz+"&Stadt="+ort+"&Land="+country_name+"&"+t+"&"+tN+"&"+e;
               String request = "https://www.crm.de/haemophilie/update.asp?"+UrlParam;
               URL url  = new URL(request);
               HttpURLConnection conn = (HttpURLConnection) url.openConnection();
               conn.setDoOutput(true);
               conn.setInstanceFollowRedirects(false);
               conn.setRequestMethod("GET");
               int status = conn.getResponseCode();
               if(status!=0){
                   /*case 200:
                   case 201:*/
                    st = true;
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog1.dismiss();
                    final Dialog dialog = new Dialog(AddressMitte.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_white);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setCancelable(false);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    TextView add = (TextView) dialog.findViewById(R.id.add);
                    TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                    ImageView img = (ImageView) dialog.findViewById(R.id.tick);
                    img.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    if(st) {
                        text.setText("Die von Ihnen geänderten Adressdaten wurden erfolgreich übertragen." +
                                "Vielen Dank für Ihre Unterstützung" +
                                "Ihr HaemTravel™-Team");
/*
                        Toast.makeText(AddressMitte.this,"here",Toast.LENGTH_SHORT).show();
*/

                    }else {
                        img.setImageDrawable(getResources().getDrawable(R.drawable.red));
                        text.setText("DDie von Ihnen geänderten Adressdaten konnten leider nicht übertragen werden. Bitte versuchen Sie es zu einem späteren Zeitpunkt noch einmal." +
                                "Vielen Dank für Ihre Unterstützung" +
                                "Ihr HaemTravel™-Team" );
                    }
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();

                        }
                    });
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                }
            });

        }
    }
}

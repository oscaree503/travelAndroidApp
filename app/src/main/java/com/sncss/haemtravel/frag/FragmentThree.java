package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.HeimatzentrumBean;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.List;

public class FragmentThree extends Fragment {
   public FragmentThree() {
   }

   SettingDatabase settingDatabase;
   List<HeimatzentrumBean> heimatzentrumBeanList;

   private static TextView phone_icon,phone_icon2, mail_icon,doctor_icon,doctor_phone_icon,mail_icon1,
           name,
           strabe,
           plz_ort,
           telefone,
           mail,mail1, notfallmer,
           kontak_name,
           kontak_number,
           kommentar;
  // private static SharedPreferences sharedpreferences;
   private String emailnew;
   //private static final String MyEmergency = "HeamTravel" ;

   private static String
           klinik_db,
           strabe_db,
           plz_ort_code_db,
           plz_ort_main_db,
           telefon_ort_code_db,
           telefon_ort_main_db,
           telefon_ort_code2_db,
           telefon_ort_main2_db,
           kontaktperson_name_db,
           telefon_ort_code3_db,
           telefon_ort_main3_db,
           telefon_ort_code4_db,
           telefon_ort_main4_db,
           kommentar_db;

   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
      View fragment_three = inflater.inflate(R.layout.fragment_three, container, false);
      Typeface font =Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
      settingDatabase = new SettingDatabase(getActivity());
      //sharedpreferences = this.getActivity().getSharedPreferences(MyEmergency, Context.MODE_PRIVATE);

      phone_icon = (TextView)fragment_three.findViewById(R.id.phone_icon);
      phone_icon2 = (TextView)fragment_three.findViewById(R.id.phone_icon2);

      mail_icon = (TextView)fragment_three.findViewById(R.id.mail_icon);
       mail_icon1 = (TextView)fragment_three.findViewById(R.id.mail_icon1);
      doctor_icon = (TextView)fragment_three.findViewById(R.id.doctor_icon);
      doctor_phone_icon = (TextView)fragment_three.findViewById(R.id.doctor_phone_icon);

      phone_icon.setTypeface(font);
      phone_icon2.setTypeface(font);
      mail_icon.setTypeface(font);
       mail_icon1.setTypeface(font);
      doctor_icon.setTypeface(font);
      doctor_phone_icon.setTypeface(font);

      name = (TextView)fragment_three.findViewById(R.id.name);
      strabe = (TextView)fragment_three.findViewById(R.id.strabe);
      plz_ort = (TextView)fragment_three.findViewById(R.id.plz_ort);
      telefone = (TextView)fragment_three.findViewById(R.id.telefone);
      notfallmer = (TextView)fragment_three.findViewById(R.id.telefone2);

      mail = (TextView)fragment_three.findViewById(R.id.mail);
       mail1 = (TextView)fragment_three.findViewById(R.id.mail1);
      kontak_name = (TextView)fragment_three.findViewById(R.id.kontak_name);
      kontak_number = (TextView)fragment_three.findViewById(R.id.kontak_number);
      kommentar = (TextView)fragment_three.findViewById(R.id.kommentar);
      /*try{
        // String eml = sharedpreferences.getString(emailnew,"");
         if(!eml.matches("")){
            mail.setText(eml);
         }
      }catch(Exception e){
         mail.setText("mail@domain.de");
      }*/

      try {
         heimatzentrumBeanList = settingDatabase.getAllDetails_H();

         if(heimatzentrumBeanList.size() != 0) {
            for (int i = 0; i < heimatzentrumBeanList.size(); i++) {

               klinik_db = (heimatzentrumBeanList.get(i).getKlinik());
               strabe_db = (heimatzentrumBeanList.get(i).getStrabe());
               plz_ort_code_db = (heimatzentrumBeanList.get(i).getPlz_ort_code());
               plz_ort_main_db = (heimatzentrumBeanList.get(i).getPlz_ort_main());
               //telefon_ort_code_db = (heimatzentrumBeanList.get(i).getTelefon_ort_code());
               telefon_ort_main_db = (heimatzentrumBeanList.get(i).getTelefon_ort_main());
               //telefon_ort_code2_db = (heimatzentrumBeanList.get(i).getTelefon_ort_code2());
               telefon_ort_main2_db = (heimatzentrumBeanList.get(i).getTelefon_ort_main2());
                emailnew = (heimatzentrumBeanList.get(i).getEmail());
               kontaktperson_name_db = (heimatzentrumBeanList.get(i).getKontaktperson_name());
               //telefon_ort_code3_db = (heimatzentrumBeanList.get(i).getTelefon_ort_code3());
               telefon_ort_main3_db = (heimatzentrumBeanList.get(i).getTelefon_ort_main3());
               //telefon_ort_code4_db = (heimatzentrumBeanList.get(i).getTelefon_ort_code4());
               telefon_ort_main4_db = (heimatzentrumBeanList.get(i).getTelefon_ort_main4());
               kommentar_db = (heimatzentrumBeanList.get(i).getKommentar());

            }

            name.setText(klinik_db);
            strabe.setText(strabe_db);
            plz_ort.setText(plz_ort_code_db + " " + plz_ort_main_db);
            telefone.setText(telefon_ort_main_db);
            notfallmer.setText(telefon_ort_main2_db);
            kontak_name.setText(kontaktperson_name_db);
            kontak_number.setText(telefon_ort_main3_db);
            kommentar.setText(kommentar_db);
             mail.setText(emailnew);
             mail1.setText(telefon_ort_main4_db);
         }
      }
      catch (Exception e)
      {

      }
      return fragment_three;
   }
}

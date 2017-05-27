package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.MeineDatenGetSet;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.List;

public class FragmentOne extends Fragment {
   public FragmentOne() {
   }
   SettingDatabase meineDatendb;
   List<MeineDatenGetSet> meineDatenGetSets;
   private static String name_sdb, strabe_sdb,plz_ort_code_sdb, plz_ort_main_sdb,telefon_code_sdb, telefon_main_sdb, email_sdb, geburtstag_sdb;
   private static TextView phone_icon, mail_icon, birth_icon , name, strabe, plz_ort, tele_fone, email, geburtstag, birth_date;

   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
      View fragment_one = inflater.inflate(R.layout.fragment_one,container,false);
      Typeface font =Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
      meineDatendb = new SettingDatabase(getActivity());

      phone_icon = (TextView)fragment_one.findViewById(R.id.phone_icon);
      mail_icon = (TextView)fragment_one.findViewById(R.id.mail_icon);
      birth_icon = (TextView)fragment_one.findViewById(R.id.birth_icon);

      name = (TextView)fragment_one.findViewById(R.id.name);
      strabe = (TextView)fragment_one.findViewById(R.id.strabe);
      plz_ort = (TextView)fragment_one.findViewById(R.id.plz_ort);
      tele_fone = (TextView)fragment_one.findViewById(R.id.tele_fone);
      email = (TextView)fragment_one.findViewById(R.id.email);
      birth_date = (TextView)fragment_one.findViewById(R.id.birth_date);
      geburtstag = (TextView)fragment_one.findViewById(R.id.geburtstag);

      try {
         meineDatenGetSets = meineDatendb.getAllDetails_MD();
         if (meineDatenGetSets.size() != 0) {
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
            plz_ort.setText(plz_ort_code_sdb + " " + plz_ort_main_sdb);
            tele_fone.setText('+' + telefon_code_sdb + '-' + telefon_main_sdb);
            email.setText(email_sdb);
            birth_date.setText(geburtstag_sdb);
         }
      }
      catch (Exception e)
      {

      }

      phone_icon.setTypeface(font);
      mail_icon.setTypeface(font);
      birth_icon.setTypeface(font);
      return fragment_one;
   }
}

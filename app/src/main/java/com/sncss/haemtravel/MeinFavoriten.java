package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.MeinFavorites;
import com.sncss.haemtravel.Bean.SectionItemM;
import com.sncss.haemtravel.adapter.MeinFavoritenAdapter;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by developer1 on 1/3/16.
 */
public class MeinFavoriten extends Activity implements OnClickListener{
    private static TextView back_img, home_img,title_topbar_txt, my_bookmark;
    Map<String, Integer> mapIndex;
    ListView counrtyList;
    String country_name,startingcode = null;;
    SettingDatabase settingDatabase;
    ArrayList<MeinFavorites> meinFavoritesList;
    String[] countries = new String[36];
    ArrayList<MeinFavorites> items = new ArrayList<MeinFavorites>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mein_favoriten);

        settingDatabase = new SettingDatabase(this);

        Typeface font =  Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

//        String[] countries = getResources().getStringArray(R.array.fstecountries_array);

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.meine_favoriten);


        meinFavoritesList = (ArrayList<MeinFavorites>) settingDatabase.get_table_meinfavoriten();
        items = getItemList(meinFavoritesList);

        /*char  a = 'A';
        char z = 'Z';
        int ia=0;
        for(char i1=a; i1<=z;i1++) {
           int loc = 1;
            for(int j=0; j<meinFavoritesList.size();j++){
                MeinFavorites m2 = meinFavoritesList.get(j);
                String s1 = m2.getCountry_name();
                char st = s1.charAt(0);
                if(loc==1 && st == i1){
                    items.add(new SectionItemM(String.valueOf(i1)));
                    int id =m2.getId();
                    String center_id=m2.getCenter_id();
                    String center_name= m2.getCenter_name();
                    String country_iso= m2.getCountry_iso();
                    String country_name= m2.getCountry_name();
                    MeinFavorites m1 = new MeinFavorites(id,center_id,center_name,country_iso,country_name);
                    items.add(m1);
                    countries[ia]=country_name;
                    loc+=1;
                }else if(st == i1){
                    int id =m2.getId();
                    String center_id=m2.getCenter_id();
                    String center_name= m2.getCenter_name();
                    String country_iso= m2.getCountry_iso();
                    String country_name= m2.getCountry_name();
                    MeinFavorites m1 = new MeinFavorites(id,center_id,center_name,country_iso,country_name);
                    items.add(m1);
                }
            }
            ia++;
        }*/

        MeinFavoritenAdapter meinFavoritenAdapter = new MeinFavoritenAdapter(this, items);
        counrtyList = (ListView) findViewById(R.id.list_favourite);
        counrtyList.setAdapter(meinFavoritenAdapter);

        counrtyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String countryname = items.get(position).getCountry_name().toString();
                String centerName = items.get(position).getCenter_name().toString();
                String center_iso = items.get(position).getCountry_iso().toString();
                String center_id = items.get(position).getCenter_id().toString();
                Intent intent = new Intent(getApplicationContext(), CityPlaceDetail.class);
                intent.putExtra("longii", "0");
                intent.putExtra("lat", "0");
                intent.putExtra("country_name", countryname);
                intent.putExtra("center_name", centerName);
                intent.putExtra("ISO", center_iso);
                intent.putExtra("center_ID", center_id);
                startActivity(intent);

            }
        });
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        getIndexList(countries);
        displayIndex();
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MeinFavoriten.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MeinFavoriten.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MeinFavoriten.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    private ArrayList<MeinFavorites> getItemList(ArrayList<MeinFavorites> meinFavoritesList) {
        ArrayList<MeinFavorites> items = new ArrayList<MeinFavorites>();
        int ia=0;
        for(int j=0; j<meinFavoritesList.size();j++){
            MeinFavorites m2 = meinFavoritesList.get(j);
            String s1 = m2.getCountry_name();
            char st = s1.charAt(0);
            boolean hasIndex=true;
            for (int i = 0; i < items.size(); i++) {
                //if(j==0){
                  //  hasIndex=true;
                //}else
                if(items.get(i).isSection()) {
                    SectionItemM si = (SectionItemM) items.get(i);
                    if (!si.getTitle().matches(String.valueOf(st))){
                        hasIndex=true;
                    }else {
                        hasIndex=false;
                    }
                }
            }
            if(hasIndex){
                items.add(new SectionItemM(String.valueOf(st)));
            }


                int id =m2.getId();
                String center_id=m2.getCenter_id();
                String center_name= m2.getCenter_name();
                String country_iso= m2.getCountry_iso();
                String country_name= m2.getCountry_name();
                MeinFavorites m1 = new MeinFavorites(id,center_id,center_name,country_iso,country_name);
                items.add(m1);
                countries[ia]=country_name;
                ia++;
        }

        return items;
    }

    /**
     *
     * @param country_name
     */
    private void getIndexList(String[] country_name) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < country_name.length; i++) {
            String countries = country_name[i];
            if(countries!=null) {
                String index = countries.substring(0, 1);
                if (mapIndex.get(index) == null)
                    mapIndex.put(index, i);
            }
        }
    }

    /**
     * For creating the sidebar index or fast scroller
     */
    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
            final TextView finalTextView = textView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = 0;
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).isSection()) {
                            SectionItemM si = (SectionItemM) items.get(i);
                            if (si.getTitle().matches(finalTextView.getText().toString())) {
                                pos = i;
                            }
                        }
                    }
                    counrtyList.smoothScrollToPositionFromTop(pos, 0);
                }
            });
        }
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.side_index:
                TextView selectedIndex = (TextView) view;
                counrtyList.setSelection(mapIndex.get(selectedIndex.getText()));
                break;
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(MeinFavoriten.this, MainActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MeinFavoriten.this,HamophiliezentrumSuchen.class));
        finish();
    }
}

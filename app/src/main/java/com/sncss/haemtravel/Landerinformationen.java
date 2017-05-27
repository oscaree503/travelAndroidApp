package com.sncss.haemtravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.Country;
import com.sncss.haemtravel.Bean.EntryItem;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.adapter.CountryListArrayAdapter;
import com.sncss.haemtravel.adapter.EntryAdapter;
import com.sncss.haemtravel.interfaces.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by developer1 on 18/2/16.
 */
public class Landerinformationen extends Activity implements OnClickListener, AdapterView.OnItemClickListener{
    private static TextView back_img, home_img,title_topbar_txt,search_icon,search_fa,closeSearch;
    private static EditText autocomplete;
    Map<String, Integer> mapIndex;
    ListView counrtyList;
    private static String back_press;
    private static Bundle bundle;
    private static Intent intent;
    private static final String TagLander = "Laender";
    private static final String Tag_Sprache  = "Sprache";
    JSONObject jsonObject;
    private static final String Tag_Id = "ID";
    private static final String Tag_Land = "Land";
    private static final String Tag_Flag = "Flag";
    private static final String Tag_Country = "Country";
    ArrayList<Country> items = new ArrayList<Country>();
    String jsonString ;
    String[] countries= new String[36];
    ListView listview=null;
    String data ="";
    CountryListArrayAdapter adapter;
    private static LinearLayout linearlayout_search,linearLayout_editbox;
    JSONArray jsonArrayCountry;
    File laender, myDir;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lander_informationen);
        myDir = getFilesDir();
        laender = new File(myDir+"/laender.json");
        byte[] buffer;
        try {
            if (laender.exists()) {
                is = new FileInputStream(laender);
            } else {
                is = getAssets().open("laender.json");
            }
            int size = is.available();

            buffer = new byte[size];

            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        bundle = getIntent().getExtras();
        linearLayout_editbox = (LinearLayout)findViewById(R.id.linearLayout_editbox);
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(R.string.landerinformationen);
        autocomplete = (EditText)findViewById(R.id.autocomplete);
        autocomplete.setOnClickListener(this);
        autocomplete.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        search_fa = (TextView)findViewById(R.id.search_fa);
        closeSearch = (TextView)findViewById(R.id.closeSearch);
        closeSearch.setOnClickListener(this);
        search_fa.setTypeface(font);
        closeSearch.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        counrtyList = (ListView) findViewById(R.id.list_countries);
        //JSON Pasring
        try{
            JSONObject json = new JSONObject(jsonString);
            jsonObject = json.getJSONObject(TagLander);
            char  a = 'A';
            char z = 'Z';
            int ia=0;
            for(char i1=a; i1<=z;i1++) {
                try {
                    jsonArrayCountry = jsonObject.optJSONArray(String.valueOf(i1));
                }catch(Exception je){
                    jsonArrayCountry=null;
                }
                    if(jsonArrayCountry!= null){
                            items.add(new SectionItem((String.valueOf(i1))));
                            for (int i = 0; i < jsonArrayCountry.length(); i++) {
                                    JSONObject objectCountry = jsonArrayCountry.getJSONObject(i);
                                    int id1 = Integer.parseInt(objectCountry.optString(Tag_Id).toString());
                                    String land1 = objectCountry.optString(Tag_Land).toString();
                                    String flag1 = objectCountry.optString(Tag_Flag).toString().toLowerCase();
                                    if(flag1.matches("do")){
                                        flag1 ="dor";
                                    }
                                    String country = objectCountry.optString(Tag_Country).toString();
                                    Country cnt  = new Country(land1, flag1,country);
                                    items.add(cnt);
                                    countries[ia] = land1;
                            }
                }
                ia++;

            }
        }catch (JSONException e){
            Log.e("msg", "" + e);
            e.printStackTrace();}
        adapter = new CountryListArrayAdapter(this, items);
        counrtyList.setAdapter(adapter);
        counrtyList.setOnItemClickListener(this);

        getIndexList(countries);
        displayIndex();
        autocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = autocomplete.getText().toString();
                adapter.filter(text);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub

            }
        });
        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Landerinformationen.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Landerinformationen.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Landerinformationen.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_right_topbar:
                startActivity(new Intent(Landerinformationen.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_left_topbar:
               onBackPressed();
                break;
            case R.id.autocomplete:
                linearLayout_editbox.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL );
                autocomplete.setHint("");
                closeSearch.setVisibility(View.VISIBLE);

                break;
            case R.id.closeSearch:
                linearLayout_editbox.setGravity(Gravity.CENTER);
                autocomplete.setText("");
                autocomplete.setHint(getString(R.string.nach_land_suchen));
                closeSearch.setVisibility(View.GONE);
                InputMethodManager inputManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(Landerinformationen.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(Landerinformationen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Country item = (Country)items.get(position);
        String countryFlagCode = item.getFlag();
        Intent intent_with_country_name = new Intent(Landerinformationen.this, LanderWebView.class);
        intent_with_country_name.putExtra("countryFlagCode", item.getCode());
        intent_with_country_name.putExtra("countryName",item.getName());
        startActivity(intent_with_country_name);
        finish();
    }

    /**
     *
     * @param country_name
     */
    private void getIndexList(String[] country_name) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < country_name.length; i++) {
            if(country_name[i]!=null) {
                String countries = country_name[i];
                String index = countries.substring(0, 1);
                if (mapIndex.get(index) == null)
                    mapIndex.put(index, i);
            }
        }
    }

    /**
     * displaying the side Index that is fast scroll
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
                            SectionItem si = (SectionItem) items.get(i);
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
}

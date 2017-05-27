package com.sncss.haemtravel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.Country;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.adapter.CountryListArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by developer1 on 23/2/16.
 */
public class HamophiliezentrumSuchen extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
        private static TextView back_img, home_img,title_topbar_txt, my_bookmark,search_icon, search_fa,tv,closeSearch;

        Map<String, Integer> mapIndex;
        ListView counrtyList;
        private static Bundle bundle;
        private static String back_press;
        private static Intent intent;
        List<String> indexList;
        ArrayList<Country> items = new ArrayList<Country>();
        ListView listview=null;
        LinearLayout mF;
        boolean success=false;
        File laender, myDir,haemo;
        JSONArray jsonArrayCenter;
        private static final String TagLander = "Laender";
        String TagJson = "Haemophiliezentren";
        private static final String Tag_Sprache  = "Sprache";
        private static final String Tag_Id = "ID";
        private static final String Tag_Land = "Land";
        private static final String Tag_Flag = "Flag";
        private static final String Tag_ISO = "Country";
        ProgressDialog dialog;
        JSONObject jsonObject,jsonObjecth;
        InputStream is,hs;
        String jsonString,jsonStringh ;
        String data = "";
        String startingcode = null;
        JSONArray jsonArrayCountry;
        EditText autocomplete;
        CountryListArrayAdapter adapter;
        String[] countries = new String[36];
        private static LinearLayout linearlayout_search,linearLayout_editbox;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.hamophiliezentrum_suchen);

                Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
                bundle = getIntent().getExtras();
                autocomplete = (EditText)findViewById(R.id.autocomplete);
                autocomplete.setOnClickListener(this);
                autocomplete.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                linearlayout_search = (LinearLayout)findViewById(R.id.linearlayout_search);
                linearLayout_editbox = (LinearLayout)findViewById(R.id.linearLayout_editbox);
                mF = (LinearLayout)findViewById(R.id.menine_fav_layt);
                home_img = (TextView)findViewById(R.id.btn_right_topbar);
                back_img = (TextView)findViewById(R.id.btn_left_topbar);
                title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
                my_bookmark = (TextView)findViewById(R.id.my_bookmark);
                search_icon = (TextView)findViewById(R.id.textView21);
                search_fa = (TextView)findViewById(R.id.search_fa);
                closeSearch = (TextView)findViewById(R.id.closeSearch);
                closeSearch.setOnClickListener(this);
                back_img.setOnClickListener(this);
                home_img.setOnClickListener(this);
                back_img.setText(R.string.fa_angle_left);
                home_img.setText(R.string.fa_home);
                title_topbar_txt.setText(R.string.hamophiliezentren);
                back_img.setTypeface(font);
                home_img.setTypeface(font);
                my_bookmark.setTypeface(font);
                search_icon.setTypeface(font);
                search_fa.setTypeface(font);
                closeSearch.setTypeface(font);
                // Capture Text in EditText
                tv = (TextView)findViewById(R.id.list_item_section_text);

                counrtyList = (ListView) findViewById(R.id.list_countries);
                myDir = getFilesDir();
                laender = new File(myDir+"/laender.json");
                haemo = new File(myDir+"/Haemophiliezentren.json");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog = ProgressDialog.show(HamophiliezentrumSuchen.this, "", "", true);
                            }
                        });
                        countryListView();

                    }
                };
                new Thread(runnable).start();


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
                                Intent in = new Intent(HamophiliezentrumSuchen.this, FooterFrg.class);
                                in.putExtra("Frag", "kontakt");
                                in.putExtra("back_press",getIntent().getComponent().getClassName());
                                startActivity(in);
                        }
                });

                datenschutz.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent in = new Intent(HamophiliezentrumSuchen.this,FooterFrg.class);
                                in.putExtra("Frag","datenschutz");
                                in.putExtra("back_press",getIntent().getComponent().getClassName());
                                startActivity(in);
                        }
                });

                rechtliches.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent in = new Intent(HamophiliezentrumSuchen.this, FooterFrg.class);
                                in.putExtra("Frag", "rechtliches");
                                in.putExtra("back_press",getIntent().getComponent().getClassName());
                                startActivity(in);
                        }
                });

        }

        /**
         * Preapre the country list to show
         */
        public void countryListView() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new CountryListArrayAdapter(HamophiliezentrumSuchen.this, StaticJsonData.countryItems);
                    counrtyList.setAdapter(adapter);
                    counrtyList.setOnItemClickListener(HamophiliezentrumSuchen.this);
                    getIndexList(countries);
                    displayIndex();
                    dialog.dismiss();
                }
            });

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
     * code to display the side index that is fast scroll
     */
        private void displayIndex() {
                LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);
                 TextView textView1;

                indexList = new ArrayList<String>(mapIndex.keySet());
                for (final String index : indexList) {
                        textView1= (TextView) getLayoutInflater().inflate(R.layout.side_index_item, null);
                        textView1.setText(index);
                        indexLayout.addView(textView1);
                    final TextView finalTextView = textView1;
                    textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = 0;
                                for(int i=0;i<items.size();i++){
                                    if(items.get(i).isSection()){
                                        SectionItem si = (SectionItem) items.get(i);
                                        if(si.getTitle().matches(finalTextView.getText().toString())){
                                            pos= i;
                                        }
                                    }

                                }
                                    counrtyList.smoothScrollToPositionFromTop(pos,0);
//                                    Toast.makeText(HamophiliezentrumSuchen.this,"Clicked"+ finalTextView.getText().toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                }
        }

    /**
     *
     * @param view
     */
        public void onClick(View view) {

                switch (view.getId()) {

                        case R.id.btn_left_topbar:
                                onBackPressed();
                                break;
                        case R.id.btn_right_topbar:
                                startActivity(new Intent(HamophiliezentrumSuchen.this, MainActivity.class));
                                finish();
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
                                InputMethodManager inputManager = (InputMethodManager)
                                        getSystemService(this.INPUT_METHOD_SERVICE);
                                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                        InputMethodManager.HIDE_NOT_ALWAYS);
                                break;

                }


        }

    /**
     * way to meinefavoriten activity
     * @param view
     */
        public void meinefavoriten(View view){
        startActivity(new Intent(HamophiliezentrumSuchen.this, MeinFavoriten.class));
      //  finish();
        }

        @Override
        public void onBackPressed() {
                super.onBackPressed();
                try {
                        back_press = bundle.getString("back_press");
                        Class c = Class.forName(back_press);
                        Intent i = new Intent(HamophiliezentrumSuchen.this, c);
                        startActivity(i);
                        finish();
                } catch (Exception e) {
                        intent = new Intent(HamophiliezentrumSuchen.this, MainActivity.class);
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
//                EntryItem item = (EntryItem)items.get(position);
                /*Toast.makeText(getApplicationContext(),((ImageView) view).getId(),Toast.LENGTH_SHORT.show());*/
                Country c = (Country)StaticJsonData.countryItems.get(position);
                String countryname = c.getName();
                Intent intent_with_country_name = new Intent(HamophiliezentrumSuchen.this, OnClickCountryName.class);
                intent_with_country_name.putExtra("country_name", countryname);
                intent_with_country_name.putExtra("ISO",c.getCode());
                startActivity(intent_with_country_name);
                finish();
        }
}

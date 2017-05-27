package com.sncss.haemtravel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.DiagnoseBehandlungGetSet;
import com.sncss.haemtravel.Bean.MeineDatenGetSet;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.color.white;

/**
 * Created by developer1 on 2/3/16.
 */
public class DiagnoseBehandlung extends Activity implements OnClickListener{

    private static Intent intent;
    private static Bundle bundle;
    private static String back_press;
    private static TextView back_img, home_img, title_topbar_txt, save_btn, spinner, save_diog;
    private static EditText
            diagnose,
            restaktivitat,
            meinfaktor,
            dosierung,
            dosierungsfrequen;
    private static String
            id_sdb,
            diagnose_sdb,
            restaktivitat_sdb,
            therapieart_sdb,
            meinfaktor_sdb,
            dosierung_sdb,
            dosierungsfrequen_sdb;
    String test = "NO value";

    SettingDatabase settingDatabase;
    List<DiagnoseBehandlungGetSet> diagnoseBehandlungGetSets;

    private static LinearLayout therapieart;

    String popUpContents[];
    PopupWindow popupWindowDogs;
    TextView spinnerText;
    private int DBC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnose_behandlung);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        settingDatabase = new SettingDatabase(this);

        spinner = (TextView)findViewById(R.id.spinner);
        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        save_btn = (TextView)findViewById(R.id.save_btn);
        spinnerText = (TextView)findViewById(R.id.spinnerText);
        save_diog = (TextView)findViewById(R.id.save_diog);

        diagnose = (EditText)findViewById(R.id.diagnose);
        restaktivitat = (EditText)findViewById(R.id.restaktivitat);
        meinfaktor = (EditText)findViewById(R.id.meinfaktor);
        dosierung = (EditText)findViewById(R.id.dosierung);
        dosierungsfrequen = (EditText)findViewById(R.id.dosierungsfrequen);

        therapieart = (LinearLayout)findViewById(R.id.therapieart);

        List<String> spinnerList = new ArrayList<String>();
        spinnerList.add("Bitte auswählen::1");
        spinnerList.add("PPx (Prophylaxe)::2");
        spinnerList.add("oD (bei Bedarf)::3");
        popUpContents = new String[spinnerList.size()];
        spinnerList.toArray(popUpContents);
        popupWindowDogs = popupWindowSpinner();
        View.OnClickListener handler = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.spinnerText:
                        popupWindowSpinner().showAsDropDown(v, 0, 0);
                        break;
                }
            }
        };

        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        spinnerText.setOnClickListener(handler);


        back_img.setText(R.string.no_text_value);
        home_img.setText(R.string.no_text_value);
        title_topbar_txt.setText(R.string.setting);

        spinner.setTypeface(font);
        save_diog.setTypeface(font);
        back_img.setTypeface(font);
        home_img.setTypeface(font);
        DBC = settingDatabase.getDBCount();
        /**
         * Fetching the data from the database
         */
        try {

            if (DBC != 0) {

                diagnoseBehandlungGetSets = settingDatabase.getAllDetails_DB();
                for (int i = 0; i < diagnoseBehandlungGetSets.size(); i++) {
                    id_sdb = String.valueOf(diagnoseBehandlungGetSets.get(i).getId());
                    diagnose_sdb = diagnoseBehandlungGetSets.get(i).getDiagnose();
                    restaktivitat_sdb = diagnoseBehandlungGetSets.get(i).getRestaktivitat();
                    therapieart_sdb = diagnoseBehandlungGetSets.get(i).getTherapieart();
                    meinfaktor_sdb = diagnoseBehandlungGetSets.get(i).getMeinfaktor();
                    dosierung_sdb = diagnoseBehandlungGetSets.get(i).getDosierung();
                    dosierungsfrequen_sdb = diagnoseBehandlungGetSets.get(i).getDosierungsfrequen();
                }

                diagnose.setText(diagnose_sdb);
                restaktivitat.setText(restaktivitat_sdb);
                dosierung.setText(dosierung_sdb);
                meinfaktor.setText(meinfaktor_sdb);
                spinnerText.setText(therapieart_sdb);
                dosierungsfrequen.setText(dosierungsfrequen_sdb);
            }
        }
        catch (Exception e)
        {

        }

        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DiagnoseBehandlung.this, FooterFrg.class);
                in.putExtra("Frag", "kontakt");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DiagnoseBehandlung.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DiagnoseBehandlung.this,FooterFrg.class);
                in.putExtra("Frag","rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });

    }

    /**
     * poupWindow code
     * @return
     */
    public PopupWindow popupWindowSpinner() {

        PopupWindow popupWindow = new PopupWindow(this);
        ListView listViewSpinner = new ListView(this);
        listViewSpinner.setAdapter(dogsAdapter(popUpContents));
        listViewSpinner.setBackgroundResource(R.drawable.diagnose_spinner_btton);
        listViewSpinner.setOnItemClickListener(new SpinnerOnItemClickListner());
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.white));
        popupWindow.setFocusable(true);
        popupWindow.setWidth(spinnerText.getWidth());
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(listViewSpinner);
        return popupWindow;
    }

    /**
     *
     * @param spinnerArray
     * @return
     */
    private ArrayAdapter<String> dogsAdapter(String spinnerArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, spinnerArray) {

            @SuppressLint("NewApi")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];
                TextView listItem = new TextView(DiagnoseBehandlung.this);
                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(14);
                listItem.setPadding(20, 10, 20, 10);
                listItem.setTextColor(getResources().getColor(R.color.text_color_black));
                return listItem;
            }
        };
        return adapter;
    }

    /**
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_topbar:
                startActivity(new Intent(DiagnoseBehandlung.this, Setting.class));
                finish();
                break;
            case R.id.btn_right_topbar:
                startActivity(new Intent(DiagnoseBehandlung.this, MainActivity.class));
                finish();
                break;
            case R.id.save_btn:
                try{
                    if (DBC != 0)
                    {
                        Log.e("update call","yes");
                        saveData();
                    }
                    else {
                        Log.e("Save Call","yes");
                       addData();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DiagnoseBehandlung.this, Setting.class));
        finish();
    }

    public void abbrechen(View view){
        Intent I1 = new Intent(DiagnoseBehandlung.this, Setting.class);
        I1.putExtra("FRAG","btn2");
        startActivity(I1);
        finish();
    }

    /**
     * saving the data to the database
     */
    public void saveData(){
        try{
            settingDatabase.updateDiagnoseBehandlung(new DiagnoseBehandlungGetSet(1,
                    diagnose.getText().toString(),
                    restaktivitat.getText().toString(),
                    spinnerText.getText().toString(),
                    meinfaktor.getText().toString(),
                    dosierung.getText().toString(),
                    dosierungsfrequen.getText().toString()
            ));
            Intent I1 = new Intent(DiagnoseBehandlung.this, Setting.class);
            I1.putExtra("FRAG","btn2");
            startActivity(I1);
            finish();

        }catch (Exception ex){
            ex.printStackTrace();
            intent = new Intent(DiagnoseBehandlung.this, Setting.class);
            intent.putExtra("back_press", getIntent().getComponent().getClassName());
            startActivity(intent);
            finish();
            Toast.makeText(DiagnoseBehandlung.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * addin the data or modifying the current data
     */
    private void addData()
    {
        try {

            DiagnoseBehandlungGetSet diagnoseBehandlungGetSet = new DiagnoseBehandlungGetSet(1,
                    diagnose.getText().toString(),
                    restaktivitat.getText().toString(),
                    spinnerText.getText().toString(),
                    meinfaktor.getText().toString(),
                    dosierung.getText().toString(),
                    dosierungsfrequen.getText().toString());
            long i = settingDatabase.diagnosebehandlung_add(diagnoseBehandlungGetSet);
            if (i != 0) {
                Intent I1 = new Intent(DiagnoseBehandlung.this, Setting.class);
                I1.putExtra("FRAG","btn2");
                startActivity(I1);
                finish();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            intent = new Intent(DiagnoseBehandlung.this, Setting.class);
            intent.putExtra("back_press", getIntent().getComponent().getClassName());
            startActivity(intent);
            finish();
            Toast.makeText(DiagnoseBehandlung.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
        }
    }
}


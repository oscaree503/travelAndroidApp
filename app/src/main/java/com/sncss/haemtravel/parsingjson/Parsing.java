package com.sncss.haemtravel.parsingjson;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by developer1 on 15/3/16.
 */
public class Parsing extends Activity {

        private static final String TagLander = "Laender";
        private static final String Tag_Sprache  = "Sprache";

        private static final String Tag_Id = "ID";
        private static final String Tag_Land = "Land";
        private static final String Tag_Flag = "Flag";
        JSONObject jsonObject;
        String jsonString = "{\"Laender\": {" +
                "    \"Sprache\": \"en\"," +
                "    \"A\": [" +
                "        {" +
                "            \"ID\": \"00001\"," +
                "            \"Land\": \"Argentina\"," +
                "            \"Flag\": \"AR.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00031\"," +
                "            \"Land\": \"Armenia\"," +
                "            \"Flag\": \"AM.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00032\"," +
                "            \"Land\": \"Australia\"," +
                "            \"Flag\": \"AU.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00365\"," +
                "            \"Land\": \"Albania\"," +
                "            \"Flag\": \"AL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00366\"," +
                "            \"Land\": \"Algeria\"," +
                "            \"Flag\": \"DZ.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00376\"," +
                "            \"Land\": \"Austria\"," +
                "            \"Flag\": \"AT.png\"" +
                "        }" +
                "    ]," +
                "    \"B\": [" +
                "        {" +
                "            \"ID\": \"00049\"," +
                "            \"Land\": \"Bangladesh\"," +
                "            \"Flag\": \"BD.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00051\"," +
                "            \"Land\": \"Brazil\"," +
                "            \"Flag\": \"BR.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00388\"," +
                "            \"Land\": \"Belarus\"," +
                "            \"Flag\": \"BY.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00390\"," +
                "            \"Land\": \"Belgium\"," +
                "            \"Flag\": \"BE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00400\"," +
                "            \"Land\": \"Bulgaria\"," +
                "            \"Flag\": \"BG.png\"" +
                "        }" +
                "    ]," +
                "    \"C\": [" +
                "        {" +
                "            \"ID\": \"00117\"," +
                "            \"Land\": \"Cambodia\"," +
                "            \"Flag\": \"KH.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00121\"," +
                "            \"Land\": \"Chile\"," +
                "            \"Flag\": \"CL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00149\"," +
                "            \"Land\": \"Colombia\"," +
                "            \"Flag\": \"CO.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00160\"," +
                "            \"Land\": \"Costa Rica\"," +
                "            \"Flag\": \"CR.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00161\"," +
                "            \"Land\": \"Cuba\"," +
                "            \"Flag\": \"CU.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00408\"," +
                "            \"Land\": \"Canada\"," +
                "            \"Flag\": \"CA.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00434\"," +
                "            \"Land\": \"China\"," +
                "            \"Flag\": \"CN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00447\"," +
                "            \"Land\": \"Croatia\"," +
                "            \"Flag\": \"HR.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00449\"," +
                "            \"Land\": \"Cyprus\"," +
                "            \"Flag\": \"CY.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00450\"," +
                "            \"Land\": \"Czech Republic\"," +
                "            \"Flag\": \"CZ.png\"" +
                "        }" +
                "    ]," +
                "    \"D\": [" +
                "        {" +
                "            \"ID\": \"00162\"," +
                "            \"Land\": \"Dominican Republic\"," +
                "            \"Flag\": \"DO.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00467\"," +
                "            \"Land\": \"Denmark\"," +
                "            \"Flag\": \"DK.png\"" +
                "        }" +
                "    ]," +
                "    \"E\": [" +
                "        {" +
                "            \"ID\": \"00163\"," +
                "            \"Land\": \"Ecuador\"," +
                "            \"Flag\": \"EC.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00171\"," +
                "            \"Land\": \"El Salvador\"," +
                "            \"Flag\": \"SV.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00174\"," +
                "            \"Land\": \"Eritrea\"," +
                "            \"Flag\": \"ER.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00176\"," +
                "            \"Land\": \"Ethiopia\"," +
                "            \"Flag\": \"ET.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00470\"," +
                "            \"Land\": \"Egypt\"," +
                "            \"Flag\": \"EG.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00480\"," +
                "            \"Land\": \"Estonia\"," +
                "            \"Flag\": \"EE.png\"" +
                "        }" +
                "    ]," +
                "    \"F\": [" +
                "        {" +
                "            \"ID\": \"00482\"," +
                "            \"Land\": \"Finland\"," +
                "            \"Flag\": \"FI.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00487\"," +
                "            \"Land\": \"France\"," +
                "            \"Flag\": \"FR.png\"" +
                "        }" +
                "    ]," +
                "    \"G\": [" +
                "        {" +
                "            \"ID\": \"00178\"," +
                "            \"Land\": \"Georgia\"," +
                "            \"Flag\": \"GE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00183\"," +
                "            \"Land\": \"Guadeloupe\"," +
                "            \"Flag\": \"GP.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00184\"," +
                "            \"Land\": \"Guatemala\"," +
                "            \"Flag\": \"GT.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00523\"," +
                "            \"Land\": \"Germany\"," +
                "            \"Flag\": \"D.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00570\"," +
                "            \"Land\": \"Greece\"," +
                "            \"Flag\": \"GR.png\"" +
                "        }" +
                "    ]," +
                "    \"H\": [" +
                "        {" +
                "            \"ID\": \"00185\"," +
                "            \"Land\": \"Honduras\"," +
                "            \"Flag\": \"HN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00575\"," +
                "            \"Land\": \"Hungary\"," +
                "            \"Flag\": \"HU.png\"" +
                "        }" +
                "    ]," +
                "    \"I\": [" +
                "        {" +
                "            \"ID\": \"00189\"," +
                "            \"Land\": \"Côte d'Ivoire\"," +
                "            \"Flag\": \"CI.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00591\"," +
                "            \"Land\": \"India\"," +
                "            \"Flag\": \"IN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00590\"," +
                "            \"Land\": \"India\"," +
                "            \"Flag\": \"IN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00660\"," +
                "            \"Land\": \"Indonesia\"," +
                "            \"Flag\": \"ID.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00672\"," +
                "            \"Land\": \"Iran\"," +
                "            \"Flag\": \"IR.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00677\"," +
                "            \"Land\": \"Iraq\"," +
                "            \"Flag\": \"IQ.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00678\"," +
                "            \"Land\": \"Ireland\"," +
                "            \"Flag\": \"IE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00682\"," +
                "            \"Land\": \"Israel\"," +
                "            \"Flag\": \"IL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00683\"," +
                "            \"Name\": \"Centro Emostasi e Trombosi\"," +
                "            \"Land\": \"Italy\"," +
                "            \"Flag\": \"IT.png\"" +
                "        }" +
                "    ]," +
                "    \"J\": [" +
                "        {" +
                "            \"ID\": \"00190\"," +
                "            \"Land\": \"Jamaica\"," +
                "            \"Flag\": \"JM.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00730\"," +
                "            \"Land\": \"Japan\"," +
                "            \"Flag\": \"JP.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00737\"," +
                "            \"Land\": \"Jordan\"," +
                "            \"Flag\": \"JO.png\"" +
                "        }" +
                "    ]," +
                "    \"K\": [" +
                "        {" +
                "            \"ID\": \"00192\"," +
                "            \"Land\": \"Kenya\"," +
                "            \"Flag\": \"KE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00193\"," +
                "            \"Land\": \"Korea\"," +
                "            \"Flag\": \"KOR.png\"" +
                "        }" +
                "    ]," +
                "    \"L\": [" +
                "        {" +
                "            \"ID\": \"00207\"," +
                "            \"Land\": \"Lithuania\"," +
                "            \"Flag\": \"LT.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00739\"," +
                "            \"Land\": \"Latvia\"," +
                "            \"Flag\": \"LV.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00741\"," +
                "            \"Land\": \"Lebanon\"," +
                "            \"Flag\": \"LB.png\"" +
                "        }" +
                "    ]," +
                "    \"M\": [" +
                "        {" +
                "            \"ID\": \"00210\"," +
                "            \"Land\": \"Martinique\"," +
                "            \"Flag\": \"MQ.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00211\"," +
                "            \"Land\": \"Mexico\"," +
                "            \"Flag\": \"MX.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00244\"," +
                "            \"Land\": \"Mongolia\"," +
                "            \"Flag\": \"MN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00248\"," +
                "            \"Land\": \"Morocco\"," +
                "            \"Flag\": \"MA.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00744\"," +
                "            \"Land\": \"Macedonia\"," +
                "            \"Flag\": \"MK.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00745\"," +
                "            \"Land\": \"Malaysia\"," +
                "            \"Flag\": \"MY.png\"" +
                "        }" +
                "    ]," +
                "    \"N\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00257\"," +
                "            \"Land\": \"Nicaragua\"," +
                "            \"Flag\": \"NI.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00746\"," +
                "            \"Land\": \"Netherlands\"," +
                "            \"Flag\": \"NL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00761\"," +
                "            \"Land\": \"Norway\"," +
                "            \"Flag\": \"NO.png\"" +
                "        }" +
                "    ]," +
                "    \"O\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }" +
                "        ]," +
                "    \"P\": [" +
                "        {" +
                "            \"ID\": \"00264\"," +
                "            \"Land\": \"Panama\"," +
                "            \"Flag\": \"PA.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00265\"," +
                "            \"Land\": \"Peru\"," +
                "            \"Flag\": \"PE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00763\"," +
                "            \"Land\": \"Pakistan\"," +
                "            \"Flag\": \"PK.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00776\"," +
                "            \"Land\": \"Palestine\"," +
                "            \"Flag\": \"PS.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00777\"," +
                "            \"Land\": \"Philippines\"," +
                "            \"Flag\": \"PH.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00781\"," +
                "            \"Land\": \"Poland\"," +
                "            \"Flag\": \"PL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00794\"," +
                "            \"Land\": \"Portugal\"," +
                "            \"Flag\": \"PT.png\"" +
                "        }" +
                "    ]," +
                "" +
                "    \"Q\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }" +
                "    ]," +
                "    \"R\": [" +
                "        {" +
                "            \"ID\": \"00801\"," +
                "            \"Land\": \"Romania\"," +
                "            \"Flag\": \"RO.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00811\"," +
                "            \"Land\": \"Russian Federation\"," +
                "            \"Flag\": \"RU.png\"" +
                "        }" +
                "    ]," +
                "    \"S\": [" +
                "        {" +
                "            \"ID\": \"00273\"," +
                "            \"Land\": \"Senegal\"," +
                "            \"Flag\": \"SN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00274\"," +
                "            \"Land\": \"Serbia\"," +
                "            \"Flag\": \"RS.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00284\"," +
                "            \"Land\": \"Singapore\"," +
                "            \"Flag\": \"SG.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00285\"," +
                "            \"Land\": \"South Africa\"," +
                "            \"Flag\": \"ZA.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00300\"," +
                "            \"Land\": \"Sri Lanka\"," +
                "            \"Flag\": \"LK.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00303\"," +
                "            \"Land\": \"Sudan\"," +
                "            \"Flag\": \"SD.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00816\"," +
                "            \"Land\": \"Saudi Arabia\"," +
                "            \"Flag\": \"SA.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00817\"," +
                "            \"Land\": \"Slovakia\"," +
                "            \"Flag\": \"SK.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00822\"," +
                "            \"Land\": \"Slovenia\"," +
                "            \"Flag\": \"SL.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00824\"," +
                "            \"Land\": \"Spain\"," +
                "            \"Flag\": \"ES.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00859\"," +
                "            \"Land\": \"Sweden\"," +
                "            \"Flag\": \"SE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00862\"," +
                "            \"Land\": \"Switzerland\"," +
                "            \"Flag\": \"CH.png\"" +
                "        }" +
                "    ]," +
                "    \"T\": [" +
                "        {" +
                "            \"ID\": \"00305\"," +
                "            \"Land\": \"Thailand\"," +
                "            \"Flag\": \"TH.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00307\"," +
                "            \"Land\": \"Tunisia\"," +
                "            \"Flag\": \"TN.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00310\"," +
                "            \"Land\": \"Turkey\"," +
                "            \"Flag\": \"TR.png\"" +
                "        }" +
                "    ]," +
                "    \"U\": [" +
                "        {" +
                "            \"ID\": \"00898\"," +
                "            \"Land\": \"United States\"," +
                "            \"Flag\": \"US.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00323\"," +
                "            \"Land\": \"United Arab Emirates\"," +
                "            \"Flag\": \"AE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00326\"," +
                "            \"Land\": \"Uruguay\"," +
                "            \"Flag\": \"UY.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00329\"," +
                "            \"Land\": \"Uzbekistan\"," +
                "            \"Flag\": \"UZ.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00874\"," +
                "            \"Land\": \"United States\"," +
                "            \"Flag\": \"US.png\"" +
                "        }" +
                "    ]," +
                "    \"V\": [" +
                "        {" +
                "            \"ID\": \"00330\"," +
                "            \"Land\": \"Venezuela\"," +
                "            \"Flag\": \"VE.png\"" +
                "        }," +
                "        {" +
                "            \"ID\": \"00360\"," +
                "            \"Land\": \"Viet Nam\"," +
                "            \"Flag\": \"VN.png\"" +
                "        }" +
                "    ]," +
                "" +
                "" +
                "    \"W\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }" +
                "    ]," +
                "" +
                "" +
                "    \"X\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }" +
                "    ]," +
                "" +
                "" +
                "    \"Y\": [" +
                "        {" +
                "            \"ID\": \"00251\"," +
                "            \"Land\": \"New Zealand\"," +
                "            \"Flag\": \"NZ.png\"" +
                "        }" +
                "    ]," +
                "    " +
                "    \"Z\": [" +
                "        {" +
                "        \"ID\": \"00364\"," +
                "        \"Land\": \"Zimbabwe\"," +
                "        \"Flag\": \"ZW.png\"" +
                "    }" +
                "    ]" +
                "}" +
                "}";

        TextView json_text;



        String data = "";

        JSONArray jsonArrayCountry;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.parsing_json);
                json_text = (TextView) findViewById(R.id.json_text);



                try{
                        JSONObject json = new JSONObject(jsonString);

                        jsonObject = json.getJSONObject(TagLander);

                        String sprache = jsonObject.getString(Tag_Sprache).toString();

                        char  a = 'A';
                        char z = 'Z';
                        for(char i1=a; i1<=z;i1++) {
                             jsonArrayCountry = jsonObject.optJSONArray(String.valueOf(i1));
                                for (int i = 0; i < jsonArrayCountry.length(); i++) {
                                        JSONObject objectCountry = jsonArrayCountry.getJSONObject(i);
                                        int id1 = Integer.parseInt(objectCountry.optString(Tag_Id).toString());
                                        String land1 = objectCountry.optString(Tag_Land).toString();
                                        String flag1 = objectCountry.optString(Tag_Flag).toString();
                                        data += "id: " + id1 + "\n " + land1 + "\n " + flag1 + "\n ";
                                }
                        }
                }catch (JSONException e){e.printStackTrace();}
                Toast.makeText(Parsing.this,  data, Toast.LENGTH_SHORT).show();
                json_text.setText(data);
                json_text.setMovementMethod(new ScrollingMovementMethod());
        }
}

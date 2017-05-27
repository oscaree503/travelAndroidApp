package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sncss.haemtravel.R;

/**
 * Created by developer1 on 29/2/16.
 */
public class Allgemeine extends Fragment {
    public Allgemeine() {
    }
    private static TextView bullet2_1,bullet2_2,bullet2_3,bullet2_4,bullet2_5,
            bullet1,
            bullet2,
            bullet3,
            bullet4,
            bullet5,
            bullet6,
            bullet7,
            bullet8,
            bullet9,
            bullet10,
            bullet11,
            bullet12,
            bullet13,
            bullet14,
            bullet15,
            bullet16,
            bullet17,
            bullet18,
            bullet19,
            bullet20,
            bullet21,
            bullet22,
            bullet23,
            bullet24,
            bullet25,
            bullet26,
            bullet27,
            bullet28,
            bullet29,
            bullet30,
            bullet31,
            bullet32,
            bullet33,
            bullet34,
            bullet35,orpax;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View v= inflater.inflate(R.layout.allgemeine, container, false);

        bullet2_1 = (TextView)v.findViewById(R.id.bullet2_1);
        bulletStyle2(bullet2_1);
        bullet2_2 = (TextView)v.findViewById(R.id.bullet2_2);
        bulletStyle2(bullet2_2);
        bullet2_3 = (TextView)v.findViewById(R.id.bullet2_3);
        bulletStyle2(bullet2_3);
        bullet2_4 = (TextView)v.findViewById(R.id.bullet2_4);
        bulletStyle2(bullet2_4);
        bullet2_5 = (TextView)v.findViewById(R.id.bullet2_5);
        bulletStyle2(bullet2_5);
        bullet1 = (TextView)v.findViewById(R.id. bullet1);
        bulletStyleHead( bullet1);
        bullet2 = (TextView)v.findViewById(R.id. bullet2);
        bulletStyle2(bullet2);
        bullet3 = (TextView)v.findViewById(R.id. bullet3);
        bulletStyleHead(bullet3);
        bullet4 = (TextView)v.findViewById(R.id. bullet4);
        bulletStyleHead(bullet4);
        bullet5 = (TextView)v.findViewById(R.id. bullet5);
        bulletStyleHead( bullet5);
        bullet6 = (TextView)v.findViewById(R.id. bullet6);
        bulletStyleHead(bullet6);
        bullet7 = (TextView)v.findViewById(R.id. bullet7);
        bulletStyleHead( bullet7);
        bullet8 = (TextView)v.findViewById(R.id. bullet8);
        bulletStyleHead(bullet8);
        bullet9 = (TextView)v.findViewById(R.id. bullet9);
        bulletStyleHead(bullet9);
        bullet10 = (TextView)v.findViewById(R.id.bullet10);
        bulletStyleHead(bullet10);
        bullet11 = (TextView)v.findViewById(R.id.bullet11);
        bulletStyleHead(bullet11);
        bullet12 = (TextView)v.findViewById(R.id.bullet12);
        bulletStyleHead(bullet12);
        bullet13 = (TextView)v.findViewById(R.id.bullet13);
        bulletStyleHead(bullet13);
        bullet14 = (TextView)v.findViewById(R.id.bullet14);
        bulletStyleHead(bullet14);
        bullet15 = (TextView)v.findViewById(R.id.bullet15);
        bulletStyleHead(bullet15);
        bullet16 = (TextView)v.findViewById(R.id.bullet16);
        bulletStyleHead(bullet16);
        bullet17 = (TextView)v.findViewById(R.id.bullet17);
        bulletStyleHead(bullet17);
        bullet18 = (TextView)v.findViewById(R.id.bullet18);
        bulletStyleHead(bullet18);
        bullet19 = (TextView)v.findViewById(R.id.bullet19);
        bulletStyleHead(bullet19);
        bullet20 = (TextView)v.findViewById(R.id.bullet20);
        bulletStyleHead(bullet20);
        bullet21 = (TextView)v.findViewById(R.id.bullet21);
        bulletStyleHead(bullet21);
        bullet22 = (TextView)v.findViewById(R.id.bullet22);
        bulletStyleHead(bullet22);
        bullet23 = (TextView)v.findViewById(R.id.bullet23);
        bulletStyleHead(bullet23);
        bullet24 = (TextView)v.findViewById(R.id.bullet24);
        bulletStyleHead(bullet24);
        bullet25 = (TextView)v.findViewById(R.id.bullet25);
        bulletStyleHead(bullet25);
        bullet26 = (TextView)v.findViewById(R.id.bullet26);
        bulletStyleHead(bullet26);
        bullet27 = (TextView)v.findViewById(R.id.bullet27);
        bulletStyleHead(bullet27);
        bullet28 = (TextView)v.findViewById(R.id.bullet28);
        bulletStyleHead(bullet28);
        bullet29 = (TextView)v.findViewById(R.id.bullet29);
        bulletStyleHead(bullet29);
       /* bullet30 = (TextView)v.findViewById(R.id.bullet30);
        bulletStyleHead(bullet30);*/
        bullet31 = (TextView)v.findViewById(R.id.bullet31);
        bulletStyleHead(bullet31);
        bullet32 = (TextView)v.findViewById(R.id.bullet32);
        bulletStyleHead(bullet32);
        bullet33 = (TextView)v.findViewById(R.id.bullet33);
        bulletStyleHead(bullet33);
        bullet34 = (TextView)v.findViewById(R.id.bullet34);
        bulletStyleHead(bullet34);
        bullet35 = (TextView)v.findViewById(R.id.bullet35);
        bulletStyleHead(bullet35);
        orpax=(TextView)v.findViewById(R.id.orpax);
        orpax.setText(Html.fromHtml(getString(R.string.ohropax)));
        return v;
    }
    public void bulletStyleHead(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_head)));

    }
    public void bulletStyle2(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_string)));

    }
}

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
public class Spezielle extends Fragment {
    private static TextView  aus, zollo1,allgei,
            oct,
            ggf,
            ckyklo,
            zurIn,
            schmer,
            biitte,
            kuhl,
            venen,
            einwe,
            anderes,
            stimmen,
            wichtig,
            transport,
            bechten,bullet2,bullet3, bullet4,bullet5,bullet6,bullet7,bulletZollo,
            tippDialog2;
    public Spezielle() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */

        View v=  inflater.inflate( R.layout.spezielle, container, false);
        aus = (TextView)v.findViewById(R.id.aus);
        aus.setText(Html.fromHtml(getString(R.string.ausreichend_g)));

        zollo1 = (TextView)v.findViewById(R.id.zollo1);
        zollo1.setText(Html.fromHtml(getString(R.string.zollo)));

        oct = (TextView)v.findViewById(R.id. oct);
        oct.setText(Html.fromHtml(getString(R.string.octostim)));

        ggf = (TextView)v.findViewById(R.id.ggf);
        ggf.setText(Html.fromHtml(getString(R.string.ggf)));

        ckyklo = (TextView)v.findViewById(R.id. ckyklo);
        ckyklo.setText(Html.fromHtml(getString(R.string.cyklok)));

        zurIn = (TextView)v.findViewById(R.id.zurInitialen);
        zurIn.setText(Html.fromHtml(getString(R.string.zur)));

        schmer = (TextView)v.findViewById(R.id.schmer);
        schmer.setText(Html.fromHtml(getString(R.string.schmer)));

        biitte = (TextView)v.findViewById(R.id.bitteBeach);
        biitte.setText(Html.fromHtml(getString(R.string.bitteBeach)));

        kuhl = (TextView)v.findViewById(R.id.kul);
        kuhl.setText(Html.fromHtml(getString(R.string.kul)));

        venen = (TextView)v.findViewById(R.id.venen);
        venen.setText(Html.fromHtml(getString(R.string.venen)));

        einwe = (TextView)v.findViewById(R.id.einwe);
        einwe.setText(Html.fromHtml(getString(R.string.einweg)));

        anderes = (TextView)v.findViewById(R.id.anderes);
        anderes.setText(Html.fromHtml(getString(R.string.andr)));

        stimmen = (TextView)v.findViewById(R.id.stimmen);
        stimmen.setText(Html.fromHtml(getString(R.string.stimmen)));

        wichtig = (TextView)v.findViewById(R.id. wichtig);
        wichtig.setText(Html.fromHtml(getString(R.string.wichtig)));

        transport = (TextView)v.findViewById(R.id.transport_und);
        transport.setText(Html.fromHtml(getString(R.string.transport_und_lagerung)));
        bulletZollo = (TextView)v.findViewById(R.id.bulletZollo);
        bechten = (TextView)v.findViewById(R.id.bechten);
        bechten.setText(Html.fromHtml(getString(R.string.beachten)));
        bullet2 = (TextView)v.findViewById(R.id.bullet2);
        bulletStyleHead(bullet2);
         bullet3 = (TextView)v.findViewById(R.id.bullet3);
         bullet4 = (TextView)v.findViewById(R.id.bullet4);
         bullet5 = (TextView)v.findViewById(R.id.bullet5);
         bullet6 = (TextView)v.findViewById(R.id.bullet6);
        bullet7 = (TextView)v.findViewById(R.id.bullet7);
        bulletStyle2(bulletZollo);
        bulletStyle2(bullet3);
        bulletStyle2(bullet4);
        bulletStyle2(bullet5);
        bulletStyle2(bullet6);
        bulletStyle2(bullet7);




        return v;
    }
    public void bulletStyleHead(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_head)));

    }
    public void bulletStyle2(TextView bv){
        bv.setText(Html.fromHtml(getString(R.string.bullet_string)));

    }
}

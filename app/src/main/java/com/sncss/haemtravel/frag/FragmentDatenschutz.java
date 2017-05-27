package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sncss.haemtravel.R;

/**
 * Created by SONY on 23-04-2016.
 */
public class FragmentDatenschutz extends Fragment {
    TextView dat_para6_bulletT,bullet1,bullet2, bullet3;
    public FragmentDatenschutz(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datenschutz,container,false);
       /* dat_para6_bulletT = (TextView)view.findViewById(R.id.dat_para6_bullet);
        dat_para6_bulletT.setText(Html.fromHtml(getString(R.string.dat_para6_bullet)));*/
        bullet1 = (TextView)view.findViewById(R.id.bullet1);
        bullet2 = (TextView)view.findViewById(R.id.bullet2);
        bullet3 = (TextView)view.findViewById(R.id.bullet3);
        bullet1.setText(Html.fromHtml(getString(R.string.bullet_head)));
        bullet2.setText(Html.fromHtml(getString(R.string.bullet_head)));
        bullet3.setText(Html.fromHtml(getString(R.string.bullet_head)));


        return view;
    }
}

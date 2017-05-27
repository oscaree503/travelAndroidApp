package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sncss.haemtravel.R;


/**
 * Created by SONY on 23-04-2016.
 */

public class FragmentKontakt extends Fragment {
    public FragmentKontakt(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontakt,container,false);
        return view;
    }
}

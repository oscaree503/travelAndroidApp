package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sncss.haemtravel.R;

/**
 * Created by SONY on 23-04-2016.
 */
public class FragmentRechtliches extends Fragment {

    public FragmentRechtliches(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rechtliches,container,false);
        return view;
    }
}

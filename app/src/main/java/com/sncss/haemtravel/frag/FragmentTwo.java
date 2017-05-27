package com.sncss.haemtravel.frag;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.DiagnoseBehandlungGetSet;
import com.sncss.haemtravel.Bean.MeineDatenGetSet;
import com.sncss.haemtravel.MainActivity;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.database.SettingDatabase;

import java.util.List;


public class FragmentTwo extends Fragment {
    public FragmentTwo() {
    }

    SettingDatabase settingDatabase;
    List<DiagnoseBehandlungGetSet> diagnoseBehandlungGetList;
    private static String
            diagnose_sdb,
            restaktivitat_sdb,
            therapieart_sdb,
            meinfaktor_sdb,
            dosierung_sdb,
            dosierungsfrequen_sdb;
    private static TextView
            diagnose,
            restaktivitat,
            therapieart,
            meinfaktor,
            dosierung,
            dosierungsfrequen;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View fragment_two = inflater.inflate(R.layout.fragment_two_update, container, false);
        settingDatabase = new SettingDatabase(getActivity());

        diagnose = (TextView) fragment_two.findViewById(R.id.diagnose);
        restaktivitat = (TextView) fragment_two.findViewById(R.id.restaktivitat);
        therapieart = (TextView) fragment_two.findViewById(R.id.therapieart);
        meinfaktor = (TextView) fragment_two.findViewById(R.id.meinFactor);
        dosierung = (TextView) fragment_two.findViewById(R.id.dosierung);
        dosierungsfrequen = (TextView) fragment_two.findViewById(R.id.dosierungsfrequen);

        try {
            diagnoseBehandlungGetList = settingDatabase.getAllDetails_DB();

            if (diagnoseBehandlungGetList.size() != 0) {
                for (int i = 0; i < diagnoseBehandlungGetList.size(); i++) {
                    diagnose_sdb = (diagnoseBehandlungGetList.get(i).getDiagnose());
                    restaktivitat_sdb = (diagnoseBehandlungGetList.get(i).getRestaktivitat());
                    therapieart_sdb = (diagnoseBehandlungGetList.get(i).getTherapieart());
                    meinfaktor_sdb = (diagnoseBehandlungGetList.get(i).getMeinfaktor());
                    dosierung_sdb = (diagnoseBehandlungGetList.get(i).getDosierung());
                    dosierungsfrequen_sdb = (diagnoseBehandlungGetList.get(i).getDosierungsfrequen());
                }
                diagnose.setText(diagnose_sdb);
                restaktivitat.setText(restaktivitat_sdb);
                therapieart.setText(therapieart_sdb);
                meinfaktor.setText(meinfaktor_sdb);
                dosierung.setText(dosierung_sdb);
                dosierungsfrequen.setText(dosierungsfrequen_sdb);
            }
        }
        catch (Exception e)
        {

        }
        return fragment_two;
    }
}

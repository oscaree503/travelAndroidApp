package com.sncss.haemtravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.Country;
import com.sncss.haemtravel.Bean.EntryItem;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.HamophiliezentrumSuchen;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.interfaces.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by developer1 on 17/3/16.
 * Creats the country List with the flag
 */
public class CountryListArrayAdapter extends ArrayAdapter<Country>  {

    private final List<Country> list;
    private ArrayList<Country> cArraylist;
    private LayoutInflater vi;
    View v,v1;
    Holder holder = null;
    Context context;
    private LinearLayout li;
   static class Holder {
        protected TextView name;
        protected ImageView flag;
        String flagId;

    }

    public CountryListArrayAdapter(Context context, List<Country> list) {
        super(context, R.layout.activity_countrycode_row, list);
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.context = context;
        this.cArraylist = new ArrayList<Country>();
        this.cArraylist.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      v = convertView;

        final Item i = list.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.list_item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.getTitle());
            } else {
                Country ei = (Country) i;
                this.holder = new Holder();
                v = vi.inflate(R.layout.list_item_enty, null);
                li = (LinearLayout) v.findViewById(R.id.idmain);
                final TextView title = (TextView) v.findViewById(R.id.list_item_entry_title);
                final ImageView imageView = (ImageView) v.findViewById(R.id.list_item_entry_drawable);
                this.holder.flagId = ei.getFlag();
                v.setTag(holder);
                if (title != null)
                    title.setText(ei.getName());
                if (imageView != null) {
                    int flagid = context.getResources().getIdentifier(ei.getFlag(), "drawable", context.getPackageName());
                    imageView.setBackgroundResource(flagid);
                }
            }
        }
        return  v;
    }
    public void filter(String charText) {
        //charText = charText;

        list.clear();
        if (charText.length() == 0) {
            list.addAll(cArraylist);
        } else {
            for (Country cA : cArraylist) {
                     if (cA.isSection()) {
                     } else {
                        if (cA.getName().contains(charText)||(cA.getName().toLowerCase().contains(charText.toLowerCase()))) {
                            list.add(cA);
                        }
                        }
                    }



            }
        notifyDataSetChanged();
    }
}

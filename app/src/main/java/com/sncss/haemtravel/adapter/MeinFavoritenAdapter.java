package com.sncss.haemtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.MeinFavorites;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.Bean.SectionItemM;
import com.sncss.haemtravel.CityPlaceDetail;
import com.sncss.haemtravel.MeinFavoriten;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.database.SettingDatabase;
import com.sncss.haemtravel.interfaces.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by developer1 on 30/3/16.
 * Used to create the Favrotie list
 */
public class MeinFavoritenAdapter extends ArrayAdapter<MeinFavorites>  {
Holder holder=null;
    private final List<MeinFavorites> centers;
    Context context;
    Typeface font;
    SettingDatabase sdb;
    private static LayoutInflater inflater=null;
    public  MeinFavoritenAdapter(Context context,List<MeinFavorites> meinFavorites)
    {
        super(context, R.layout.activity_meinfavratioe_row, meinFavorites);
        font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        this.centers=meinFavorites;
        this.context=context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder{
    TextView del;
        TextView countryname;
        TextView centerName;
        TextView center_iso;
        TextView center_id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

      View row = convertView;
        final Item i = centers.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItemM si = (SectionItemM) i;
                row = inflater.inflate(R.layout.list_item_section, null);
                row.setOnClickListener(null);
                row.setOnLongClickListener(null);
                row.setLongClickable(false);
                final TextView sectionView = (TextView) row.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.getTitle());
                /* v = vi.inflate(R.layout.side_index_item,null);
                 TextView tv = (TextView)v.findViewById(R.id.side_list_item);
                 tv.setText(si.getTitle());
                 tv.setTag(i);*/
            } else {
                row = inflater.inflate(R.layout.favoriten_listview, null);
                this.holder = new Holder();
                this.holder.countryname = (TextView) row.findViewById(R.id.textView22);
                this.holder.del = (TextView) row.findViewById(R.id.del);
                row.setTag(holder);

                this.holder.countryname.setText(centers.get(position).getCountry_name() + " - " + centers.get(position).getCenter_name());
                this.holder.del.setTypeface(font);
                this.holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sdb = new SettingDatabase(context);
                        sdb.delet_row_meinfavoriten(centers.get(position).getCenter_id());
                        centers.remove(centers.get(position));
                        notifyDataSetChanged();
                    }
                });
            }
        }
        return  row;
    }



}

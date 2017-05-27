package com.sncss.haemtravel.adapter;

/**
 * Created by developer1 on 10/3/16.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.EntryItem;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.interfaces.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class EntryAdapter extends ArrayAdapter<Item>  {

    Holder holder = null;
    private Context context;
    private ArrayList<Item> items, item1;
    private LayoutInflater vi;
    private LinearLayout li;
    SharedPreferences sharedPreferences;

    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public class Holder{
        String flagId;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
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
                EntryItem ei = (EntryItem) i;
                this.holder = new Holder();
                v = vi.inflate(R.layout.list_item_enty, null);
                li = (LinearLayout) v.findViewById(R.id.idmain);
                final TextView title = (TextView) v.findViewById(R.id.list_item_entry_title);
                final ImageView imageView = (ImageView) v.findViewById(R.id.list_item_entry_drawable);
                this.holder.flagId = ei.images;
                v.setTag(holder);
                if (title != null)
                    title.setText(ei.title);
                if (imageView != null) {
                    int flagid = context.getResources().getIdentifier(ei.images, "drawable", context.getPackageName());
                    imageView.setBackgroundResource(flagid);
                }
            }
        }
        return v;
    }

}

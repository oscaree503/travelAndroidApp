package com.sncss.haemtravel.adapter;


import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.RowItem;
import com.sncss.haemtravel.R;

/*
* Was used to create the list at checklist screen but currently of no use
*/
public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;

    public CustomBaseAdapter(Context context, List<RowItem> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView faCircle;
        TextView txtTitle;
        TextView faCircleArrow;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_tick_mark_with_arrow_circle_checklist, null);
            holder = new ViewHolder();
            holder.faCircleArrow = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.faCircle = (TextView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        RowItem rowItem = (RowItem) getItem(position);
        holder.faCircleArrow.setText(rowItem.getcircle_empty());
        holder.txtTitle.setText(rowItem.gettext());
        holder.faCircle.setText(rowItem.getarrow_circle());

        holder.faCircleArrow.setTypeface(font);
        holder.txtTitle.setTypeface(font);
        holder.faCircle.setTypeface(font);

        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}



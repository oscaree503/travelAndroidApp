package com.sncss.haemtravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.Bean.CenterListBean;
import com.sncss.haemtravel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 24-03-2016.
 * Adapter used to create the center List that is displays the name of the center and the distance.
 *
 */

public class CenterListAdapter extends BaseAdapter {
    private static ArrayList<CenterListBean> centers;
    Holder holder=null;
    private LayoutInflater clInflator;
    public class Holder{
        TextView centerName;
        TextView distance;

    }
    public CenterListAdapter(Context context , ArrayList<CenterListBean> centerlist) {
        clInflator = LayoutInflater.from(context);
        centers = centerlist;

    }

    @Override
    public int getCount() {
        return centers.size();
    }

    @Override
    public CenterListBean getItem(int index){
        return this.centers.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row  = clInflator.inflate(R.layout.activity_listview, null);
            this.holder=new Holder();
            this.holder.centerName=(TextView)row.findViewById(R.id.label);
            this.holder.distance=(TextView)row.findViewById(R.id.label2);
            row.setTag(holder);
        }else
        {
            holder=(Holder)row.getTag();
        }
        this.holder.centerName.setText(centers.get(position).getTitle());

        if(centers.get(position).getDistance().isEmpty())
            this.holder.distance.setText(centers.get(position).getStadt()+" - "+"0.00 km");
        else
            this.holder.distance.setText(centers.get(position).getStadt()+" - "+centers.get(position).getDistance());

       if(centers.get(position).getLat().matches("") || centers.get(position).getLogi().equals("") )
        {

        }
        else {

        }

        return row;
    }

}

package com.sncss.haemtravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.NotfallBean;
import com.sncss.haemtravel.Bean.TelefoneBean;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.utility.CheckCallpermission;

import java.util.ArrayList;

/**
 * Created by SONY on 21-04-2016.
 * used to show the multiple notfall number on the screen
 */
public class NotfallAdapter extends BaseAdapter{
    private static ArrayList<NotfallBean> NotfallList;
    Holder holder=null;
    private LayoutInflater clInflator;
    Context contextl;
    Activity al;
    public class Holder{
        TextView notfall_number;
        ImageView emergency;


    }
    public NotfallAdapter(Context context, ArrayList<NotfallBean> notfallList, Activity a) {
        clInflator = LayoutInflater.from(context);
        NotfallList = notfallList;
        contextl = context;
        al = a;

    }

    @Override
    public int getCount() {
        return NotfallList.size();
    }

    @Override
    public NotfallBean getItem(int index){
        return this.NotfallList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row  = clInflator.inflate(R.layout.notfall_list_cityplace, null);
            this.holder=new Holder();
            this.holder.notfall_number=(TextView)row.findViewById(R.id.notfall_number);
            this.holder.emergency=(ImageView)row.findViewById(R.id.emergency);
            row.setTag(holder);
        }else
        {
            holder=(Holder)row.getTag();
        }
        this.holder.notfall_number.setText(NotfallList.get(position).getNotfalls());
        if(this.holder.notfall_number.getText().toString().matches("")){
            this.holder.emergency.setImageResource(R.drawable.red_call_disable);
            this.holder.emergency.setEnabled(false);
        }
        this.holder.emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCall(NotfallList.get(position).getNotfalls());
            }
        });
        return row;
    }
    public void startCall(String num) {
        Intent intenta = new Intent(Intent.ACTION_CALL);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            boolean check = CheckCallpermission.checkPermission(contextl);
            if (!check) {
                CheckCallpermission.requestPermission(al);
            } else {
                intenta.setData(Uri.parse("tel:" + num));
                contextl.startActivity(intenta);

            }
        } else {
            intenta.setData(Uri.parse("tel:" + num));
            contextl.startActivity(intenta);

        }
    }
}


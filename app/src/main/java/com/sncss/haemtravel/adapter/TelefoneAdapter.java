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

import com.sncss.haemtravel.Bean.CenterListBean;
import com.sncss.haemtravel.Bean.TelefoneBean;
import com.sncss.haemtravel.CityPlaceDetail;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.utility.CheckCallpermission;

import java.util.ArrayList;

/**
 * Created by SONY on 21-04-2016.
 * creats the multiple telefone number on then cityplacedetial activity
 */
public class TelefoneAdapter extends BaseAdapter{
    private static ArrayList<TelefoneBean> telefonList;
    Holder holder=null;
    Context contextl;
    Activity al;
    private LayoutInflater clInflator;
    public class Holder{
        TextView telefone_number;
        ImageView telefone;
    }
    public TelefoneAdapter(Context context , ArrayList<TelefoneBean> telefoneList, Activity a) {
        clInflator = LayoutInflater.from(context);
        telefonList = telefoneList;
        contextl= context;
        al = a;

    }

    @Override
    public int getCount() {
        return telefonList.size();
    }

    @Override
    public TelefoneBean getItem(int index){
        return this.telefonList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row  = clInflator.inflate(R.layout.telefone_list_cityplace, null);
            this.holder=new Holder();
            this.holder.telefone_number=(TextView)row.findViewById(R.id.telefon_number);
            this.holder.telefone=(ImageView)row.findViewById(R.id.telefone);

            row.setTag(holder);
        }else
        {
            holder=(Holder)row.getTag();
        }
        this.holder.telefone_number.setText(telefonList.get(position).getTelefone());
        if(this.holder.telefone_number.getText().toString().matches("")){
            this.holder.telefone.setImageResource(R.drawable.blue_call_disable);
            this.holder.telefone.setEnabled(false);
        }
        this.holder.telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCall(telefonList.get(position).getTelefone());
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


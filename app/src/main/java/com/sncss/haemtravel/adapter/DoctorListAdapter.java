package com.sncss.haemtravel.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


import android.widget.TextView;
import android.widget.Toast;

import com.sncss.haemtravel.AddNewContact;
import com.sncss.haemtravel.Bean.CenterListBean;
import com.sncss.haemtravel.Bean.DoctorBean;
import com.sncss.haemtravel.Bean.EntryItem;
import com.sncss.haemtravel.Bean.SectionItem;
import com.sncss.haemtravel.CityPlaceDetail;
import com.sncss.haemtravel.R;
import com.sncss.haemtravel.interfaces.Item;

import java.util.ArrayList;

/**
 * Created by SONY on 26-03-2016.
 * Use to display the list of the doctors added by the user on the city place details
 */
public class DoctorListAdapter extends BaseAdapter {
    public class Holder{
        TextView email;
        TextView telefone;
        TextView docEdit;
        TextView docName;

    }
    Holder holder = null;
    private Context context;
    String country_name, center_name,center_ID,ISO;
    private ArrayList<DoctorBean> DocList;
    private LayoutInflater vi;
    private LinearLayout li;
    Typeface tf;
    SharedPreferences sharedPreferences;

    public DoctorListAdapter(Context context, ArrayList<DoctorBean> docList,String center_ID,String country_name,String center_name,String ISO ) {
        this.context = context;
        this.DocList = docList;
        this.center_ID = center_ID ;
        this.country_name = country_name;
        this.center_name= center_name;
        this.ISO = ISO;

         tf = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");

        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        try {
            return DocList.size();
        }catch (Exception e){
            return 0;
        }

    }
    @Override
    public DoctorBean getItem(int index){
        return this.DocList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row  = vi.inflate(R.layout.doctor_detail_list_view, null);
            this.holder=new Holder();
            this.holder.telefone=(TextView)row.findViewById(R.id.docPhone);
            this.holder.email=(TextView)row.findViewById(R.id.docMail);
            this.holder.docEdit=(TextView)row.findViewById(R.id.docEdit);
            this.holder.docName = (TextView)row.findViewById(R.id.docName);
            row.setTag(holder);
        }else
        {
            holder=(Holder)row.getTag();
        }
        holder.telefone.setTypeface(tf);
        holder.email.setTypeface(tf);
        holder.docEdit.setTypeface(tf);
        this.holder.docName.setText(DocList.get(position).getDocName());
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String[] aEmailList = {DocList.get(position).getEmail()};
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My subject");
                emailIntent.setType("plain/text");
                emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(emailIntent);
            }
        });
        holder.docEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewContact = new Intent(context, AddNewContact.class);
                addNewContact.putExtra("center_name", center_name);
                addNewContact.putExtra("country_name", country_name);
                addNewContact.putExtra("center_ID", center_ID);
                addNewContact.putExtra("do_contact", "edit_Contact");
                addNewContact.putExtra("name",DocList.get(position).getDocName());
                addNewContact.putExtra("email",DocList.get(position).getEmail());
                addNewContact.putExtra("telephoneCode",DocList.get(position).getTelefoneCode());
                addNewContact.putExtra("telephone",DocList.get(position).getTelefone());
                addNewContact.putExtra("ISO",ISO);
                addNewContact.putExtra("docId",DocList.get(position).getDocId());
                addNewContact.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(addNewContact);
            }
        });
        holder.telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCall("+"+DocList.get(position).getTelefoneCode()+DocList.get(position).getTelefone());
            }
        });

        return row;
    }
    public void startCall(String num) {
        Intent intenta = new Intent(Intent.ACTION_CALL);
        intenta.setData(Uri.parse("tel:" + num));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intenta);
    }



}

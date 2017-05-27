package com.sncss.haemtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sncss.haemtravel.Bean.EmailBean;
import com.sncss.haemtravel.Bean.TelefoneBean;
import com.sncss.haemtravel.R;

import java.util.ArrayList;

/**
 * Created by SONY on 21-04-2016.
 * Use to add the emails to the center on the cityplacedetail activity
 */
public class EmailAdapter extends BaseAdapter{
    private static ArrayList<EmailBean> EmailList;
    Holder holder=null;
    Context contextl;
    private LayoutInflater clInflator;
    public class Holder{
        TextView email_mul;
        ImageView email_icon;

    }
    public EmailAdapter(Context context, ArrayList<EmailBean> emailList) {
        clInflator = LayoutInflater.from(context);
        EmailList = emailList;
        contextl = context;

    }

    @Override
    public int getCount() {
        return EmailList.size();
    }

    @Override
    public EmailBean getItem(int index){
        return this.EmailList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row  = clInflator.inflate(R.layout.email_list_cityplace, null);
            this.holder=new Holder();
            this.holder.email_mul=(TextView)row.findViewById(R.id.email_mul);
            this.holder.email_icon=(ImageView)row.findViewById(R.id.email_icon);
            row.setTag(holder);
        }else
        {
            holder=(Holder)row.getTag();
        }
        this.holder.email_mul.setText(EmailList.get(position).getEamil());
        if(this.holder.email_mul.getText().toString().matches("")){
            this.holder.email_icon.setImageResource(R.drawable.mail_disable);
            this.holder.email_icon.setEnabled(false);
        }
        this.holder.email_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] aEmailList = {EmailList.get(position).getEamil()};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, aEmailList);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My subject");
                emailIntent.setType("plain/text");
                contextl.startActivity(emailIntent);
            }
        });
        return row;
    }

}


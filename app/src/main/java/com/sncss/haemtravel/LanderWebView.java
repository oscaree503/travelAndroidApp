package com.sncss.haemtravel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by developer1 on 18/3/16.
 */
public class LanderWebView extends Activity implements View.OnClickListener {
    private WebView webView;
    private static Bundle bundle;
    private static String back_press,country_code;
    private static Intent intent;
    private static TextView back_img, home_img,title_topbar_txt;
    ProgressDialog dialog;
    File laender, myDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lander_webview);
        bundle = getIntent().getExtras();

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home_img = (TextView)findViewById(R.id.btn_right_topbar);
        back_img = (TextView)findViewById(R.id.btn_left_topbar);
        final String countryName = bundle.getString("countryName");
        title_topbar_txt = (TextView)findViewById(R.id.title_topbar_txt);
        back_img.setOnClickListener(this);
        home_img.setOnClickListener(this);

        back_img.setText(R.string.fa_angle_left);
        home_img.setText(R.string.fa_home);
        title_topbar_txt.setText(countryName);

        back_img.setTypeface(font);
        home_img.setTypeface(font);
        country_code = bundle.getString("countryFlagCode");
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());

        /* geeting File from server**/

        getFile();

        TextView kontakt = (TextView)findViewById(R.id.kontakt);
        TextView datenschutz = (TextView)findViewById(R.id.datenschutz);
        TextView rechtliches = (TextView)findViewById(R.id.rechtliches);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LanderWebView.this,FooterFrg.class);
                in.putExtra("Frag","kontakt");
                in.putExtra("countryFlagCode",country_code);
                in.putExtra("countryName",countryName);
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                startActivity(in);
            }
        });
        datenschutz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LanderWebView.this, FooterFrg.class);
                in.putExtra("Frag", "datenschutz");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("countryFlagCode",country_code);
                in.putExtra("countryName",countryName);

                startActivity(in);
            }
        });
        rechtliches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LanderWebView.this, FooterFrg.class);
                in.putExtra("Frag", "rechtliches");
                in.putExtra("back_press",getIntent().getComponent().getClassName());
                in.putExtra("countryFlagCode",country_code);
                in.putExtra("countryName",countryName);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            back_press = bundle.getString("back_press");
            Class c = Class.forName(back_press);
            Intent i  = new Intent(LanderWebView.this,c );
            startActivity(i);
            finish();
        }catch(Exception e){
            intent = new Intent(LanderWebView.this, Landerinformationen.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * finding the file from server if connected to internet else showing the file from memory
     */
    public void getFile(){
        File myDir = getFilesDir();
        laender = new File(myDir+"/"+country_code+".html");
        dialog = ProgressDialog.show(LanderWebView.this, "", "", true);
        try{
            final FTPClient fm = new FTPClient();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
                        // ARE WE CONNECTED TO THE NET
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()) {
                            fm.connect("www.crm.de");
                            if (fm.login("oxington", "Oxi-CRM!")) {
                                fm.enterLocalPassiveMode();
                                // get details of a file or directory
                                String remoteFilePath = "xhtml/"+country_code+".html";
                                // File f = getFileStreamPath("haemtravel/laender.json");
                                OutputStream outputStream = null;
                                OutputStream outputStream1 = null;
                                outputStream = new BufferedOutputStream(new FileOutputStream(laender));
                                fm.retrieveFile(remoteFilePath, outputStream);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadView();
                                    }
                                });
                            }
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadView();
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            t.start();
        }catch (Exception e){

        }
    }

    /**
     * showing the flie stored in asset folder
     */
public void loadView(){
    myDir = getFilesDir();
    String url="file:///android_asset/"+country_code+".html";
    laender = new File(myDir+"/"+country_code+".html");
    byte[] buffer;
    try {
        if (laender.exists()) {
            url = "file:///" +laender;

        } else {
            url = "file:///android_asset/"+country_code+".html";
        }
    }catch (Exception e){

    }
    //loading webview
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl(url);
    webView.getSettings().setSupportMultipleWindows(true);
    webView.setWebChromeClient(new WebChromeClient() {
        @Override
        public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg) {
            WebView.HitTestResult result = view.getHitTestResult();
            String data = result.getExtra();
            Context context = view.getContext();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
            context.startActivity(browserIntent);
            return false;
        }
    });
}

    /**
     * Inner class to perform the action on click inside the webview
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("callto:")) {
               /* Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url.substring(7)));
                startActivity(intent);*/
                view.reload();
                return true;
            }

            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_right_topbar:
                startActivity(new Intent(LanderWebView.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_left_topbar:
                onBackPressed();
                break;
        }
    }
}

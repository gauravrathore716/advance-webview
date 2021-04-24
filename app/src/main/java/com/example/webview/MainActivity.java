package com.example.webview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sdsmdg.tastytoast.TastyToast;


public class MainActivity extends AppCompatActivity {


    ProgressDialog pd;

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView= findViewById(R.id.web);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        boolean connected = false;

        // webView.getSettings().setSupportZoom(true);
        ///    webView.getSettings().setBuiltInZoomControls(true);
        //      webView.loadUrl("http://easyonlineconverter.com/");
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        if (connected == true)

        {

        }

        else

        {

            TastyToast.makeText(getApplicationContext(), "No Internet Connection!", TastyToast.LENGTH_LONG, TastyToast.WARNING);

        }

        webView.setWebViewClient(new WebViewClient()

                                 {

                                     WebView view;

                                     @Override

                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);

                                         if (!Common.connectionAvailable(MainActivity.this))


                                         {

                                             Intent i = new Intent(MainActivity.this,ErrorActivty.class);
                                             i.putExtra("MSG",getString(R.string.checkinternet));
                                             startActivity(i);
                                             finish();

                                         }

                                         pd=new ProgressDialog(MainActivity.this);
                                         pd.setMessage(getString(R.string.loading));
                                         pd.show();
                                     }

                                     @Override

                                     public void onPageFinished(WebView webView,String url){

                                         super.onPageFinished(view ,url);
                                         if (pd!=null)
                                             pd.dismiss();

                                     }

                                     @Override
                                     public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error)
                                     {
                                         super.onReceivedError(view, request, error);
                                         Intent i =new Intent(MainActivity.this,ErrorActivty.class);
                                         i.putExtra("MSG",getString(R.string.somethingwrong)+error.toString());
                                         startActivity(i);
                                         finish();

                                     }

                                     @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

                                     @Override

                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                         view.loadUrl(request.getUrl().toString());
                                         return super.shouldOverrideUrlLoading(view, request);
                                     }
                                 }
        );
        webView.loadUrl("https://www.amazon.in/");

    }

    @Override
    public void onBackPressed() {

        webView = findViewById(R.id.web);
        if (webView.canGoBack())
            webView.goBack();

        else

            super.onBackPressed();
    }
}

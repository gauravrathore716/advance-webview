package com.example.webview;

import android.content.Context;
import android.net.ConnectivityManager;

class Common {

    static boolean connectionAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() !=null;

    }
}

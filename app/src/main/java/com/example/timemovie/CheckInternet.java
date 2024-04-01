package com.example.timemovie;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.net.ConnectException;

public class CheckInternet {

    public static String getNetworkInfo(Context context) {
        String status = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            status = "connected";
        }
        else {
            status = "desconnected";
        }
        return status;
    }
}

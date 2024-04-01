package com.example.timemovie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status= CheckInternet.getNetworkInfo(context);
        if (status.equals("connected")){
            Toast.makeText(context.getApplicationContext(), "Welcom to our app",Toast.LENGTH_LONG ).show();
        }
        else if (status.equals("desconnected")){
            Toast.makeText(context.getApplicationContext(), "Not connected",Toast.LENGTH_LONG ).show();
        }
    }
}

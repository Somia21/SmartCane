package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;


public class WifiOnCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Log.d("wifi","wifi on...");
        WifiManager wifiManager = (WifiManager) commandModel.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

    }

    @Override
    public String getDefaultPhrase() {
        return "Turn On WiFi, On WiFi, Please On WiFi, Mobile Wifi";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.WIFI_ON_Kea_Ja_Raha_Ha);
    }
}

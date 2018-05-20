package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class WifiOffCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Log.d("wifi","wifi off..");
        WifiManager wifiManager = (WifiManager) commandModel.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
    }

    @Override
    public String getDefaultPhrase() {
        return "Turn off Wifi, PLease off Wifi, Off Wifi";

    }
    @  Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.Wifi_Of_Kea_Ja_Raha_Ha);
    }
}

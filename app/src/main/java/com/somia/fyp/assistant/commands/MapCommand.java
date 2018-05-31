package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.somia.fyp.NewLocationSharing.MapsActivity;
import com.somia.fyp.NewLocationSharing.MyLocationService;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class MapCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Intent i = new Intent(commandModel.getContext(), MapsActivity.class);

        commandModel.getContext().startActivity(i);
        Intent intent = new Intent(commandModel.getContext(),MyLocationService.class);
        intent.setAction(MyLocationService.ACTIon_START);
        commandModel.getContext().startService(intent);
    }

    @Override
    public String getDefaultPhrase() {
        return "map,start map,give me a direction,start sharing my location,share my location";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.somia.fyp.NewLocationSharing.GetCurrentLocation;
import com.somia.fyp.NewLocationSharing.MapsActivity;
import com.somia.fyp.NewLocationSharing.MyLocationService;
import com.somia.fyp.UI.Activitys.singUpActivity;
import com.somia.fyp.assistant.CommandModel;
import com.somia.fyp.utial.MySharedPref;


public class ShareLocationCommand implements com.somia.fyp.assistant.Command {
    private String TAG="ShareLocationCommand";
    @Override
    public void execute(CommandModel commandModel) {
        Intent intent = new Intent(commandModel.getContext(), MyLocationService.class);
      commandModel.getContext().  startService(intent);
      commandModel.getContext().startActivity(new Intent(commandModel.getContext(), MapsActivity.class));
    }

    @Override
    public String getDefaultPhrase() {
        return "share loaction,share my Location";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return "sharing your MyLocation";
    }

    private void getLocation(final Context context){
        GetCurrentLocation.LocationResult locationResult = new GetCurrentLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                //Got the MyLocation!
                Log.d(TAG,"sening lcoation: "+location.getLatitude() +"getLongitude: "+location.getLongitude()+"to: "+ MySharedPref.getSavedObjectFromPreference(context,MySharedPref.SHARD_PREF_AUDIO_BOOK, singUpActivity.FAMILY_PHONE_NUMBER,String.class));
            }
        };
        GetCurrentLocation myLocation = new GetCurrentLocation();
        myLocation.getLocation(context, locationResult);
    }
}

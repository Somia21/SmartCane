package com.somia.fyp.LocationSharing;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.somia.fyp.UI.Activitys.singUpActivity;
import com.somia.fyp.utial.MySharedPref;

import java.util.Timer;

/**
 * Created by Somia on 4/24/2018.
 */

public class MyLocationService extends Service {
Timer timer;
public static final String ACTION_STOP="ACTION_STOP";
public static final String ACTIon_START ="ACTIon_START";
private String TAG ="TAG";
private String userUiqID;
    private Handler handler;
    private Runnable runnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

     if (intent.getAction().equals(ACTIon_START)){
         Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
         userUiqID= MySharedPref.getSavedObjectFromPreference(getApplicationContext(),MySharedPref.SHARD_PREF_AUDIO_BOOK,singUpActivity.FAMILY_PHONE_NUMBER,String.class);
         if (userUiqID==null)
             userUiqID= Settings.Secure.getString(getApplicationContext().getContentResolver(),
                     Settings.Secure.ANDROID_ID);
         getLocation(MyLocationService.this);
         handler = new Handler();

         runnable = new Runnable() {
             @Override
             public void run() {
                 getLocation(MyLocationService.this);
                 handler.postDelayed(this, 10000);
             }
         };
         handler.postDelayed(runnable,10000);
     }else if (intent.getAction().equals(ACTION_STOP))
     {
         stopSelf();
     }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
    private void getLocation(final Context context){

        GetCurrentLocation.LocationResult locationResult = new GetCurrentLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                //Got the MyLocation!
                MyLocation myLocation = new MyLocation(location.getLongitude(),location.getLatitude());
                WriteToDatabase writeToDatabase = new WriteToDatabase();
                writeToDatabase.saveToDabase(myLocation,userUiqID);
                Log.d(TAG,"sening lcoation: "+location.getLatitude() +"getLongitude: "+location.getLongitude()+"to: "+ MySharedPref.getSavedObjectFromPreference(context,MySharedPref.SHARD_PREF_AUDIO_BOOK, singUpActivity.FAMILY_PHONE_NUMBER,String.class));
            }
        };
        GetCurrentLocation myLocation = new GetCurrentLocation();
        myLocation.getLocation(context, locationResult);
    }
    @Override
    public void onDestroy() {
        if (handler!=null){
            handler.removeCallbacks(runnable);
        }
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
package com.somia.fyp.tensorflowv1;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;


public class TTSService extends Service {
    TTSHelper helper;


    public TTSService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null && intent.getAction().equals("STOP")) {
            stopSelf();
        } else {

            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle("Object Recoginition");

            builder.setContentIntent(PendingIntent.getActivity(this, 12343, new Intent(this, StopTTS.class), 0));

///*          builder.setOngoing(true);
//            builder.setSmallIcon(R.drawable.ger);*/
            builder.addAction(0, "stop",
                    PendingIntent.getService(this, 51251, new Intent(getApplicationContext(),TTSService.class).setAction("STOP"), 0));

            startForeground(12342, builder.build());
            try {
                helper.stop();
            } catch (Exception e) {
            }
            if (intent != null) {
                helper = new TTSHelper(this, intent.getStringExtra("toSpeak"), intent.getStringExtra("Language"), LocalBroadcastManager.getInstance(this));
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        try {
            helper.stop();
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
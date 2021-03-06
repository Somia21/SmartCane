package com.somia.fyp.tensorflowv1;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class TTSHelper extends UtteranceProgressListener implements TextToSpeech.OnInitListener {
    private final String language;
    private TextToSpeech tts;
    private Context context;
    private String toSpeak;
    private LocalBroadcastManager localBroadcastManager;


    public TTSHelper(Context context, String toSpeak, String language,LocalBroadcastManager localBroadcastManager) {
        this.context = context;
        tts = new TextToSpeech(context, this);
        tts.setOnUtteranceProgressListener(this);
        this.toSpeak = toSpeak;
        this.language=language;
        this.localBroadcastManager=localBroadcastManager;
    }

    public void stop() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                result = tts.setLanguage(Locale.forLanguageTag(language));
            }

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "language_not_supported", Toast.LENGTH_LONG).show();
            } else {
                speakOut();
            }

        } else {
            Toast.makeText(context, "tts_failed Error code "+status, Toast.LENGTH_LONG).show();
            if(status == -1) //
            {
                // this is will open play store if use have not install or disable google text to speach
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.tts"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } catch (android.content.ActivityNotFoundException anfe) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.tts"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }else {
                Toast.makeText(context, "please make sure you select Google text to speech in next screen ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction("com.android.settings.TTS_SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }

    }

    private void speakOut() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSpeak);
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, map);
    }

    @Override
    public void onStart(String s) {

    }

    @Override
    public void onDone(String s) {
        Intent intent = new Intent("com.service.result");
        if(s != null)
            intent.putExtra("com.service.message", s);
        localBroadcastManager.sendBroadcast(intent);
        context.stopService(new Intent(context, TTSService.class));
    }

    @Override
    public void onError(String s) {

    }
}
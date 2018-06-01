package com.somia.fyp.utial;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Somia on 4/24/2018.
 */

public class MySharedPref {
    public static final String SHARD_PREF_AUDIO_BOOK="com.somia.fyp.MySharedPref";
    public static final String SHARD_PREF_KEY_BLIND_BUTTON_CLICKED="SHARD_PREF_KEY_BLIND_BUTTON_CLICKED";
    public static final String SHARD_PREF_KEY_FAMILY_BUTTON_CLICKED="SHARD_PREF_KEY_FAMILY_BUTTON_CLICKED";
   // com.ameerhamza6733.okAmeer.MySharedPref
    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey,
                                                    Object object) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static String getSavedObjectFromPreference(Context context, String preferenceFileName,
                                                      String preferenceKey, Class<String> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, "0"), classType);
        }
        return null;
    }

}
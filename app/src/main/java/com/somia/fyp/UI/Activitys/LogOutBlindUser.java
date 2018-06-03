package com.somia.fyp.UI.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Somia on 6/3/2018.
 */

public class LogOutBlindUser extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(LogOutBlindUser.this,FamilyorBlindUser.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        LogOutBlindUser.this.startActivity(intent);
    }
}

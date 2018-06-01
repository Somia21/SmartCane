package com.somia.fyp.UI.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.somia.fyp.NewLocationSharing.MapsActivity;
import com.somia.fyp.NewLocationSharing.NewMapsFamily;
import com.somia.fyp.R;
import com.somia.fyp.utial.MySharedPref;

import java.nio.channels.NonWritableChannelException;

/**
 * Created by Somia on 5/27/2018.
 */

public class SignInFamily extends Activity {


    private EditText Myphone;
    private Button FsignUp;
    public static String FAMILY_PHONE_NUMBER;

    private String TAG = "sing up activity tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_familymember);
        MySharedPref.saveObjectToSharedPreference(this,MySharedPref.SHARD_PREF_AUDIO_BOOK,MySharedPref.SHARD_PREF_KEY_FAMILY_BUTTON_CLICKED,"1");

//        String userUiqID = MySharedPref.getSavedObjectFromPreference(getApplicationContext(), MySharedPref.SHARD_PREF_AUDIO_BOOK,
//                SignInFamily.FAMILY_PHONE_NUMBER, String.class);
//        SharedPreferences prefs = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
//        if(userUiqID!=null)
//        {
//            startActivity(new Intent(SignInFamily.this, NewMapsFamily.class));
//            Toast.makeText(SignInFamily.this, "First Run", Toast.LENGTH_LONG)
//                    .show();
//        }


        Myphone=findViewById(R.id.addYourPhoneNumber);
        FsignUp=findViewById(R.id.signupfamilyuser);

        FsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserfillAllFileds())
                {
                    Toast.makeText(SignInFamily.this,"please fill all required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                MySharedPref.saveObjectToSharedPreference(getApplicationContext(),MySharedPref.SHARD_PREF_AUDIO_BOOK,FAMILY_PHONE_NUMBER,Myphone.getText().toString());
                Intent i = new Intent(SignInFamily.this, NewMapsFamily.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
    private boolean isUserfillAllFileds() {
        return Myphone.getText().toString().isEmpty();
    }
}

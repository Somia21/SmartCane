package com.somia.fyp.UI.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.somia.fyp.R;
import com.somia.fyp.utial.MySharedPref;

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
                Intent i = new Intent(SignInFamily.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
    private boolean isUserfillAllFileds() {
        return Myphone.getText().toString().isEmpty();
    }
}

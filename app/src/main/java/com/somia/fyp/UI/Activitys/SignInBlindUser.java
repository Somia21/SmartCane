package com.somia.fyp.UI.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.somia.fyp.R;
import com.somia.fyp.utial.MySharedPref;

/**
 * Created by Somia on 5/27/2018.
 */

public class SignInBlindUser extends Activity {

    private EditText phoneFamily;
    private Button msignUp;
    public static String FAMILY_PHONE_NUMBER;

    private String TAG = "sing up activity tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_blinduser);

        phoneFamily=findViewById(R.id.addFamilyPhoneNumber);
        msignUp=findViewById(R.id.signupblinduser);

        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserfillAllFileds())
                {
                    Toast.makeText(SignInBlindUser.this,"please fill all required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                MySharedPref.saveObjectToSharedPreference(getApplicationContext(),MySharedPref.SHARD_PREF_AUDIO_BOOK,FAMILY_PHONE_NUMBER,phoneFamily.getText().toString());
                Intent i = new Intent(SignInBlindUser.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
    private boolean isUserfillAllFileds() {
        return phoneFamily.getText().toString().isEmpty();
    }
}


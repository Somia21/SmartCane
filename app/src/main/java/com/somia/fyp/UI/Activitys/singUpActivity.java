package com.somia.fyp.UI.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.somia.fyp.R;
import com.somia.fyp.utial.MySharedPref;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class singUpActivity extends Activity {
    TextView signinhere;
    Typeface fonts1;
    private EditText userName;
    private EditText Email;
    private EditText password;
    private EditText phone;
    private com.dd.processbutton.iml.ActionProcessButton msignUp;
    public static String FAMILY_PHONE_NUMBER;

    private FirebaseAuth mAuth;
    private String TAG = "sing up activity tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_phone_number);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
        signinhere = (TextView) findViewById(R.id.signinhere);
        userName = (EditText) findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        phone=findViewById(R.id.addPhoneNumber);
        password = (EditText) findViewById(R.id.password);
        msignUp = (com.dd.processbutton.iml.ActionProcessButton) findViewById(R.id.signup1);



        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserfillAllFileds())
                {
                    Toast.makeText(singUpActivity.this,"please fill all required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                MySharedPref.saveObjectToSharedPreference(getApplicationContext(),MySharedPref.SHARD_PREF_AUDIO_BOOK,FAMILY_PHONE_NUMBER,phone.getText().toString());
                creatAccoutInFirebase();
            }
        });
        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(singUpActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });

        fonts1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/Lato-Regular.ttf");


        signinhere.setTypeface(fonts1);
    }

    private boolean isUserfillAllFileds() {
        return userName.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || password.getText().toString().isEmpty();
    }

    private void creatAccoutInFirebase() {
        msignUp.setLoadingText("creating account...");
       msignUp. setMode(ActionProcessButton.Mode.ENDLESS);
        msignUp.setProgress(1);
        mAuth.createUserWithEmailAndPassword(Email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            singUpActivity.this.msignUp.setProgress(0);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(singUpActivity.this, "Authentication failed."+task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            singUpActivity.this.msignUp.setProgress(0);
                            updateUI(null);
                        }


                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        if(user!=null){
            Toast.makeText(this,"Your account has been created",Toast.LENGTH_LONG).show();
            Intent i = new Intent(singUpActivity.this, LoginActivity.class);
            i.putExtra(LoginActivity.ExtraEmail,user.getEmail());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }


    }
}

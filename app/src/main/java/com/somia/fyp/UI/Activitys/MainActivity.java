package com.somia.fyp.UI.Activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.somia.fyp.R;
import com.somia.fyp.UI.fragment.VoiceRecgonizationFragment;
import com.somia.fyp.assistant.commands.Receivers.SendSmsActivity;
import com.somia.fyp.Interfacess.IGoogleSpeechRecognzerError;
import com.somia.fyp.utial.MySharedPref;
import com.somia.fyp.utial.SpeechRecognizerManager;
import com.somia.fyp.utial.MyTextToSpeech;
import com.github.clans.fab.FloatingActionButton;

import lolodev.permissionswrapper.callback.OnRequestPermissionsCallBack;
import lolodev.permissionswrapper.wrapper.PermissionWrapper;


public class MainActivity extends AppCompatActivity implements IGoogleSpeechRecognzerError,SpeechRecognizerManager.OnMagicWordListener{
    private VoiceRecgonizationFragment newIntance;
    private SpeechRecognizerManager mSpeechRecognizerManager;
    private boolean isSpeekButtonPressed=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySharedPref.saveObjectToSharedPreference(this,MySharedPref.SHARD_PREF_AUDIO_BOOK,MySharedPref.SHARD_PREF_KEY_BLIND_BUTTON_CLICKED,"1");
//         Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                .getBoolean("isFirstRun", true);
//
//        if (isFirstRun) {
//            //show start activity
//
//            startActivity(new Intent(MainActivity.this, SignInBlindUser.class));
//            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
//                    .show();
//        }
//else{
//            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
//                    .putBoolean("isFirstRun", false).commit();
//        }

      ImageView mSpeakButton = (ImageView) findViewById(R.id.imageView);

        try {
            MyTextToSpeech.intiTextToSpeech(this, "hi", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkPermissionAndProcess();

        mSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSpeekButtonPressed=true;
                checkPermissionAndProcess();
            }
        });

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            if (event.getAction() == KeyEvent.ACTION_DOWN){

                super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DPAD_UP));
                super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_DPAD_UP));
                Toast.makeText(this, "Turning On Voice Assistant", Toast.LENGTH_SHORT).show();
                checkPermissionAndProcess();
                return true;
            }}
        return super.dispatchKeyEvent(event);
    };

    private void checkPermissionAndProcess() {
        try {
            if(mSpeechRecognizerManager!=null){
                mSpeechRecognizerManager.destroy();
                mSpeechRecognizerManager=null;
            }
        }catch (Exception e){}
        if (Build.VERSION.SDK_INT > 22) {

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                askRunTimePermissions();
                return;
            }else{
                GoogleSpeechOROwn();
            }

        } else{
            GoogleSpeechOROwn();

        }
    }

    private void GoogleSpeechOROwn() {
//        if (isSpeekButtonPressed){
//            showVoiceFragment();
//        }else{
//            registerSpeechRecognizer();
//        }
        showVoiceFragment();
    }

    private void showVoiceFragment() {
        try {
            if(!isFinishing()){

                FragmentTransaction transactionFragment = getSupportFragmentManager().beginTransaction();
                newIntance = VoiceRecgonizationFragment.newInstance("en-IN", false, true);
                newIntance.setStyle(1, R.style.AppTheme);
                transactionFragment.add(android.R.id.content, newIntance).addToBackStack(null).commitAllowingStateLoss();

            }

        }catch (Exception e){
            Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(int Error) {
        Log.d(getClass().getSimpleName(),"onError"+Error);
        if(newIntance!=null)
            getSupportFragmentManager().beginTransaction().remove(newIntance).commitAllowingStateLoss();

        registerSpeechRecognizer();

    }

    private void askRunTimePermissions() {

        new PermissionWrapper.Builder(this)
                .addPermissions(new String[]{ Manifest.permission.RECORD_AUDIO})
                //enable rationale message with a custom message
                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosed
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        Log.i(SendSmsActivity.class.getSimpleName(), "Permission was granted.");
                        GoogleSpeechOROwn();
                    }
                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(MainActivity.this,"App need permission ",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).build().request();
    }

    @Override
    public void OnMagicWordDeceted(String word) {
        if (mSpeechRecognizerManager!=null){
            mSpeechRecognizerManager.destroy();
            mSpeechRecognizerManager=null;
        }
        showVoiceFragment();
    }

    @Override
    protected void onPause() {
        try {
            if (mSpeechRecognizerManager!=null){
                mSpeechRecognizerManager.destroy();
                mSpeechRecognizerManager=null;
            }

        }catch (Exception e){e.printStackTrace();}
        Log.d(getClass().getSimpleName(),"onPause");
        super.onPause();

    }

    @Override
    protected void onResume() {
        registerSpeechRecognizer();
        super.onResume();
    }

    private void registerSpeechRecognizer() {
        try {
            if(mSpeechRecognizerManager==null){
                mSpeechRecognizerManager =new SpeechRecognizerManager(MainActivity.this);
                mSpeechRecognizerManager.setOnResultListner(MainActivity.this);
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
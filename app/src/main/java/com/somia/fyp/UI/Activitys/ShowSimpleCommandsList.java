package com.somia.fyp.UI.Activitys;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.somia.fyp.R;
import com.somia.fyp.UI.fragment.HelpFragment;
import com.somia.fyp.utial.MyTextToSpeech;

import java.util.Arrays;
import java.util.List;

public class ShowSimpleCommandsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_commands_list);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, new HelpFragment(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        List<String> Lines = Arrays.asList(getResources().getStringArray(R.array.help_tts_phrses));
        try {
            MyTextToSpeech.intiTextToSpeech(this,"hi",Lines.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

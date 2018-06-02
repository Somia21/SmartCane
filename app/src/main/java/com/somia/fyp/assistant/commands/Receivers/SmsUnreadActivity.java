package com.somia.fyp.assistant.commands.Receivers;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.somia.fyp.R;
import com.somia.fyp.UI.fragment.VoiceRecgonizationFragment;
import com.somia.fyp.Interfacess.INoNeedCommander;
import com.somia.fyp.Interfacess.IGoogleSpeechRecognzerError;
import com.somia.fyp.utial.SMSUtils;
import com.somia.fyp.utial.SmsMmsMessage;
import com.somia.fyp.utial.TTSService;
import com.somia.fyp.utial.MyTextToSpeech;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;
import lolodev.permissionswrapper.callback.OnRequestPermissionsCallBack;
import lolodev.permissionswrapper.wrapper.PermissionWrapper;

public class SmsUnreadActivity extends AppCompatActivity implements INoNeedCommander,IGoogleSpeechRecognzerError {

    private Handler handler;
    private Runnable runnable;
    private Runnable mTextTOSpeechrunnable;
    private Handler mTextToSpeechHandler;

    private VoiceRecgonizationFragment newIntance;
    private String[] mUrduPositiveWords = {"read","yes","please read","yas"};
           // {"جی ہاں","ہاں", "جی", "پڑھو","سناو" ,"سناؤ"};
   // private String[] mRomanUrdoPositiveWords = {"ji haan","ha","yas",};
    private boolean userWantToReadorNot = false;
    private ArrayList<SmsMmsMessage> unread;
    private ArrayList<Card> cards;
    private CardListView listView;
    private CardArrayAdapter mCardArrayAdapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_unread);

        cards = new ArrayList<Card>();
        if (Build.VERSION.SDK_INT > 22) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                askRunTimePermissions();
                return;
            } else
                getUnreadSMSandUpdateUI();
        } else
            getUnreadSMSandUpdateUI();


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s = intent.getStringExtra("com.service.message");
                showVoiceRegoniztionFragment();
            }
        };

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            if (event.getAction() == KeyEvent.ACTION_DOWN){
                super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DPAD_UP));
                super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_DPAD_UP));
                showVoiceRegoniztionFragment();
                return true;
            }}
        return super.dispatchKeyEvent(event);
    };


    private void getUnreadSMSandUpdateUI() {
        unread = SMSUtils.getUnreadMessages(this);
        String text = "";
        if (unread == null || unread.size() < 1) {
            Log.d("First", "getUnreadSMSandUpdateUI: ");
            Toast.makeText(this, "no message to read ", Toast.LENGTH_SHORT).show();
            intiTextToSpeech("hi", "aapake paas koee unread message nahin hai");

        } else if (unread.size() >=1) {
            Collections.reverse(unread);
            for (SmsMmsMessage message : unread) {
                Card card = new Card(this);
                CardHeader header = new CardHeader(this);
                header.setTitle(message.getContactName());
                card.addCardHeader(header);
               // SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
               // String dateString = formatter.format(new Date(Long.parseLong(String.valueOf(message.getTimestamp()))));
                card.setTitle(message.getMessageBody() );
                        //+ " time : " + dateString);
                cards.add(card);

            }
            mCardArrayAdapter = new CardArrayAdapter(this, cards);

            listView = (CardListView) findViewById(R.id.myList);
            if (listView != null) {
                listView.setAdapter(mCardArrayAdapter);
            }

            try {
                intiTextToSpeech("hi-IN", "आपको कई मैसेज आए हैं  क्या आप चाहते हैं इसे सुनना");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void intiTextToSpeech(final String LEN, final String text) {
        mTextToSpeechHandler = new Handler();
        mTextTOSpeechrunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Intent i = new Intent(SmsUnreadActivity.this, TTSService.class);
                    i.putExtra("toSpeak", text);
                    i.putExtra("Language", LEN);
                    SmsUnreadActivity.this.startService(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mTextToSpeechHandler.postDelayed(mTextTOSpeechrunnable, 1000);

    }

    private void showVoiceRegoniztionFragment() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!isFinishing()) {

                        FragmentTransaction transactionFragment = getSupportFragmentManager().beginTransaction();
                        newIntance = VoiceRecgonizationFragment.newInstance("en-IN", false, false);
                        newIntance.setStyle(1, R.style.AppTheme);
                        transactionFragment.add(android.R.id.content, newIntance).addToBackStack(null).commitAllowingStateLoss();

                        //  newIntance.show(fragmentManager, "fragment_voice_input");

                    }

                } catch (Exception e) {
                    Toast.makeText(SmsUnreadActivity.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        handler.postDelayed(runnable, 10);

    }

    @Override
    public void onNoCommandrExcute(String Queary) {
        voiceRecgonizerDismiss();
        for (String s : mUrduPositiveWords) {
            if (Queary.equalsIgnoreCase(s)) {
                userWantToReadorNot = true;
                break;
            } else {
                userWantToReadorNot = false;
            }
        }
        readOrNot(userWantToReadorNot);


    }


    @Override
    protected void onDestroy() {
        try {
            MyTextToSpeech.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter("com.service.result"));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    private void askRunTimePermissions() {
        new PermissionWrapper.Builder(this)
                .addPermissions(new String[]{Manifest.permission.READ_SMS})
                //enable rationale message with a custom message

                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosed
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        Log.i(SendSmsActivity.class.getSimpleName(), "Permission was granted.");
                        getUnreadSMSandUpdateUI();


                    }

                    @Override
                    public void onDenied(String permission) {
                        Log.i(SendSmsActivity.class.getSimpleName(), "Permission was not granted.");
                    }
                }).build().request();
    }


    private void voiceRecgonizerDismiss() {
        try {
            if (newIntance != null)
                getSupportFragmentManager().beginTransaction().remove(newIntance).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readOrNot(boolean userWantToReadorNot) {
        try {
            if (userWantToReadorNot)
                speckThisMessage();

            else
                moveToNextMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    // called if user what read sms by assistant

    private void speckThisMessage() throws Exception {

        if (unread == null || unread.size() < 1) {
            Log.d("First", "getUnreadSMSandUpdateUI: ");
            Toast.makeText(this, "no message to read ", Toast.LENGTH_SHORT).show();
            intiTextToSpeech("en-IN","aapake paas koee unread message nahin hai");
        }
        if (unread.size() > 1)

            intiTextToSpeech("hi-IN", unread.get(0).getMessageBody() + "अगला मैसेज" + unread.get(1).getContactName() + "से आया क्या आप सुनना चाहते हैं या नहीं");// message from you want to listen or not
        else
            intiTextToSpeech("hi-IN", unread.get(0).getMessageBody());
        unread.remove(0);


    }

    private void moveToNextMessage() throws Exception {
        if (unread.size() > 0) {
            intiTextToSpeech("hi-IN", "अगला मैसेज" + unread.get(0).getContactName() + "से आया क्या आप सुनना चाहते हैं या नहीं");// message from you want to listen or not
            unread.remove(0);
        }
        else if(unread.size()<1)
        {
            intiTextToSpeech("hi-IN","कोई और मैसेज नहीं");//no more message
        }
    }


    @Override
    public void onError(int Error) {
        voiceRecgonizerDismiss();
    }
}


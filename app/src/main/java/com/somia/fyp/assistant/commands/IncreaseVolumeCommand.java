package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class IncreaseVolumeCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        AudioManager audio = (AudioManager) commandModel.getContext().getSystemService(Context.AUDIO_SERVICE);
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
        Log.d("Volume","increase the volume..");

    }

    @Override
    public String getDefaultPhrase() {
        return "حجم زیادہ کرو,آواز زیادہ کرو,آواز بلند کرو,volume Jyada karo,volume Zyada karo,volume jayada karo,Awaz Jyada karo,volume Uncha karo, Awaz Unchi karo";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.Volume_Zayada_ke_Ja_rahi_ha);
    }
}

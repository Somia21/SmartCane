package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.media.AudioManager;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class DecreaseVolumeCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        AudioManager audio = (AudioManager) commandModel.getContext().getSystemService(Context.AUDIO_SERVICE);
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

    }

    @Override
    public String getDefaultPhrase() {
        return "والیوم کم کرو,آواز کم کرو,آواز تھوڑی کرو,volume thoda karo,Volume kam karo,Awaz kam karo";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.Volume_kam_kiya_ja_raha_ha);
    }
}
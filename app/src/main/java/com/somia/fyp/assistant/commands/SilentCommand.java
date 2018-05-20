package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.media.AudioManager;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class SilentCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {

        try{
            final AudioManager mode = (AudioManager) commandModel.getContext().getSystemService(Context.AUDIO_SERVICE);
            mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getDefaultPhrase() {
        return "خاموش,نماز وقت,silent lagao,silent karo";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.Silent_Phone_Kar_Dea_Gaya_Ha);
    }
}

package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.somia.fyp.R;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

public class NextSongCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Intent i = new Intent("com.android.music.musicservicecommand");
        i.putExtra("command", "next");
        commandModel.getContext().sendBroadcast(i);
        Log.d("Music","NextSongCommand");

    }

    @Override
    public String getDefaultPhrase() {
        return "اگلا گانا,Aag Lagana lagao";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return context.getResources().getString(R.string.Agala_Gaana_Lagaaya_Ja_Raha_Ha);
    }
}

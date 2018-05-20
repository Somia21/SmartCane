package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;


import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;
import com.somia.fyp.assistant.commands.Receivers.InstructionsActivity;

public class InstructionsCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Intent i = new Intent(commandModel.getContext(), InstructionsActivity.class);
        i.putExtra("onOrOff", false);
        commandModel.getContext().startActivity(i);
    }

    @Override
    public String getDefaultPhrase() {
        return "open instruction,open help";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

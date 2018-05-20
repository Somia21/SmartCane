package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;
import com.somia.fyp.assistant.commands.Receivers.SmsUnreadActivity;

public class ReadSmsCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        commandModel.getContext().startActivity(new Intent(commandModel.getContext(),SmsUnreadActivity.class));

    }

    @Override
    public String getDefaultPhrase() {

        return "read message, read my messages";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

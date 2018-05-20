package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;
import com.somia.fyp.assistant.commands.Receivers.CallingActivity;

public class CallCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {

        Intent intent =  new Intent(commandModel.getContext(), CallingActivity.class);
        intent.putExtra(CallingActivity.EXTRA_NAME,commandModel.getPredicate());

        commandModel.getContext().startActivity(intent);
    }

    @Override
    public String getDefaultPhrase() {
        return "make call,do call,make phone call";
    }

    @Override
    public String getTtsPhrase(Context context) {
       return "आप किसी कॉल करना चाहते हैं";
    }
}

package com.somia.fyp.assistant.commands.Receivers;

import android.content.Context;
import android.content.Intent;

import com.somia.fyp.UI.Activitys.LogOutBlindUser;
import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;

/**
 * Created by Somia on 6/3/2018.
 */

public class LogoutCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {

        Intent intent =  new Intent(commandModel.getContext(), LogOutBlindUser.class);
        intent.putExtra(CallingActivity.EXTRA_NAME,commandModel.getPredicate());

        commandModel.getContext().startActivity(intent);
    }

    @Override
    public String getDefaultPhrase() {
        return "logout from application,please logout";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return "you are logging out";
    }
}

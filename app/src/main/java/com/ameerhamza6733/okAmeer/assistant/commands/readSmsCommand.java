package com.ameerhamza6733.okAmeer.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.ameerhamza6733.okAmeer.assistant.Command;
import com.ameerhamza6733.okAmeer.assistant.CommandModel;
import com.ameerhamza6733.okAmeer.assistant.commands.Receivers.smsUnreadActivity;

public class readSmsCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        commandModel.getContext().startActivity(new Intent(commandModel.getContext(),smsUnreadActivity.class));

    }

    @Override
    public String getDefaultPhrase() {

        return "پڑھو SMS ,میسج پڑھو,message padho";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

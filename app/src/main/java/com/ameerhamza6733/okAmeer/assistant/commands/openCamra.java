package com.ameerhamza6733.okAmeer.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.ameerhamza6733.okAmeer.UI.Activitys.CameraActivity;
import com.ameerhamza6733.okAmeer.assistant.Command;
import com.ameerhamza6733.okAmeer.assistant.CommandModel;

public class openCamra implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Intent intent = new Intent(commandModel.getContext(),CameraActivity.class);
        commandModel.getContext().startActivity(intent);
    }

    @Override
    public String getDefaultPhrase() {
        return "direction";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

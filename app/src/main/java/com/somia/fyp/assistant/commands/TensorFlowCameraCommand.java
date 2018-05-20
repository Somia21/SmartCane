package com.somia.fyp.assistant.commands;

import android.content.Context;
import android.content.Intent;

import com.somia.fyp.assistant.Command;
import com.somia.fyp.assistant.CommandModel;
import com.somia.fyp.tensorflowv1.CameraActivity;

/**
 * Created by Somia on 4/25/2018.
 */

public class TensorFlowCameraCommand implements Command {
    @Override
    public void execute(CommandModel commandModel) {
        Intent i = new Intent(commandModel.getContext(), CameraActivity.class);
        i.putExtra("onOrOff", false);
        commandModel.getContext().startActivity(i);
    }

    @Override
    public String getDefaultPhrase() {
        return "detect object";
    }

    @Override
    public String getTtsPhrase(Context context) {
        return null;
    }
}

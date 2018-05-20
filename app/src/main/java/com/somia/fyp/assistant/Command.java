package com.somia.fyp.assistant;

import android.content.Context;

public interface Command {
     void execute(CommandModel commandModel);
    String getDefaultPhrase ();
    String getTtsPhrase(Context context);
}

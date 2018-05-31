package com.somia.fyp.assistant;

import android.content.Context;

public class CommandModel {
    private  String ExtraPhrase;
    private String Predicate;
    private Context context;

    public CommandModel(String ExtraPhrase, String predicate, Context context) {
        this.ExtraPhrase = ExtraPhrase;
        Predicate = predicate;
        this.context = context;
    }
    public CommandModel(String predicate, Context context) {

        Predicate = predicate;
        this.context = context;
    }

    public String getCommandExtraPhrase() {
        return ExtraPhrase;
    }

    public String getPredicate() {
        return Predicate;
    }

    public Context getContext() {
        return context;
    }
}

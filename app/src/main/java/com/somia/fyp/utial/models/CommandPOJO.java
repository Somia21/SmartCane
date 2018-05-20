package com.somia.fyp.utial.models;


public class CommandPOJO {

    private String CommandName;
    private String CommandAction;
    private String CommandRequstedby;



    public CommandPOJO() {
    }

    public CommandPOJO(String commandName, String commandAction, String commandRequstedby) {
        CommandName = commandName;
        CommandAction = commandAction;
        CommandRequstedby = commandRequstedby;


    }




    public void setCommandName(String commandName) {
        CommandName = commandName;
    }

    public void setCommandAction(String commandAction) {
        CommandAction = commandAction;
    }

    public void setCommandRequstedby(String commandRequstedby) {
        CommandRequstedby = commandRequstedby;
    }

    public String getCommandName() {
        return CommandName;
    }

    public String getCommandAction() {
        return CommandAction;
    }

    public String getCommandRequstedby() {
        return CommandRequstedby;
    }
}

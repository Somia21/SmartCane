package com.somia.fyp.utial.models;



public class Command {
    private long votebySize;
    private CommandPOJO commandPOJO;

    public Command(long voteby, CommandPOJO commandPOJO) {
        this.votebySize = voteby;
        this.commandPOJO = commandPOJO;
    }

    public long getVotebySize() {
        return votebySize;
    }

    public CommandPOJO getCommandPOJO() {
        return commandPOJO;
    }

    public Command(CommandPOJO commandPOJO) {
        this.commandPOJO = commandPOJO;
    }

    public Command(int voteby) {
        this.votebySize = voteby;
    }
}

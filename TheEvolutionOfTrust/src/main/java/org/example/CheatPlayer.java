package org.example;

public class CheatPlayer implements Player {

    private String name;

    public CheatPlayer(String name) {
        this.name = name;
    }

    @Override
    public Choice playChoice() {
        return Choice.CHEAT;
    }
}
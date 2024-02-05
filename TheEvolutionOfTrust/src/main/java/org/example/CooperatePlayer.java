package org.example;

public class CooperatePlayer implements Player {

    private String name;

    public CooperatePlayer(String name) {
        this.name = name;
    }
    @Override
    public Choice playChoice() {
        return Choice.COOPERATE;
    }
}
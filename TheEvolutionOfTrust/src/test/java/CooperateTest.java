package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CooperatePlayerTest {

    @Test
    public void expectsToCreateACooperatePlayer() {
        assertDoesNotThrow(() -> new CooperatePlayer("Aman"));
    }

    @Test
    public void expectsToAlwaysGiveChoiceCheat() {
        Player player = new CooperatePlayer("Chunnu");
        assertEquals(Choice.COOPERATE, player.playChoice());
    }

}
import org.example.CheatPlayer;
import org.example.Choice;
import org.example.CooperatePlayer;
import org.example.TrustTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrustTransactionTest {
    @Test
    public void expectsToCreateTransactionRulesObject(){
        assertDoesNotThrow(() -> new TrustTransaction());
    }

    @Test
    public void testIfBothPlayerAreCooperatePlayerThenBothScore2Points(){
        TrustTransaction trustTransaction = new TrustTransaction();
        CooperatePlayer player1 = new CooperatePlayer("Chunnu");
        CooperatePlayer player2 = new CooperatePlayer("Munnu");
        List<Choice> choices = new ArrayList<>();
        choices.add(player1.playChoice());
        choices.add(player2.playChoice());
        List<Integer> scores = trustTransaction.scores(choices);
        assertEquals(Arrays.asList(2,2), scores);
    }


    @Test
    public void testIfBothPlayerAreCheatPlayerThenBothScore0Points(){
        TrustTransaction trustTransaction = new TrustTransaction();
        CheatPlayer player1 = new CheatPlayer("Chunnu");
        CheatPlayer player2 = new CheatPlayer("Munnu");

        List<Choice> choices = new ArrayList<>();

        choices.add(player1.playChoice());
        choices.add(player2.playChoice());
        List<Integer> scores = trustTransaction.scores(choices);
        assertEquals(Arrays.asList(0,0), scores);
    }

    @Test
    public void testIfPlayer1IsCooperatePlayerAndPlayer2IsCheatPlayerThenPlayerOneScoreMinus1AndPlayer2Score3Points(){
        TrustTransaction trustTransaction = new TrustTransaction();
        CooperatePlayer player1 = new CooperatePlayer("Chunnu");
        CheatPlayer player2 = new CheatPlayer("Munnu");

        List<Choice> choices = new ArrayList<>();
        choices.add(player1.playChoice());
        choices.add(player2.playChoice());
        List<Integer> scores = trustTransaction.scores(choices);
        assertEquals(Arrays.asList(-1,3), scores);
    }


    @Test
    public void testIfPlayer1IsCheatPlayerAndPlayer2IsCooperatePlayerThenPlayer1Score3AndPlayer2ScoreMinus1Points(){
        TrustTransaction trustTransaction = new TrustTransaction();
        CheatPlayer player1 = new CheatPlayer("Chunnu");
        CooperatePlayer player2 = new CooperatePlayer("Munnu");

        List<Choice> choices = new ArrayList<>();
        choices.add(player1.playChoice());
        choices.add(player2.playChoice());
        List<Integer> scores = trustTransaction.scores(choices);
        assertEquals(Arrays.asList(3,-1), scores);
    }

    @Test
    public void testIfBothPlayersAreCheatPlayerAfter5RoundsTheScoreWillRemainZero(){

    }
}

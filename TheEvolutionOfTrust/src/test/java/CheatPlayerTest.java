
import org.example.CheatPlayer;
import org.example.Choice;
import org.example.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheatPlayerTest {

    @Test
    public void expectsToCreateACheatPlayer() {
        assertDoesNotThrow(() -> new CheatPlayer("Aman"));
    }

    @Test
    public void expectsToAlwaysGiveChoiceCheat() {
        Player player = new CheatPlayer("Chunnu");
        assertEquals(Choice.CHEAT, player.playChoice());
    }


}
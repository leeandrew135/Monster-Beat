package test.backend;

import backend.Monster;
import backend.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * test for monster and character class the extends from abstract player test
     */
    @Test
    void loseALife() {
        Player player = new Monster();

        // Simulate losing all lives
        player.loseALife();
        player.loseALife();
        player.loseALife();

        assertEquals(0, player.getLivesLeft(), "Lives left should be 0 after losing all lives.");
        assertTrue(player.getIfDied(), "Player should be marked as dead after losing all lives.");

        // Attempt to lose another life should not decrease lives below zero
        player.loseALife();
        assertEquals(0, player.getLivesLeft(), "Lives left should not decrease below 0.");
    }
}

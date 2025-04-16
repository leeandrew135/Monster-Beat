package test.backend;

import backend.Game;
import backend.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    final String userEmail = "test@example.com";

    /**
     *  Test for initializing Game with 2 difficulty
     */
    @BeforeEach
    void setUp() {
        // Initialize Game with a moderate difficulty for testing
        game = new Game(userEmail, 2);
    }

    @Test
    void gameConstructor() {
        assertNotNull(game.monsters);
        assertEquals(2, game.monsters.size());
        assertNotNull(game.character);
        assertNotNull(game.questionBank);
        assertFalse(game.questionBank.isEmpty());
    }


    /**
     * Test of generateQuestion method, of class Game.
     */
    @Test
    void generateQuestion() {
        System.out.println("generateQuestion()");
        Question firstQuestion = game.generateQuestion();
        assertNotNull(firstQuestion);
        Question expectedResult = game.questionBank.get(0); // test for first question content
        assertEquals(firstQuestion, expectedResult);
        // Simulate answering all questions
        while (game.generateQuestion() != null);
        // After all questions are answered, the next call should return null
        assertNull(game.generateQuestion());
    }

    /**
     * Test of answerWrong method, of class Game.
     */
    @Test
    void answerWrong() {
        System.out.println("answerWrong()");
        int initialLives = game.character.getLivesLeft();
        String result = game.answerWrong();
        assertEquals(initialLives - 1, game.character.getLivesLeft());
        assertTrue(result.equals("game end") || result.equals("c1- change question"));
    }

    /**
     * Test of answerRight method, of class Game.
     */
    @Test
    void answerRight() {
        String result = game.answerRight();
        assertTrue(result.equals("game end") || result.equals("m2- change question") || result.equals("m1- change question") || result.equals("change monster and question"));
    }

    /**
     * Test of calculateMarks method, of class Game.
     */
    @Test
    void calculateMarks() {
        int timeLeft = 60;
        int score = game.calculateMarks(timeLeft);
        assertTrue(score > 0);

        // Test score calculation when time has run out
        score = game.calculateMarks(0);
        assertTrue(score > 0);

        // Test score calculation when character is dead
        game.character.loseALife();
        game.character.loseALife();
        game.character.loseALife();
        score = game.calculateMarks(timeLeft);
        assertEquals(0, score);
    }
    /**
     * Test of calculateQuestions method, of class Game.
     */
    @Test
    void calculateQuestions() {
        Game game1 = new Game("anne111@gmail.com", 2);
        game1.monster1.loseALife();
        game1.monster1.loseALife();
        game1.monster1.loseALife();
        game1.monster2.loseALife();
        game1.monster2.loseALife();

        System.out.println(game1.monster1.getLivesLeft());
        System.out.println(game1.monster2.getLivesLeft());
        int answered = game1.calculateQuestions(game1.monsters);
        assertEquals(answered, 5);
    }
}

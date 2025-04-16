package backend;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Game class represents the main gameplay logic, controlling the flow of the game,
 * managing questions, and tracking the player and monsters' states. It handles game initialization,
 * difficulty settings, and responses to player's actions during the game.
 */

public class Game {
    public ArrayList<Monster> monsters; // 2 monster for each level
    public Character character;
    public Monster monster1; //first monster
    public Monster monster2; // second monster
    public int difficultyLevel;
    public ArrayList<Question> questionBank;
    int questionCounter = 0;
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    public int getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public ArrayList<Question> getQuestionBank() {
        return questionBank;
    }
    public void setQuestionBank(ArrayList<Question> questionBank) {
        this.questionBank = questionBank;
    }

    /**
     * Constructs a new Game instance. Initializes game elements including monsters and the player character
     * based on the specified difficulty level, and loads an appropriate set of questions.
     *
     * @param UserEmail       Email of the user playing the game, used for score tracking (currently unused in constructor).
     * @param difficultyLevel The difficulty level of the game, affecting monster difficulty and questions.
     */

    public Game(String UserEmail, int difficultyLevel) {
        System.out.println("difficultyLevel is : " + difficultyLevel);
        this.monsters = generateMonsters(difficultyLevel);
        monster1 = monsters.get(0);
        monster2 = monsters.get(1);
        this.character = generateCharacter(difficultyLevel);
        this.difficultyLevel = difficultyLevel;
        this.questionBank = DataProcessing.loadQuestionBank(difficultyLevel);
        Collections.shuffle(questionBank); //shuffle the question bank
    }

    public Question generateQuestion() {
        if (this.questionCounter >= this.questionBank.size()) {
            //end game
            return null;
        }
        return this.questionBank.get(questionCounter++);
    }
    /**
     * Processes the player's incorrect answer, reducing the character's life and determining the game state.
     * @return A string indicating the next state ("game end" if game is over, otherwise indicates question change).
     */

    public String answerWrong(){
        character.loseALife();
        System.out.println("answerWong invoked");
        System.out.println("now character" +
            "s life is " + character.getLivesLeft());
        if (character.ifDied) return "game end";
        else return "c1- change question";
    }
    /**
     * Handles a correct answer from the player, affecting the monsters' health and potentially ending the game.
     * @return A string indicating the game's next state based on the result of the answer.
     */

    public String answerRight() {
        // monster1 already died
        if (monster1.getIfDied()) {
            monster2.loseALife();
            if (monster2.getIfDied()) return "game end";
            return "m2- change question";
        } else { // monster 1 not died
            monster1.loseALife();
            if (monster1.ifDied) return "change monster and question"; // also needs update question
            return "m1- change question";
        }

    }
    /**
     * Calculates and returns the player's score based on the time left, remaining lives, and questions answered.
     * @param timeLeft The time left in the game.
     * @return The calculated score.
     */

    public int calculateMarks(int timeLeft) {
        int result = 0;
        // if character survive
        if (!character.getIfDied()) {
            if (timeLeft > 0) { // if we still have time left, we killed all monsters and get 6 question right
                result =  timeLeft * 5 + character.livesLeft * 50 + 6 * 30;
            } else { // no time left, we check monsters to get how many questions do I get it right
                int questionAnswered = calculateQuestions(monsters);
                result =  character.livesLeft * 50 + questionAnswered * 30;
            }
        } else { // character died
            int questionAnswered = calculateQuestions(monsters);
            result = questionAnswered * 30 - timeLeft;
        }

        return Math.max(0, result);
    }
    /**
     * Calculates the number of questions correctly answered based on the monsters' health.
     * Used internally to determine the final score.
     * @param monsters The list of monsters in the game.
     * @return The number of questions correctly answered.
     */

    public int calculateQuestions(ArrayList<Monster> monsters) {
        int result = 0;
        Monster m1 = monsters.get(0);
        if (m1.getIfDied()) result += 3;
        else return 3 - m1.getLivesLeft();

        Monster m2 = monsters.get(1); //get second monster if first monster died
        result += 3 - m2.getLivesLeft();
        return result;
    }
    /**
     * Generates the list of monsters for the game based on the difficulty level.
     * @param difficultyLevel The game's difficulty level.
     * @return A list of initialized monsters.
     */

    private ArrayList<Monster> generateMonsters(int difficultyLevel) {
        ArrayList<Monster> monsterList = new ArrayList<>(2);
        Monster monster1 = new Monster();
        Monster monster2 = new Monster();
        if (difficultyLevel == 1) {
            monster1.setImg("src/resources/m1.png");
            monster2.setImg("src/resources/m2.png");
        } else if (difficultyLevel == 2) {
            monster1.setImg("src/resources/m3.png");
            monster2.setImg("src/resources/m4.png");
        } else {
            monster1.setImg("src/resources/m5.png");
            monster2.setImg("src/resources/m6.png");
        }

        monsterList.add(monster1);
        monsterList.add(monster2);
        return monsterList;
    }
    /**
     * Creates the player character for the game based on the difficulty level.
     * @param difficultyLevel The game's difficulty level.
     * @return The initialized character.
     */

    private Character generateCharacter(int difficultyLevel) {
        Character character = new Character();
        if (difficultyLevel == 1) {
            character.setImg("src/resources/c1.png");
        } else if (difficultyLevel == 2) {
            character.setImg("src/resources/c2.png");
        } else {
            character.setImg("src/resources/c3.png");
        }

        return character;
    }
}

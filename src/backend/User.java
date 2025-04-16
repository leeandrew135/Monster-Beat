package backend;
/**
 * player class
 * (constructor)create a new player
 * update player
 */
public class User {
    private String email;
    private int level1HighestScore;
    private int level2HighestScore;
    private int level3HighestScore;

    /**
     * Constructor to create a new player without scores.
     *
     * @param email The email address of the new user.
     */
    public User(String email)  {
        this.email = email;
        this.level1HighestScore = 0;
        this.level2HighestScore = 0;
        this.level3HighestScore = 0;
    }

    /**
     * Constructor to create a new player with initial scores.
     *
     * @param email The email address of the new user.
     * @param level1HighestScore The highest score in level 1.
     * @param level2HighestScore The highest score in level 2.
     * @param level3HighestScore The highest score in level 3.
     */
    public User(String email, int level1HighestScore, int level2HighestScore, int level3HighestScore) {
        this.email = email;
        this.level1HighestScore = level1HighestScore;
        this.level2HighestScore = level2HighestScore;
        this.level3HighestScore = level3HighestScore;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", level1HighestScore=" + level1HighestScore +
            ", level2HighestScore=" + level2HighestScore +
            ", level3HighestScore=" + level3HighestScore +
            '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel1HighestScore() {
        return level1HighestScore;
    }

    public void setLevel1HighestScore(int level1HighestScore) {
        this.level1HighestScore = level1HighestScore;
    }

    public int getLevel2HighestScore() {
        return level2HighestScore;
    }

    public void setLevel2HighestScore(int level2HighestScore) {
        this.level2HighestScore = level2HighestScore;
    }

    public int getLevel3HighestScore() {
        return level3HighestScore;
    }

    public void setLevel3HighestScore(int level3HighestScore) {
        this.level3HighestScore = level3HighestScore;
    }
}

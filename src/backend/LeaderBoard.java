/**
 * LeaderBoard controller control the data in leaderboard.
 * how to use it sortedList static method
 *   LeaderBoard.getSortedList(1); // sort by level1Level
 *   @params int difficulty level
 *   @return a sorted Arraylist that can be showed in order
 *   a static class, no need to declare a LeaderBoard class first
 */
package backend;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Controls and manages leaderboard data. This static class provides functionality
 * to retrieve a sorted list of users based on their scores in different difficulty levels.
 */
public class LeaderBoard {
    /**
     * Retrieves a sorted list of users based on their highest scores for a given difficulty level.
     * The sorting is in descending order, with higher scores appearing first.
     *
     * @param difficultyLevel The difficulty level by which to sort the leaderboard.
     *                        Valid levels are 1 (Easy), 2 (Medium), and 3 (Hard).
     * @return A sorted ArrayList of User objects, sorted by their score in the specified difficulty.
     */
    public static ArrayList<User> getSortedList(int difficultyLevel) {
        ArrayList<User> sortedList = DataProcessing.loadUserInfo(); //data from UserInfo.csv
        System.out.println(sortedList.size());

        Collections.sort(sortedList, (p1, p2) -> {
            if (difficultyLevel == 1) {
                return Integer.compare(p2.getLevel1HighestScore(), p1.getLevel1HighestScore());
            } else if (difficultyLevel == 2) {
                return Integer.compare(p2.getLevel2HighestScore(), p1.getLevel2HighestScore());
            } else {
                return Integer.compare(p2.getLevel3HighestScore(), p1.getLevel3HighestScore());
            }
        });
        return sortedList;
    }
}

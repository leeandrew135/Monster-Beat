package test.backend;

import backend.DataProcessing;
import backend.LeaderBoard;
import backend.Question;
import backend.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LeaderBoardTest {

    /**
     * Test of getSortedList method, of class LeaderBoardTest.
     */
    @Test
    void getSortedList() {
        System.out.println("getSortedList");
        ArrayList<User> userList = LeaderBoard.getSortedList(3);
        for (int i = 0; i < userList.size() - 1; i++) {
            assertTrue(userList.get(i).getLevel3HighestScore() >= userList.get(i + 1).getLevel3HighestScore());
        }
    }

}

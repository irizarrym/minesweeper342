/*
    Michael Irizarry (miriza6@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Unit tests for Minesweeper
*/

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperTest
{
    private static final String testFile = "scoresTest.txt";
    
    public MinesweeperTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    @Test
    public void test01()
    {
        GuiCounter ctr = new GuiCounter(3);
        assertEquals(0, ctr.getValue());
        ctr.increment();
        assertEquals(1, ctr.getValue());
    }
    
    @Test
    public void test02()
    {
        GuiCounter ctr = new GuiCounter(2);
        assertEquals(0, ctr.getValue());
        ctr.setValue(10);
        assertEquals(10, ctr.getValue());
    }
    
    @Test
    public void test03()
    {
        GuiCounter ctr = new GuiCounter(3);
        assertEquals(0, ctr.getValue());
        ctr.setValue(10);
        ctr.decrement();
        assertEquals(9, ctr.getValue());
    }
    
    @Test
    public void test04()
    {
        GuiCounter ctr = new GuiCounter(4);
        assertEquals(0, ctr.getValue());
        ctr.setValue(10);
        ctr.increment();
        ctr.increment();
        assertEquals(12, ctr.getValue());
    }
    
    @Test
    public void test05()
    {
        GuiCounter ctr = new GuiCounter(4);
        assertEquals(0, ctr.getValue());
        
        ctr.setValue(10);
        for(int i = 0; i < 100; ++i)
        {
            ctr.increment();
            ctr.decrement();
            assertEquals(10, ctr.getValue());
        }
    }
    
    @Test
    public void test06()
    {
        /*
            Clicks all cells on grid which should result in a win or loss
        */
        BackendTester test = new BackendTester();
        
        assertTrue(!test.win && !test.lose);
        
        for(int x = 0; x < 10; ++x) for(int y = 0; y < 10; ++y)
        {
            test.game.clickCell(x, y);
        }
        
        assertTrue(test.win || test.lose);
    }
    
    @Test
    public void test07() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add score to table
        topTen.addScore("Anonymous", 15);
        
        // Check table
        assertEquals(1, topTen.count());
        assertEquals(15, topTen.getScore(0));
        assertEquals("Anonymous", topTen.getName(0));
    }
    
    @Test
    public void test08() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 11 scores to table
        for(int i = 0; i < 11; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(10, topTen.count()); // There should be at most 10 entries
    }
    
    @Test
    public void test09() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 10 scores to table
        for(int i = 0; i < 10; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(10, topTen.count());
        
        // Worst score is 9 so 10 should not be top ten
        assertFalse(topTen.isTopTen(10));
    }
    
    @Test
    public void test10() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 10 scores to table
        for(int i = 0; i < 10; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(10, topTen.count());
        
        // Worst score is 9 so 9 should not be top ten either
        assertFalse(topTen.isTopTen(9));
    }
    
    @Test
    public void test11() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 10 scores to table
        for(int i = 0; i < 10; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(10, topTen.count());
        
        // Worst score is 9 so 8 should be top ten
        assertTrue(topTen.isTopTen(8));
    }
    
    @Test
    public void test12() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 9 scores to table
        for(int i = 0; i < 9; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(9, topTen.count());
        
        // There are less than 10 entries so there should be space for a terrible score
        assertTrue(topTen.isTopTen(1000000));
    }
    
    @Test
    public void test13() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 3 scores to table but in descending order
        for(int i = 0; i < 3; ++i)
        {
            topTen.addScore("Player " + i, 3 - i);
        }
        
        // Check table
        assertEquals(3, topTen.count());
        
        // Scores should be in ascending order
        assertEquals(1, topTen.getScore(0));
        assertEquals(2, topTen.getScore(1));
        assertEquals(3, topTen.getScore(2));
    }
    
    @Test
    public void test14() throws IOException
    {
        HighScores topTen = new HighScores(testFile);
        
        // Begin with empty file
        topTen.reset();
        assertEquals(0, topTen.count());
        
        // Add 10 scores to table
        for(int i = 0; i < 10; ++i)
        {
            topTen.addScore("Player " + i, i);
        }
        
        // Check table
        assertEquals(10, topTen.count());
        
        // Test saving and loading of high scores file
        topTen = new HighScores(testFile);
        assertEquals(10, topTen.count());
        
        for(int i = 0; i < 10; ++i)
        {
            assertEquals(i, topTen.getScore(i));
            assertEquals("Player " + i, topTen.getName(i));
        }
    }
    
    @Test
    public void test15() throws IOException, InterruptedException
    {
        GuiTimer timer = new GuiTimer();
        
        // Timer should start at 0
        assertEquals(0, timer.getValue());
        
        // Start timer and sleep for two seconds
        timer.startTimer();
        Thread.sleep(2000);
        timer.stopTimer();
        
        // Timer should be greater than zero
        assertTrue(0 < timer.getValue());
    }
        
    
    
    
    private class BackendTester implements iMinesweeper
    {
        public MinesweeperGame game;
        public boolean win = false;
        public boolean lose = false;
        public int clicks = 0;
        
        public BackendTester()
        {
            game = new MinesweeperGame(this);
            game.newGame();
            clicks = 0;
        }
        
        @Override
        public void setCell(int x, int y, String state)
        {
            clicks += 1;
        }

        @Override
        public void gameWin()
        {
            win = true;
        }

        @Override
        public void gameLose() 
        {
            lose = true;
        }
    }
}

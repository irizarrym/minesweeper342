/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    High Scores for Minesweeper
*/

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class HighScores
{
    private final String filePath;
    private Score[] scores;
    
    /******************
     * Public Methods *
     ******************/ 
    
    // @path to high scores table
    public HighScores(String path)
    {
        filePath = path;
        scores = new Score[10];
        loadFile();
    }
    
    // Return number of entries in high scores table
    // Returns: int from 0 to 10
    public int count()
    {
        for(int i = 0; i < scores.length; ++i)
        {
            if(scores[i] == null) return i;
        }
        
        return scores.length;
    }
    
    // Returns name of high score entry at (0 <= @index < 10)
    public String getName(int index)
    {
        assert(0 <= index && index < count());
        
        return scores[index].name;
    }
    
    // Returns score of high score entry at (0 <= @index < 10)
    public int getScore(int index)
    {
        assert(0 <= index && index < count());
        
        return scores[index].time;
    }
    
    // Did @time make it into the top ten scores?
    public boolean isTopTen(int time)
    {
        return getIndexOf(time) >= 0;
    }
    
    // Add a new name and time to the high scores table
    // Returns: true if score is added to the table; false otherwise
    public boolean addScore(String name, int time)
    throws UnsupportedEncodingException, FileNotFoundException, IOException
    {
        // Get index of the new score (assuming time makes it in top ten)
        final int index = getIndexOf(time);
        if(index < 0) return false;
        
        // Shift all scores to the right
        for(int i = 9; i > index; --i)
        {
            scores[i] = scores[i - 1];
        }
        
        // Insert new score
        scores[index] = new Score(name, time);
        
        // Save modified high scores table
        saveFile();
        
        return true;
    }
    
    // Clear high scores table
    public void reset()
    throws FileNotFoundException, IOException
    {
        for(int i = 0; i < scores.length; ++i)
        {
            scores[i] = null;
        }
        
        saveFile();
    }
    
    
    
    /*******************
     * Private Methods *
     *******************/
    
    // Return index of where 
    private int getIndexOf(int time)
    {
        for(int i = 0; i < 10; ++i)
        {
            if(scores[i] == null) return i;
            if(time < scores[i].time) return i;
        }
        
        return -1;
    }
    
    // Load high scores from @filePath
    private void loadFile()
    {
        Scanner input;
        
        // Try to open file
        try
        {
            input = new Scanner(new File(filePath));
        }
        catch(FileNotFoundException err)
        {
            return;
        }
        
        for(int i = 0; input.hasNextLine() && i < scores.length; ++i)
        {
            // Name and time are on separate lines
            String name = input.nextLine();
            if(!input.hasNextLine())
            {
                JOptionPane.showMessageDialog(null,
                    "The high scores file appears to be corrupt.\n" +
                    "It will be overwritten if a new high score is recorded.\n");
                break;
            }
            String strTime = input.nextLine();
            
            if(name == null || strTime == null)
            {
                JOptionPane.showMessageDialog(null,
                    "The high scores file appears to be corrupt.\n" +
                    "It will be overwritten if a new high score is recorded.\n");
                break;
            }
            
            int time;
            try
            {
                time = Integer.parseUnsignedInt(strTime);
            }
            catch(NumberFormatException err)
            {
                JOptionPane.showMessageDialog(null,
                    "The high scores file appears to be corrupt.\n" +
                    "It will be overwritten if a new high score is recorded.");
                break;
            }
            
            scores[i] = new Score(name, time);
        }
        
        input.close();
    }
    
    // Save high scores to @filePath
    private void saveFile()
    throws UnsupportedEncodingException, FileNotFoundException, IOException
    {
        (new File(filePath)).createNewFile();
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        
        for(Score s : scores)
        {
            if(s == null) break;
            
            // Name and time are written on separate lines
            // This makes the file very easy to read in and parse
            writer.println(s.name);
            writer.println(s.time);
        }
        
        writer.close();
    }
    
    private class Score
    {
        public final String name;
        public final int time;
        
        public Score(String _name, int _time)
        {
            name = _name;
            time = _time;
        }
    }
}
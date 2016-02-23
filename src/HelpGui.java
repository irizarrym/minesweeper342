/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    GUI for Help >> Help
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelpGui extends JFrame
{
    public HelpGui()
    {
        
        super("Minesweeper Help/About");
        // Help/About drop down
        setLayout(new GridLayout(1,2));
        JButton Help = new JButton("Help");
        JButton About = new JButton("About");
        
        Help.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(frame.getComponent(0), "Left click to check square for bomb, right click to mark bomb. Game ends once all are found or bomb explodes.");
                }
            }
        
         About.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(frame.getComponent(0), "Made By Group 60: Michael Irizarry (miriza6@uic.edu)
                                                                       And Nikhil Shankar (nshank3@uic.edu)");
                }
            }
        }
    }

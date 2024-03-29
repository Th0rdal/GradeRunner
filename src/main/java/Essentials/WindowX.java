/**
 * This class takes care of the Window and popup messages
 *
 */

package Essentials;

import javax.swing.*;
import java.awt.*;

public class WindowX extends Canvas {

    private final JFrame frame;
    public WindowX(int width, int height, String title, Game game) {
        this.frame = new JFrame(title); //creates a new Window

        this.frame.setPreferredSize(new Dimension(width, height));  //sets the preferred screen size
        this.frame.setMaximumSize(new Dimension(width, height));    //sets max screen size
        this.frame.setMinimumSize(new Dimension(width, height));    //sets min screen size

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //sets default operation when the window is closed
        this.frame.setResizable(false); //makes the window not resizeable
        this.frame.setLocationRelativeTo(null); //relative location is default
        this.frame.add(game);   //adds the game to the screen
        this.frame.setVisible(true);    //sets the frame as visible
        game.start();   //starts the game

    }
    public void warning(String message) {   //creates a warning popup with the warning message
        JOptionPane.showMessageDialog(this.frame, message);
    }

    public void error(String message) { //creates an error popup with the error message and closes the game
        JOptionPane.showMessageDialog(this.frame, message);
        System.exit(1);
    }
}

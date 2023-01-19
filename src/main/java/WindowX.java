/**
 * This class takes care of the Window
 *
 */

import javax.swing.*;
import java.awt.*;

public class WindowX extends Canvas {

    public WindowX(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title); //creates a new Window

        frame.setPreferredSize(new Dimension(width, height));  //sets the preferred screen size
        frame.setMaximumSize(new Dimension(width, height));    //sets max screen size
        frame.setMinimumSize(new Dimension(width, height));    //sets min screen size

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //sets default operation when the window is closed
        frame.setResizable(false); //makes the window not resizeable
        frame.setLocationRelativeTo(null); //relative location is default
        frame.add(game);   //adds the game to the screen
        frame.setVisible(true);    //sets the frame as visible
        game.start();   //starts the game

    }
}

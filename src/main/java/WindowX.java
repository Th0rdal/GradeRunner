/**
 * This class takes care of the Window
 *
 */

import javax.swing.*;
import java.awt.*;

public class WindowX extends Canvas {

    private JFrame frame;
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
}

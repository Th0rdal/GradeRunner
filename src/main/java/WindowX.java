import javax.swing.*;
import java.awt.*;

public class WindowX extends Canvas {

    private JFrame frame;
    public WindowX(int width, int height, String title, Game game) {
        this.frame = new JFrame(title);

        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(game);
        this.frame.setVisible(true);
        game.start();

    }
}

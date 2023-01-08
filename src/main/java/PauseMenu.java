
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PauseMenu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private Color color;

    public PauseMenu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;

    }



    public void tick() {

    }

    public void render(Graphics g) {
        Font menu = new Font("arial", 1, 100);
        Font buttons = new Font("arial", 1, 70);
        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Paused",450,150);

        g.setColor(Color.black);
        g.fillRect(375, 200, 500, 100);
        g.fillRect(375, 350, 500, 100);
        //g.drawRect(375, 500, 500, 100);
        //g.drawRect(375, 700, 500, 100);

        g.setColor(Color.white);
        g.setFont(buttons);
        g.drawString("Continue", 475, 275);
        g.drawString("Main Menu", 450, 425);
        //g.drawString("Help", 555, 425);
        //g.drawString("Quit", 555, 775);

    }

    public boolean mouseOverBox(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gamestate == Game.STATE.Pause) {

            if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
                game.gamestate = Game.STATE.Game;
                startGame();
            }

            if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
                game.setGamestate(Game.STATE.Menu);
            }
//
//      if (mouseOverBox(mx, my, 375, 500, 500, 100)) {
//        game.gamestate = Game.STATE.Options;
//      }

//            if (mouseOverBox(mx, my, 375, 700, 500, 100)) {
//                System.exit(1);
//            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void startGame() {
    }
}
/**
 * Handles mouse events, sprite loading and render of the Pause Menu Screen
 */

package Screens;

import Essentials.Game;
import Essentials.Handler;
import Settings.Audio;

import java.awt.*;
import java.awt.event.*;

public class PauseMenu extends BasicMenu {

    //buttons
    private final Screens.Button Button_Continue = new Screens.Button(375, 200, 500, 100, "Continue", true);
    private final Screens.Button Button_MainMenu = new Screens.Button(375, 350, 500, 100, "Main Menu", true);

    public PauseMenu(Game game, Handler handler, Audio audio){
        super(game, handler, audio);
        this.loadSprites();
    }

    public void tick() {}   //physic calculations in this method

    public void render(Graphics g) {    //method for all graphic calculations
        super.render(g);

        Font menu = new Font("arial", Font.PLAIN, 100);
        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Paused",(Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Paused") / 2),150);

        this.Button_Continue.render(g);
        this.Button_MainMenu.render(g);
    }

    public void loadSprites() { //loading of the sprites the object needs
        super.loadSprites();
    }

    //mouse event
    public void mouseMoved(MouseEvent e) {  //handles all events when mouse is moved
        if (game.getGamestate() != Game.STATE.Pause) {
            return;
        }
        super.mouseMoved(e);
        int mx = e.getX();
        int my = e.getY();

        this.Button_Continue.toggleHighlighted(this.mouseOverBox(mx, my, 375, 200, 500, 100));
        this.Button_MainMenu.toggleHighlighted(this.mouseOverBox(mx, my, 375, 350, 500, 100));
    }

    public void mousePressed(MouseEvent e) {    //handles all events when mouse is pressed
        if (game.gamestate != Game.STATE.Pause) {
            return;
        }

        super.mousePressed(e);
        int mx = e.getX();
        int my = e.getY();

        if (this.mouseOverBox(mx, my, 375, 200, 500, 100)) {
            game.setGamestate(Game.STATE.Game);
        }else if (this.mouseOverBox(mx, my, 375, 350, 500, 100)) {
            game.setGamestate(Game.STATE.Menu);
        }
    }

    public void mouseReleased(MouseEvent e){}   //handles all events when mouse is released
}

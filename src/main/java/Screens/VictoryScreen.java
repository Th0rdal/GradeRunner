package Screens;

import Essentials.Game;
import Essentials.Handler;
import Settings.Audio;

import java.awt.*;
import java.awt.event.MouseEvent;

public class VictoryScreen extends BasicMenu {


    private final Screens.Button Button_MainMenu = new Screens.Button(350, 700, 500, 100, "Main Menu.Menu", true);

    public VictoryScreen(Game game, Handler handler, Audio audio) {
        super(game, handler, audio);
        this.loadSprites();
    }

    public void loadSprites() {
        super.loadSprites();
    }

    public void tick(){}

    public void render(Graphics g) {

        Font titleFont = new Font("arial", Font.PLAIN, 100);
        g.setFont(titleFont);
        g.setColor(Color.black);
        g.drawString("YOU WON", (Game.WIDTH / 2 - g.getFontMetrics().stringWidth("YOU WON") / 2), 150);
        this.Button_MainMenu.render(g);
    }

    public void mouseMoved(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.VictoryScreen) {
            return;
        }

        super.mouseMoved(e);
        int mx = e.getX();
        int my = e.getY();

        this.Button_MainMenu.changeHighlight(this.mouseOverBox(mx, my, 350, 700, 500, 100));

    }
    public void mousePressed(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.VictoryScreen) {
            return;
        }

        super.mousePressed(e);
        int mx = e.getX();
        int my = e.getY();

        if (this.mouseOverBox(mx, my, 350, 700, 500, 100)) {
            game.setGamestate(Game.STATE.Menu);
        }
    }

    public void mouseReleased(MouseEvent e){
    }
}

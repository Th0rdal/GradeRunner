package Screens;

import Essentials.Game;
import Essentials.Handler;
import Settings.Audio;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DeathScreen extends BasicMenu {

    private final Button Button_MainMenu = new Button(350, 700, 500, 100, "Main Menu", true);
    private final Font titleFont = new Font("arial", Font.PLAIN, 100);
    private final Font textFont = new Font("arial", Font.PLAIN, 30);
    private final HUD hud;


    public DeathScreen(Game game, Handler handler, Audio audio) {
        super(game, handler, audio);
        this.loadSprites();
        this.hud = handler.getHUD();
    }

    public void loadSprites() {
        super.loadSprites();
    }

    public void tick(){}

    public void render(Graphics g) {

        g.setFont(this.titleFont);
        g.setColor(Color.black);
        g.drawString("YOU DIED", (Game.WIDTH / 2 - g.getFontMetrics().stringWidth("YOU DIED") / 2), 150);
        g.setFont(this.textFont);
        g.drawString("Score: " + this.hud.getScore(), 400, 300);
        g.drawString("Time left: " + this.hud.getTime(), 400, 400);
        this.Button_MainMenu.render(g);
    }

    public void mouseMoved(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.DeathScreen) {
            return;
        }

        super.mouseMoved(e);
        int mx = e.getX();
        int my = e.getY();

        this.Button_MainMenu.changeHighlight(this.mouseOverBox(mx, my, 350, 700, 500, 100));
    }
    public void mousePressed(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.DeathScreen) {
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

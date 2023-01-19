import java.awt.*;
import java.awt.event.MouseEvent;

public class DeathScreen extends BasicMenu {

    private boolean mouseOverMainMenuButton = false;

    public DeathScreen(Game game, Handler handler, Audio audio) {
        super(game, handler, audio);
        this.loadSprites();
    }

    public void loadSprites() {
        super.loadSprites();
    }

    public void tick(){}

    public void render(Graphics g) {

        Font titleFont = new Font("arial", Font.PLAIN, 100);
        Font buttonFont = new Font("arial", Font.PLAIN, 70);
        Font buttonHighlightedFont = new Font("arial", Font.PLAIN, 80);
        g.setFont(titleFont);
        g.setColor(Color.black);
        g.drawString("YOU DIED", 380, 150);

        g.fillRect(350, 700, 500, 100);
        g.setColor(Color.white);
        if (this.mouseOverMainMenuButton) {
            g.setFont(buttonHighlightedFont);
            g.drawString("Main Menu", 400, 775);
        } else {
            g.setFont(buttonFont);
            g.drawString("Main Menu", 425, 775);
        }

    }

    public void mouseMoved(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.DeathScreen) {
            return;
        }

        super.mouseMoved(e);
        int mx = e.getX();
        int my = e.getY();

        this.mouseOverMainMenuButton = Utilities.mouseOverBox(mx, my, 350, 700, 500, 100);
    }
    public void mousePressed(MouseEvent e) {
        if (game.getGamestate() != Game.STATE.DeathScreen) {
            return;
        }

        super.mousePressed(e);
        int mx = e.getX();
        int my = e.getY();

        if (Utilities.mouseOverBox(mx, my, 350, 700, 500, 100)) {
            game.setGamestate(Game.STATE.Menu);
        }
    }

    public void mouseReleased(MouseEvent e){
    }
}

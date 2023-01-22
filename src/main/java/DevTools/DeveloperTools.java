/**
 * This class adds DevTools.DeveloperTools to the game
 * currently shows fps and ticks per second
 */

package DevTools;

import Essentials.Game;
import Essentials.Handler;
import GameObjects.Player;
import Screens.LevelSelect;

import java.awt.*;


public class DeveloperTools{

    private Player p;
    private final LevelSelect levelSelect;
    private final Handler handler;
    private static boolean visibility = false;
    private int frames = 0, ticks = 0;
    private int lastFrames = 0, lastTicks = 0;
    public DeveloperTools(Handler handler, LevelSelect levelSelect) {
        this.handler = handler;
        this.levelSelect = levelSelect;
    }

    public void render(Graphics g) {
        if (DeveloperTools.visibility) {
            g.setColor(Color.black);
            g.drawRect(0, 0, 140, 120);
            g.drawString("Dev-Tools:", 10, 12);
            g.drawString("FPS: " + this.lastFrames + "/" + Game.MAX_FRAMES_PER_SECOND, 10, 32);
            g.drawString("TICKS: " + this.lastTicks + "/" + Game.MAX_TICKS_PER_SECOND, 10, 42);
            g.drawString("Selected Level: " + this.levelSelect.getSelectedLevel(), 10, 52);
            g.drawString("Player X " + this.p.getX(), 10, 62);
            g.drawString("Player Y " + this.p.getY(), 10, 72);
            g.drawString("Player Vel X " + this.p.getVelX(), 10, 82);
            g.drawString("Player Vel Y " + this.p.getVelY(), 10, 92);
            g.drawString("Moved: " + this.handler.getMoved(), 10, 102);
            g.drawString("maxLen: " + this.handler.getTotalLength(), 10, 112);
        }
    }
    public void incrementFrames() {this.frames++;}
    public void updateFrames() {
        this.lastFrames = frames;
        this.frames = 0;
    }
    public void incrementTicks() {this.ticks++;}
    public void updateTicks() {
        this.lastTicks = ticks;
        this.ticks = 0;
    }
    public static void toggleVisibility() {
        DeveloperTools.visibility = !DeveloperTools.visibility;
    }
    public void getPlayer() {
        this.p = (Player)this.handler.getPlayer();
    }

}

/**
 * This class adds DeveloperTools to the game
 * currently shows fps and ticks per second
 */

import java.awt.*;


public class DeveloperTools extends GameObject{


    public DeveloperTools() {
        super(0.0f, 0.0f, ID.Developer, 0, 0);
    }
    private int frames = 0, ticks = 0;
    private int lastFrames = 0, lastTicks = 0;
    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 0, 100, 100);
        g.drawString("FPS: " + Integer.toString(this.lastFrames) + "/" + Game.MAX_FRAMES_PER_SECOND, 10, 12);
        g.drawString("TICKS: " + Integer.toString(this.lastTicks) + "/" + Game.MAX_TICKS_PER_SECOND, 10, 22);
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



    @Override
    public void tick() {
        return;
    }
    @Override
    public void collision(GameObject collisionObject) {
        return;
    }
    @Override
    public Rectangle getBounds() {
        return null;
    }

}

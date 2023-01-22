package GameObjects;

import Essentials.Handler;
import Utilities.FileHandler;
import Utilities.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goal extends GameObject{

    private transient BufferedImage imgGoal;
    public Goal(float x, float y, Handler handler) {
        super(x, y, ID.Goal, 32, 39, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.imgGoal, (int) x, (int) y, null);
    }

    @Override
    public void collision(GameObject collisionObject) {}

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, this.width, this.height);
    }

    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(FileHandler.loadImage("src/main/resources/goal.png"));
        this.imgGoal = ss.grabImage(0, 0, this.width, this.height);
    }
}

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goal extends GameObject{

    private transient BufferedImage imgGoal;
    public Goal(float x, float y, Handler handler) {
        super(x, y, ID.Goal, 32, 39);
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.imgGoal, (int) x, (int) y, null);
    }

    @Override
    public void collision(GameObject collisionObject) {
        return;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, this.width, this.height);
    }

    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(Utilities.loadImage("src/main/resources/goal.png"));
        this.imgGoal = ss.grabImage(0, 0, this.width, this.height);
    }
}

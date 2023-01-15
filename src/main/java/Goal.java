import java.awt.*;
import java.awt.image.BufferedImage;

public class Goal extends GameObject{

    private Game game;
    private Handler handler;
    private BufferedImage imgGoal;
    public Goal(float x, float y, Game game, Handler handler) {
        super(x, y, ID.Goal, 32, 39);
        this.game = game;
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
        SpriteSheet ss = new SpriteSheet(Game.loader.loadImage("/goal.png"));
        this.imgGoal = ss.grabImage(0, 0, this.width, this.height);
    }

    public void finish() {
        game.setGamestate(Game.STATE.Menu);
    }
}

import java.awt.*;

public class Player extends GameObject{

    private Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id, 32, 32);
        super.setObjectColor(Color.red);
        this.handler = handler;
    }

    public Rectangle getBounds() {return new Rectangle((int)x, (int)y, this.width, this.height);}

    public void tick() {
        x += getVelX();
        y += getVelY();

        x = Game.WallCollision(x, 1, Game.WIDTH-49);
        y = Game.WallCollision(y, 56, Game.HEIGHT-72);
        this.handler.checkCollision(this);
    }

    public void render(Graphics g) {
        g.setColor(getObjectColor());
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    public void collision(GameObject collisionObject) {
        return;
    }
}

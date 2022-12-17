import java.awt.*;

public class MarkerPoint extends GameObject{

    public MarkerPoint(float x, float y, ID id) {
        super(x, y, id, 10, 10);
    }
    @Override
    public void tick() {
        return;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    @Override
    public void collision(GameObject collisionObject) {
        return;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, this.width, this.height);
    }
}

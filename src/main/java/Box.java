import java.awt.*;

public class Box extends GameObject{

    public Box(float x, float y) {
        super(x, y, ID.Enemy, 32, 32);
        super.setObjectColor(Color.blue);
    }
    public void tick() {
        x += getVelX();
        y += getVelY();
    }
    public void render(Graphics g) {
        g.setColor(getObjectColor());
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    @Override
    public void collision(GameObject collisionObject) {
        return;
    }

    public Rectangle getBounds() {return new Rectangle ((int)x, (int)y, this.width, this.height);}

}

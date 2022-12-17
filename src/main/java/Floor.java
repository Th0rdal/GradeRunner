import java.awt.*;

public class Floor extends GameObject{


    public Floor(float x, float y) {
        super(x, y, ID.Floor, 500, 32);
        super.setObjectColor(Color.green);
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

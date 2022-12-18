import java.awt.*;

public class Platform extends GameObject{

    private boolean breakable;
    private Handler handler;
    public Platform(float x, float y, boolean breakable, Handler handler) {
        super(x, y, ID.Platform, 500, 32);
        super.setObjectColor(Color.green);
        this.breakable = breakable;
        this.handler = handler;
    }
    public void tick() {
        x += getVelX();
        y += getVelY();
    }
    public void render(Graphics g) {
        g.setColor(getObjectColor());
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    public void hitFromBelow() {
        if (this.breakable) {
            this.onBreak();
            this.handler.removeObject(this);
        }
    }

    public void onBreak() {
        return;
    }


    @Override
    public void collision(GameObject collisionObject) {
        return;
    }

    public Rectangle getBounds() {return new Rectangle ((int)x, (int)y, this.width, this.height);}


}

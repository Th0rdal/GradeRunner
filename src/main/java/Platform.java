/**
 * This class is a basic Platform.
 * The Player can jump on it and can break it by jumping against it from under it (if the flag is set)
 *
 */

import java.awt.*;

public class Platform extends GameObject{

    private boolean breakable;  //if true block can be broken
    private Handler handler;
    public Platform(float x, float y, boolean breakable, Handler handler) {
        super(x, y, ID.Platform, 500, 32);
        super.setObjectColor(Color.BLACK);
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

    public void hitFromBelow() {    //is activated when the platform is hit from below.
        if (this.breakable) {   //if breakable true, activate on break and remove the object from the screen
            //put in here what happens on break
            this.handler.removeObject(this);
        }
    }


    @Override
    public void collision(GameObject collisionObject) { //this object should do nothing on collision
        return;
    }

    public Rectangle getBounds() {return new Rectangle ((int)x, (int)y, this.width, this.height);}
    public void loadSprites() {return;}

}

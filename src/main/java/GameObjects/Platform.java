/**
 * Handles collisions, tick and render for Platforms
 *
 */

package GameObjects;

import Essentials.Handler;

import java.awt.*;

public class Platform extends GameObject {

    private final boolean breakable;  //if true block can be broken
    public Platform(float x, float y, int width, int height, boolean breakable, Handler handler) {
        super(x, y, ID.Platform, width, height, handler);
        super.setObjectColor(Color.BLACK);
        this.breakable = breakable;
    }
    public Platform(float x, float y, int width, int height, boolean breakable, Handler handler, ID id) {
        super(x, y, id, width, height, handler);
        super.setObjectColor(Color.BLACK);
        this.breakable = breakable;
    }
    public void tick() {    //method for all physics calculation
        x += getVelX();
        y += getVelY();
    }
    public void render(Graphics g) {    //method for all graphic calculations
        g.setColor(getObjectColor());
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    public void hitFromBelow() {    //is activated when the platform is hit from below.
        if (this.breakable) {   //if breakable true, activate on break and remove the object from the screen
            this.handler.removeObject(this);
        }
    }

    @Override
    public void collision(GameObject collisionObject) {}    //calculates collisions and reactions to them.

    public void loadSprites() {}    //loads the spriteSheet and sprites

    //getter
    public Rectangle getBounds() {  //returns a Rectangle that represents the object (for collision calculation)
        return new Rectangle ((int)x, (int)y, this.width, this.height);
    }


}

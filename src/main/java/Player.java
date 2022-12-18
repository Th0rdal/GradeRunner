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
        y -= getVelY();
        if (hasGravity()) {
            if (!this.isOnPlatform()) {
                setVelY(getVelY() - getGravity());
            }else {
                setVelY(0);
            }
        }

        if (Game.canScroll()) {
            x = Game.ScrollCollision(x, Game.scrollWidthLeft, Game.scrollWidthRight);
            if (Game.getScroll()) {
                Game.toggleScroll();
                this.handler.adjustScroll(getVelX());
            }
        }
        x = Game.WallCollision(x, 0, Game.WIDTH - 32);
        y = Game.WallCollision(y, 0, Game.HEIGHT - 69);
        this.handler.checkCollision(this);
    }

    public void render(Graphics g) {
        g.setColor(getObjectColor());
        g.fillRect((int)x, (int)y, this.width, this.height);
    }

    public void collision(GameObject collisionObject) {
        if (collisionObject.getID() == ID.Platform) {
            if (this.getY() < collisionObject.getY()) {
                y = collisionObject.getY() - this.getHeight();
            }else {
                y = collisionObject.getY() + collisionObject.getHeight();
                ((Platform)collisionObject).hitFromBelow();
            }
        }
    }

    public boolean isOnPlatform() {    //checks if the given GameObject is on a Platform
        for (GameObject tempObject : this.handler.getObjectList()) {
            if (tempObject.getID() != ID.Platform) {
                continue;
            }
            if (new Rectangle((int)this.getX(), (int)this.getY()+5, (int)this.getWidth(), (int)this.getHeight()).intersects(tempObject.getBounds())) {
                this.collision(tempObject);
                return true;
            }
        }
        return false;
    }

}

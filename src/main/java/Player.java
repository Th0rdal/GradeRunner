import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private transient BufferedImage imgLookRight, imgWalkRight1, imgWalkRight2, imgLookLeft, imgWalkLeft1, imgWalkLeft2;
    private int counter = 0;
    private boolean walkRight = false;


    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id, 12, 17);
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

    public void render(Graphics g) {;
        if (this.getVelY() != 0) {  //spritesheets while in air
            if (this.getVelX() > 0){
                g.drawImage(this.imgLookRight, (int) x, (int) y, null);
            }else if (this.getVelX() < 0) {
                g.drawImage(this.imgLookLeft, (int)x, (int)y, null);
            }else {
                g.drawImage(this.imgLookRight, (int) x, (int) y, null);
            }
        }else { //spritesheets while on ground
            if (this.getVelX() > 0) {   //running right
                if (counter % 3 == 0) {
                    g.drawImage(this.imgLookRight, (int) x, (int) y, null);
                    this.counter++;
                } else if (counter % 3 == 1) {
                    g.drawImage(this.imgWalkRight1, (int) x, (int) y, null);
                    this.counter++;
                } else if (counter % 3 == 2) {
                    g.drawImage(this.imgWalkRight2, (int) x, (int) y, null);
                    this.counter = 0;
                }
            }else if (this.getVelX() < 0) { //running left
                if (counter % 3 == 0) {
                    g.drawImage(this.imgLookLeft, (int) x, (int) y, null);
                    this.counter++;
                } else if (counter % 3 == 1) {
                    g.drawImage(this.imgWalkLeft1, (int) x, (int) y, null);
                    this.counter++;
                } else if (counter % 3 == 2) {
                    g.drawImage(this.imgWalkLeft2, (int) x, (int) y, null);
                    this.counter = 0;
                }
            }else { //when not moving
                g.drawImage(this.imgLookRight, (int) x, (int) y, null);
            }
        }
    }

    public void collision(GameObject collisionObject) {
        if (collisionObject.getID() == ID.Platform) {
            float tempY, tempX;
            boolean hitFromBelowFlag = false;
            if (this.getY() < collisionObject.getY()) {
                tempY = collisionObject.getY() - this.getHeight();
            }else {
                tempY = collisionObject.getY() + collisionObject.getHeight();
                hitFromBelowFlag = true;
            }
            if (this.getX() < collisionObject.getX()) {
                tempX = collisionObject.getX() - this.getWidth();
            }else {
                tempX = collisionObject.getX() + collisionObject.getWidth();
            }

            if (Math.abs(tempX - this.getX()) < Math.abs(tempY - this.getY())) {
                x = tempX;
                y += this.getGravity();
            }else {
                y = tempY;
                if (hitFromBelowFlag) {
                    ((Platform)collisionObject).hitFromBelow();
                    this.setVelY(0);
                }
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
    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        this.imgLookRight = ss.grabImage(0, 0, 12, 17);
        this.imgWalkRight1 = ss.grabImage(1, 0, 12, 17);
        this.imgWalkRight2 = ss.grabImage(2, 0, 12, 17);
        this.imgLookLeft = ss.grabImage(3, 0, 12, 17);
        this.imgWalkLeft1 = ss.grabImage(4, 0, 12, 17);
        this.imgWalkLeft2 = ss.grabImage(5, 0, 12, 17);
    }

}

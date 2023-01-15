import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private transient BufferedImage imgLookRight, imgWalkRight1, imgWalkRight2, imgLookLeft, imgWalkLeft1, imgWalkLeft2;
    private int spriteCounter = 0;
    private boolean walkRight = false;
    private float jumpVel = 15.0f;


    public Player(float x, float y, Handler handler) {
        super(x, y, ID.Player, 36, 51);
        this.handler = handler;
     }

    public Rectangle getBounds() {return new Rectangle((int)x, (int)y, this.width, this.height);}

    public void tick() {
        x += getVelX();
        y -= getVelY();
        if (hasGravity()) {
            if (!this.handler.isOnPlatform(this)) {
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
                if (spriteCounter % 3 == 0) {
                    g.drawImage(this.imgLookRight, (int) x, (int) y, null);
                    this.spriteCounter++;
                } else if (spriteCounter % 3 == 1) {
                    g.drawImage(this.imgWalkRight1, (int) x, (int) y, null);
                    this.spriteCounter++;
                } else if (spriteCounter % 3 == 2) {
                    g.drawImage(this.imgWalkRight2, (int) x, (int) y, null);
                    this.spriteCounter = 0;
                }
            }else if (this.getVelX() < 0) { //running left
                if (spriteCounter % 3 == 0) {
                    g.drawImage(this.imgLookLeft, (int) x, (int) y, null);
                    this.spriteCounter++;
                } else if (spriteCounter % 3 == 1) {
                    g.drawImage(this.imgWalkLeft1, (int) x, (int) y, null);
                    this.spriteCounter++;
                } else if (spriteCounter % 3 == 2) {
                    g.drawImage(this.imgWalkLeft2, (int) x, (int) y, null);
                    this.spriteCounter = 0;
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
        }else if (collisionObject.getID() == ID.Enemy) {
            if (this.getY() < collisionObject.getY()) {
                ((Enemy)collisionObject).hitFromAbove();
                this.jump();
            }else {
                this.hit();
            }
        }
    }

    public void hit() {
        this.setX(200.0f);
        this.setY(200.0f);
    }

    public void jump() {
        this.setVelY(this.jumpVel);
    }

    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(Game.loader.loadImage("/player.png"));
        this.imgLookRight = ss.grabImage(0, 0, this.width, this.height);
        this.imgWalkRight1 = ss.grabImage(1, 0, this.width, this.height);
        this.imgWalkRight2 = ss.grabImage(2, 0, this.width, this.height);
        this.imgLookLeft = ss.grabImage(3, 0, this.width, this.height);
        this.imgWalkLeft1 = ss.grabImage(4, 0, this.width, this.height);
        this.imgWalkLeft2 = ss.grabImage(5, 0, this.width, this.height);
    }

}

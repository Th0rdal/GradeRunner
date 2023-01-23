package GameObjects;

import Essentials.Handler;
import Utilities.FileHandler;
import Utilities.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject{

    private transient BufferedImage imgWalkRight1, imgWalkRight2, imgWalkLeft1, imgWalkLeft2;
    private int spriteCounter = 0;
    public Enemy(float x, float y, Handler handler) {
        super(x, y, ID.Enemy, 38, 51, handler, 100);
        this.setVelX(3.5f);
    }

    @Override
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
        this.handler.checkCollision(this);
    }

    @Override
    public void render(Graphics g) {
        if (this.getVelX() > 0) {   //running right
            if (spriteCounter % 2 == 0) {
                g.drawImage(this.imgWalkRight1, (int) x, (int) y, null);
                this.spriteCounter++;
            } else if (spriteCounter % 2 == 1) {
                g.drawImage(this.imgWalkRight2, (int) x, (int) y, null);
                this.spriteCounter--;
            }
        }else if (this.getVelX() < 0) {
            if (spriteCounter % 2 == 0) {
                g.drawImage(this.imgWalkLeft1, (int) x, (int) y, null);
                this.spriteCounter++;
            } else if (spriteCounter % 2 == 1) {
                g.drawImage(this.imgWalkLeft2, (int) x, (int) y, null);
                this.spriteCounter--;
            }
        }else {
            g.drawImage(this.imgWalkRight1, (int) x, (int) y, null);
        }
    }

    @Override
    public void collision(GameObject collisionObject) {
        if (collisionObject.getID() == ID.Platform) {
            float[] collisionDirectionArray = super.collisionDirection(collisionObject);
            float tempY = collisionDirectionArray[0];
            float tempX = collisionDirectionArray[1];

            if (Math.abs(tempX - this.getX()) < Math.abs(tempY - this.getY())) {
                x = tempX;
                this.setVelX(this.getVelX() * -1.0f);
                y += this.getGravity();
            }else {
                y = tempY;
            }
        }else if (collisionObject.getID() == ID.Enemy) {
            float[] collisionDirectionArray = super.collisionDirection(collisionObject);
            float tempY = collisionDirectionArray[0];
            float tempX = collisionDirectionArray[1];

            if (Math.abs(tempX - this.getX()) < Math.abs(tempY - this.getY())) {
                this.setVelX(this.getVelX() * -1.0f);
                collisionObject.setVelX(this.getVelX() * -1.0f);
            }
        }
    }

    public void hitFromAbove() {
        this.handler.removeObject(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, this.width, this.height);
    }

    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(FileHandler.loadImage("src/main/resources/enemy.png"));
        this.imgWalkRight1 = ss.grabImage(3, 0, this.width, this.height);
        this.imgWalkRight2 = ss.grabImage(2, 0, this.width, this.height);
        this.imgWalkLeft1 = ss.grabImage(1, 0, this.width, this.height);
        this.imgWalkLeft2 = ss.grabImage(0, 0, this.width, this.height);
    }
}
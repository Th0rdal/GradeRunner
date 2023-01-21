package GameObjects;

import Essentials.Game;
import Essentials.Handler;
import Utilities.SpriteSheet;
import Utilities.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private transient BufferedImage imgLookRight, imgWalkRight1, imgWalkRight2, imgLookLeft, imgWalkLeft1, imgWalkLeft2;
    private int spriteCounter = 0;
    private float jumpVel = 15.0f;
    private float moveVel = 5.0f;

    public enum moveState {
        left,
        right,
        sprint,
        sprintStop,
        jump,
        stop

    }


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

    public void render(Graphics g) {
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

    private float[] collisionDirection(GameObject collisionObject) {
        float[] returnArray = new float[4];
        if (this.getY() < collisionObject.getY()) {
            returnArray[0] = collisionObject.getY() - this.getHeight();
            returnArray[2] = 0.0f;  //hit above
        }else {
            returnArray[0] = collisionObject.getY() + collisionObject.getHeight();
            returnArray[2] = 1.0f;  //hit below
        }
        if (this.getX() < collisionObject.getX()) {
            returnArray[1] = collisionObject.getX() - this.getWidth();
            returnArray[3] = 0.0f;  //hit left
        }else {
            returnArray[1] = collisionObject.getX() + collisionObject.getWidth();
            returnArray[3] = 1.0f;  //hit right
        }
        return returnArray;
    }

    public void collision(GameObject collisionObject) {
        if (collisionObject.getID() == ID.Platform) {
            float[] collisionDirectionArray = this.collisionDirection(collisionObject);
            float tempY = collisionDirectionArray[0];
            float tempX = collisionDirectionArray[1];

            if (Math.abs(tempX - this.getX()) < Math.abs(tempY - this.getY())) {
                x = tempX;
                y += this.getGravity();
            }else {
                y = tempY;
                if (collisionDirectionArray[2] == 1.0f) {
                    ((Platform)collisionObject).hitFromBelow();
                    this.setVelY(0);
                }
            }
        }else if (collisionObject.getID() == ID.Enemy) {
            float[] collisionDirectionArray = this.collisionDirection(collisionObject);
            float tempY = collisionDirectionArray[0];
            float tempX = collisionDirectionArray[1];
            if (Math.abs(tempX - this.getX()) < Math.abs(tempY - this.getY())) {
                this.handler.hit();
            }else {
                if (collisionDirectionArray[2] == 0.0f) {
                    ((Enemy)collisionObject).hitFromAbove();
                    this.move(moveState.jump);
                }else {
                    this.handler.hit();
                }
            }
        }else if (collisionObject.getID() == ID.Goal) {
            this.handler.finish();
        }
    }

    public void move(moveState state) {
        if (state == moveState.left) {
            this.setVelX(-1.0f * this.moveVel);
        }else if (state == moveState.right) {
            this.setVelX(this.moveVel);
        }else if (state == moveState.jump) {
            this.setVelY(this.jumpVel);
        }else if (state == moveState.sprint) {
            this.moveVel = this.moveVel * 2.0f;
            this.jumpVel = this.jumpVel * 1.2f;
            this.setVelX(getVelX() * 2);
        }else if (state == moveState.sprintStop) {
            this.moveVel = this.moveVel / 2.0f;
            this.jumpVel = this.jumpVel / 1.2f;
            this.setVelX(getVelX() / 2);
        }else if (state == moveState.stop) {
            this.setVelX(0.0f);
        }
    }

    @Override
    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(FileHandler.loadImage("src/main/resources/player.png"));
        this.imgLookRight = ss.grabImage(0, 0, this.width, this.height);
        this.imgWalkRight1 = ss.grabImage(1, 0, this.width, this.height);
        this.imgWalkRight2 = ss.grabImage(2, 0, this.width, this.height);
        this.imgLookLeft = ss.grabImage(3, 0, this.width, this.height);
        this.imgWalkLeft1 = ss.grabImage(4, 0, this.width, this.height);
        this.imgWalkLeft2 = ss.grabImage(5, 0, this.width, this.height);
    }

}

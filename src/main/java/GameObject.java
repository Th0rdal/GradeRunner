/**
 * Blueprint class for all GameObjects.
 * Includes all important variables and methods any GameObject needs to have
 */

import java.awt.*;

public abstract class GameObject {

    protected float x, y;    //x and y values on screen
    protected float VelX, VelY; //velocity in x and y direction
    protected float gravity = 1.0f;
    protected ID id;
    protected int width, height;
    protected Color objectColor;
    protected boolean bGravity = true, breakable;
    public GameObject(float x, float y, ID id, int width, int height) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();    //physic calculations in this method
    public abstract void render(Graphics g); //anything drawing related in this method

    public abstract void collision(GameObject collisionObject); //collision handling in this method

    public abstract Rectangle getBounds();  //returns a Rectangle that represents the object (for collision calculation)
    public void adjustForScroll(float adjustX) {
        x += adjustX;
    }   //adjusts the position of the object when scrolling

    //getter and setter
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return this.y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getVelX() {
        return VelX;
    }
    public void setVelX(float velX) {
        VelX = velX;
    }
    public float getVelY() {
        return VelY;
    }
    public void setVelY(float velY) {
        VelY = velY;
    }
    public float getWidth() {return this.width;}
    public float getHeight() {return this.height;}
    public float getGravity() {return this.gravity;}
    public boolean hasGravity() {return this.bGravity;}
    public ID getID() {return this.id;}
    public Color getObjectColor() {return this.objectColor;}
    public void setObjectColor(Color tempColor) {this.objectColor = tempColor;}
}

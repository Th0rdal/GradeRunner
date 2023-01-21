/**
 * Blueprint class for all GameObjects.
 * Includes all important variables and methods any GameObjects.GameObject needs to have
 */
package GameObjects;

import Essentials.Handler;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable, Cloneable{

    protected float x, y;    //x and y values on screen
    protected float VelX, VelY; //velocity in x and y direction
    protected float gravity = 1.0f;
    protected ID id;
    protected int width, height;
    protected Color objectColor;
    protected boolean bGravity = true;
    protected transient Handler handler;
    public GameObject(float x, float y, ID id, int width, int height) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public GameObject(float x, float y, ID id, int width, int height, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    protected float[] collisionDirection(GameObject collisionObject) {
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

    public abstract void tick();    //physic calculations in this method
    public abstract void render(Graphics g); //anything drawing related in this method

    public abstract void collision(GameObject collisionObject); //collision handling in this method

    public abstract Rectangle getBounds();  //returns a Rectangle that represents the object (for collision calculation)
    public void adjustForScroll(float adjustX) {
        x += adjustX;
    }   //adjusts the position of the object when scrolling
    public abstract void loadSprites();
    //getter and setter
    public float getX() {
        return x;
    }
    public float getY() {
        return this.y;
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
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

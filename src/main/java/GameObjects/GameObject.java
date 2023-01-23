/**
 * Blueprint class for all GameObjects.
 * Includes all important variables and methods any GameObject needs to have
 */

package GameObjects;

import Essentials.Handler;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable, Cloneable{

    protected float x, y;    //x and y values on screen
    protected float VelX, VelY; //velocity in x and y direction
    protected float gravity = 1.0f; //gravitational acceleration
    protected ID id;    //GameObject ID
    protected int width, height;    //width and height of the object
    protected Color objectColor;    //color for the objects that do not have sprites
    protected boolean bGravity = true;  //saves if object has gravity affect it
    protected transient Handler handler;
    protected long scoreAdd = 0;    //points to add to score on kill

    public GameObject(float x, float y, ID id, int width, int height, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    public GameObject(float x, float y, ID id, int width, int height, Handler handler, long scoreAdd) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.scoreAdd = scoreAdd;
    }

    protected float[] collisionDirection(GameObject collisionObject) {  //calculates in which direction the collision took place
        float[] returnArray = new float[4];
        if (this.getY() < collisionObject.getY()) { //hit above
            returnArray[0] = collisionObject.getY() - this.getHeight();
            returnArray[2] = 0.0f;
        }else { //hit below
            returnArray[0] = collisionObject.getY() + collisionObject.getHeight();
            returnArray[2] = 1.0f;
        }
        if (this.getX() < collisionObject.getX()) { //hit left
            returnArray[1] = collisionObject.getX() - this.getWidth();
            returnArray[3] = 0.0f;
        }else { //hit right
            returnArray[1] = collisionObject.getX() + collisionObject.getWidth();
            returnArray[3] = 1.0f;
        }
        return returnArray;
    }

    public void adjustForScroll(float adjustX) {
        x += adjustX;
    }   //adjusts the position of the object when scrolling
    public Object clone() throws CloneNotSupportedException {   //clones the GameObject
        return super.clone();
    }

    //abstract methods
    public abstract void tick();    //physic calculations in this method
    public abstract void render(Graphics g); //method for all graphic calculations

    public abstract void collision(GameObject collisionObject); //calculates collisions and reactions to them
    public abstract void loadSprites(); //loading of the sprites the object needs
    public abstract Rectangle getBounds();  //returns a Rectangle that represents the object (for collision calculation)


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
    public long getScoreAdd() {
        return this.scoreAdd;
    }
}

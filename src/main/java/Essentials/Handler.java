/**
 * This class handles all GameObjects.
 * Calls tick and render for all GameObjects, does collision checking, adjusts for scrolling
 */

package Essentials;

import GameObjects.GameObject;
import GameObjects.ID;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Handler {
    private final Game game;
    private final LinkedList<GameObject> objectList = new LinkedList<>();   //list of all GameObjects
    private final ConcurrentLinkedDeque<GameObject> deleteQueue = new ConcurrentLinkedDeque<>();    //queue with all GameObjects to delete
    private float moved = 0;
    private float totalLength = 0.0f;
    public Handler(Game game) {
        this.game = game;
    }

    public Handler(Game game, int totalLength) {
        this.game = game;
        this.totalLength = totalLength;
    }


    public void tick() {
        for (GameObject tempObject : this.objectList) {
            tempObject.tick();
        }
        while (!this.deleteQueue.isEmpty()) {
            objectList.remove(deleteQueue.pop());
        }
    }

    public void render(Graphics g) {
        for (GameObject tempObject : this.objectList) {
            tempObject.render(g);
        }
    }

    public void checkCollision(GameObject object) { //checks the collisions of the parameter object with all other objects
        for (GameObject tempObject : this.objectList) {
            if (tempObject == object) {
                return;
            }
            if (object.getBounds().intersects(tempObject.getBounds())) {
                object.collision(tempObject);
            }
        }
    }

    public void adjustScroll(float velX) {  //adjusts the screen for scrolling
        this.moved += (-1.0f * velX);
        Game.canScrollLeft = (this.moved <= 0);
        Game.canScrollRight = (this.moved >= this.totalLength);
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() == ID.Player) {
                continue;
            }
            tempObject.adjustForScroll(-1.0f * velX);
        }
    }

    public GameObject getPlayer() {
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() == ID.Player) {
                return tempObject;
            }
        }
        return null;
    }
    public void loadImages() {
        for (GameObject tempObject : this.objectList) {
            tempObject.loadSprites();
        }
    }

    public boolean isOnPlatform(GameObject object) {    //checks if the given GameObjects.GameObject is on a GameObjects.Platform
        for (GameObject tempObject : this.getObjectList()) {
            if (tempObject.getID() != ID.Platform) {
                continue;
            }
            if (new Rectangle((int)object.getX(), (int)object.getY()+5, (int)object.getWidth(), (int)object.getHeight()).intersects(tempObject.getBounds())) {
                object.collision(tempObject);
                return true;
            }
        }
        return false;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = ((float) totalLength - 1250.0f + 15.0f) * -1.0f;
    }
    public void addObject(GameObject object) {this.objectList.add(object);}
    public void removeObject(GameObject object) {this.deleteQueue.push(object);}
    public void clear() {
        this.objectList.clear();
    }
    public LinkedList<GameObject> getObjectList() {return this.objectList;}
    public void finish() {
        game.setGamestate(Game.STATE.VictoryScreen);
    }
    public void hit() {
        game.setGamestate(Game.STATE.DeathScreen);
    }
    public float getMoved() {return this.moved;}
    public float getTotalLength(){return this.totalLength;}

}

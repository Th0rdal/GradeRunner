/**
 * This class handles all GameObjects.
 * Calls tick and render for all GameObjects, does collision checking, adjusts for scrolling
 */

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Handler {
    private LinkedList<GameObject> objectList = new LinkedList<GameObject>();   //list of all GameObjects
    private ConcurrentLinkedDeque deleteQueue = new ConcurrentLinkedDeque();    //queue with all GameObjects to delete
    private float moved = 0;

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
            if (tempObject.getID() == object.getID()) {
                continue;
            }
            if (object.getBounds().intersects(tempObject.getBounds())) {
                object.collision(tempObject);
            }
        }
    }

    public void adjustScroll(float velX) {  //adjusts the screen for scrolling
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() == ID.Player) {
                continue;
            }
            this.moved += (-1.0f * velX);
            if (moved == 0) {
                Game.canScrollLeft = false;
            }else if (moved < 0) {
                Game.canScrollLeft = true;
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

    public void addObject(GameObject object) {this.objectList.add(object);}
    public void removeObject(GameObject object) {this.deleteQueue.push(object);}
    public void clear() {
        this.objectList.clear();
    }
    public LinkedList<GameObject> getObjectList() {return this.objectList;}

}

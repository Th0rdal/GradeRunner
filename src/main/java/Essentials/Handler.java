/**
 * This class handles all GameObjects.
 * Calls tick and render for all GameObjects,
 * does collision checking,
 * adjusts for scrolling,
 * loads all GameObject images,
 * loads Level and creates HUD,
 */

package Essentials;

import GameObjects.GameObject;
import GameObjects.ID;
import GameObjects.Platform;
import Saves.Level;
import Screens.HUD;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Handler {
    private final Game game;
    private final HUD hud;
    private final LinkedList<GameObject> objectList = new LinkedList<>();   //list of all GameObjects
    private final ConcurrentLinkedDeque<GameObject> deleteQueue = new ConcurrentLinkedDeque<>();    //queue with all GameObjects to delete
    private float moved = 0;
    private float totalLength;

    public Handler(Game game) {
        this.game = game;
        this.hud = new HUD(this.game);
    }

    public void tick() {    //method for all physics calculation
        for (GameObject tempObject : this.objectList) {
            tempObject.tick();
        }
        while (!this.deleteQueue.isEmpty()) {
            objectList.remove(deleteQueue.pop());
        }
    }

    public void render(Graphics g) {    //method for all graphic calculations
        for (GameObject tempObject : this.objectList) {
            tempObject.render(g);
        }
    }

    public void checkCollision(GameObject object) { //checks the collisions of the parameter object with all other objects
        for (GameObject tempObject : this.objectList) {
            if (tempObject == object) {
                continue;
            }
            if (object.getBounds().intersects(tempObject.getBounds())) {
                if (tempObject.getID() == ID.Death) {
                    if (object.getID() == ID.Player) {
                        this.game.setGamestate(Game.STATE.DeathScreen);
                        return;
                    }
                    this.removeObject(object);
                    return;
                }
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

    public void loadImages() {  //loads all images of GameObjects
        for (GameObject tempObject : this.objectList) {
            tempObject.loadSprites();
        }
    }

    public boolean isOnPlatform(GameObject object) {    //checks if the given GameObject is on a Platform
        for (GameObject tempObject : this.getObjectList()) {
            if (tempObject.getID() != ID.Platform) {
                continue;
            }
            if (new Rectangle((int)object.getX(), (int)object.getY()+5, (int)object.getWidth(), (int)object.getHeight()).intersects(tempObject.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void loadLevel(Level l) {    //loads level and creates walls around the level
        l.load(this.game, this);
        this.hud.setupHUD(l.getLevelTime());
        this.addObject(new Platform(-32, 0, 32, Game.HEIGHT, false, this));
        this.addObject(new Platform(l.getTotalLength(), 0, 32, Game.HEIGHT, false, this));
        this.addObject(new Platform(0, Game.HEIGHT+64, l.getTotalLength(), 32, false, this, ID.Death));

    }

    public void addObject(GameObject object) {this.objectList.add(object);}
    public void removeObject(GameObject object) {
        this.deleteQueue.push(object);
    }
    public void clear() {   //resets the handler
        this.objectList.clear();
        this.deleteQueue.clear();
        this.moved = 0;
    }

    public void finish() {  //method for when the player reaches the goal
        this.hud.finish();
        game.setGamestate(Game.STATE.VictoryScreen);
    }

    public void addToScore(long score) {
        this.hud.addToScore(score);
    }

    public void hit() { //method for when the player gets hit
        game.setGamestate(Game.STATE.DeathScreen);
    }

    //getter and setter
    public void setTotalLength(int totalLength) {   //calculated the totalLength for scrolling
        this.totalLength = ((float) totalLength - 1250.0f + 10.0f) * -1.0f;
    }
    public LinkedList<GameObject> getObjectList() {return this.objectList;}
    public float getMoved() {return this.moved;}
    public float getTotalLength(){return this.totalLength;}
    public HUD getHUD() {
        return this.hud;
    }
    public GameObject getPlayer() { //returns the player object
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() == ID.Player) {
                return tempObject;
            }
        }
        return null;
    }
}

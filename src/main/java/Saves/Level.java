/**
 * This class saves a world in the game.
 *
 */
package Saves;

import Essentials.Game;
import GameObjects.GameObject;
import Essentials.Handler;
import Utilities.Encryption;
import Utilities.FileHandler;

import java.io.*;
import java.util.LinkedList;

public class Level implements Serializable{
    @Serial
    private static final long serialVersionUID = 3538199102484134650L;
    private final String name;    //name of the world
    private final String pathToFile;  //path to the file
    private final int totalLength;
    private final int levelTime;
    private transient Handler handler;

    private final LinkedList<GameObject> blocks = new LinkedList<>(); //a list with all blocks in the level

    public Level(String name, Handler handler, int totalLength, int time) {
        long timeOfCreation = System.nanoTime();
        this.name = name;
        String encryptionCode = Encryption.encryptWorld(timeOfCreation, this.name);
        this.handler = handler;
        this.pathToFile = "saves/worlds/" + encryptionCode + ".world";
        this.totalLength = totalLength;
        this.levelTime = time;
    }

    public void load(Game game, Handler handler) {    //loads the level onto the screen
        this.handler = handler;
        this.handler.clear();
        this.handler.setTotalLength(this.totalLength);
        for (GameObject tempObject : this.blocks) {
            try {
                GameObject tempCopy = (GameObject) tempObject.clone();
                this.handler.addObject(tempCopy);
                tempCopy.setHandler(this.handler);
            }catch (CloneNotSupportedException e) {
                e.printStackTrace();
                game.getWindow().error("Could not load World!");
            }
        }
    }

    public void save() { //saves the level to a file
        this.blocks.addAll(this.handler.getObjectList());
        FileHandler.saveObjectToFile(this, this.pathToFile);
    }
    public int levelTime() {
        return this.levelTime;
    }
    public String getName() {
        return this.name;
    }
    public int getTotalLength() {
        return this.totalLength;
    }

}

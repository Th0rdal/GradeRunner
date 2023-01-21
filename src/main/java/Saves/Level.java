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
    private final String encryptionCode;  //a unique code for each world
    private final String encryptedName;   //encrypted name of the world
    private final long timeOfCreation;    //the time the object was created in nanoSeconds
    private final int totalLength;
    private transient Handler handler;

    private final LinkedList<GameObject> blocks = new LinkedList<>(); //a list with all blocks in the level

    public Level(String name, Handler handler, int totalLength) {
        this.timeOfCreation = System.nanoTime();
        this.name = name;
        this.encryptedName = Encryption.encryptName(this.name, this.timeOfCreation);
        this.encryptionCode = Encryption.encryptWorld(this.timeOfCreation, this.name);
        this.handler = handler;
        this.pathToFile = "saves/worlds/" + this.encryptionCode + ".world";
        this.totalLength = totalLength;
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

    public String getName() {
        return this.name;
    }

}
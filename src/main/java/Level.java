/**
 * This class saves a world in the game.
 *
 */

import java.io.*;
import java.util.LinkedList;

public class Level implements Serializable {

    private String name;    //name of the world
    private String pathToFile;  //path to the file
    private String encryptionCode;  //a unique code for each world
    private String encryptedName;   //encrypted name of the world
    private long timeOfCreation;    //the time the object was created in nanoSeconds
    private transient Handler handler;

    private LinkedList<GameObject> blocks = new LinkedList<>(); //a list with all blocks in the level

    public Level(String name, Handler handler) {
        this.timeOfCreation = System.nanoTime();
        this.name = name;
        this.encryptedName = Utilities.encryptName(this.name, this.timeOfCreation);
        this.encryptionCode = Utilities.encryptWorld(this);
        this.handler = handler;
        this.pathToFile = "saves/worlds/" + this.encryptionCode + ".world";
    }

    public void load(Handler handler) {    //loads the level onto the screen
        this.handler = handler;
        this.handler.clear();
        System.out.println(this.blocks.size());
        for (GameObject tempObject : this.blocks) {
            this.handler.addObject(tempObject);
            tempObject.setHandler(this.handler);
        }
    }

    public void save() { //saves the level to a file
        for (GameObject tempObject : this.handler.getObjectList()) {
            this.blocks.add(tempObject);
        }
        Utilities.saveObjectToFile(this, this.pathToFile);
    }

    //getters
    public String getName() {return this.name;}
    public String getEncryptedName() {return this.encryptedName;}
    public Long getCreationTime() {return this.timeOfCreation;}
}

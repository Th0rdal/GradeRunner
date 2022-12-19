/**
 * This class takes care of the different save files
 */

import java.io.Serializable;
import java.util.HashMap;

public class SaveFile implements Serializable {

    private String name;
    private HashMap<String, Integer> LevelsComplete;    //encrypted level name / score
    private long creationTime;
    private String encryptedName;
    private String pathToFile;
    public SaveFile(String name) {
        this.creationTime = System.nanoTime();
        this.name = name;
        this.encryptedName = Utilities.encryptName(this);
        this.pathToFile = "saves/players/" + encryptedName;
    }
    public void save() {
        Utilities.saveObjectToFile(this, this.pathToFile);
    }

    //getter
    public String getName() {return this.name;}
    public Long getCreationTime() {return this.creationTime;}

}

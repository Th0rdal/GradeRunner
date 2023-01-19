/**
 * This class takes care of the different save files
 */

import java.io.Serializable;
import java.util.HashMap;

public class SaveFile implements Serializable {

    private final String name;
    private HashMap<String, Integer> LevelsComplete;    //encrypted level name / score
    private final long creationTime;
    private final String encryptedName;
    private final String pathToFile;
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

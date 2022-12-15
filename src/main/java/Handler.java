import java.awt.*;
import java.util.LinkedList;

public class Handler {
    private LinkedList<GameObject> objectList = new LinkedList<>();

    public void tick() {
        for (GameObject tempObject : this.objectList) {
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject tempObject : this.objectList) {
            tempObject.render(g);
        }
    }

    public void checkCollision(GameObject object) {
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() == object.getID()) {
                continue;
            }
            if (object.getBounds().intersects(tempObject.getBounds())) {
                object.collision(tempObject);
            }
        }
    }

    public void addObject(GameObject object) {this.objectList.add(object);}
    public void removeObject(GameObject object) {this.objectList.remove(object);}
    public void clear() {
        this.objectList.clear();
    }

    public LinkedList<GameObject> getObjectList() {return this.objectList;}

    private int pass() {
        return 1;
    }
}

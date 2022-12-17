import java.awt.*;
import java.util.LinkedList;

public class Handler {
    private LinkedList<GameObject> objectList = new LinkedList<>();
    private float moved = 0;

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

    public void adjustScroll(float velX) {
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

    public boolean isOnPlatform(GameObject object) {
        for (GameObject tempObject : this.objectList) {
            if (tempObject.getID() != ID.Floor) {
                continue;
            }
            if (new Rectangle((int)object.getX(), (int)object.getY()+5, (int)object.getWidth(), (int)object.getHeight()).intersects(tempObject.getBounds())) {
                object.collision(tempObject);
                return true;
            }
        }
        return false;
    }


    public void addObject(GameObject object) {this.objectList.add(object);}
    public void removeObject(GameObject object) {this.objectList.remove(object);}
    public void clear() {
        this.objectList.clear();
    }
    public LinkedList<GameObject> getObjectList() {return this.objectList;}

}

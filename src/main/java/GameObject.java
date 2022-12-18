import java.awt.*;

public abstract class GameObject {

    protected float x, y;    //x and y values on screen
    protected float VelX, VelY; //velocity in x and y direction
    protected float gravity = 1.0f;
    protected ID id;
    protected int width, height;
    protected Color objectColor;
    protected boolean bGravity = true, breakable;
    public GameObject(float x, float y, ID id, int width, int height) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public abstract void collision(GameObject collisionObject);

    public abstract Rectangle getBounds();
    public void adjustForScroll(float adjustX) {
        x += adjustX;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return this.y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getVelX() {
        return VelX;
    }
    public void setVelX(float velX) {
        VelX = velX;
    }
    public float getVelY() {
        return VelY;
    }
    public void setVelY(float velY) {
        VelY = velY;
    }
    public float getWidth() {return this.width;}
    public float getHeight() {return this.height;}
    public float getGravity() {return this.gravity;}
    public boolean hasGravity() {return this.bGravity;}
    public ID getID() {return this.id;}
    public Color getObjectColor() {return this.objectColor;}
    public void setObjectColor(Color tempColor) {this.objectColor = tempColor;}
}

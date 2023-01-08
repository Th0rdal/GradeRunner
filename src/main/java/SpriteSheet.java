import java.awt.image.BufferedImage;
public class SpriteSheet {
    private final BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return sprite.getSubimage((col * width), (row * height), width, height);
    }
}

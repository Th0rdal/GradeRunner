/**
 * Handles SpriteSheets and grabs sprites from it
 */

package Utilities;

import java.awt.image.BufferedImage;
public class SpriteSheet {
    private final BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {   //grabs a sprite from the spritesheet
        return sprite.getSubimage((col * width), (row * height), width, height);
    }
}

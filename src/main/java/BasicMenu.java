import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class BasicMenu extends MouseAdapter {

    protected Game game;
    protected Handler handler;
    protected Audio backgroundAudio;
    protected BufferedImage imgUnmute, imgMute;

    public BasicMenu(Game game, Handler handler, Audio audio) {
        this.game = game;
        this.handler = handler;
        this.backgroundAudio = audio;
        this.loadSprites();
    }

    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(Utilities.loadImage("src/main/resources/soundImg.png"));
        this.imgUnmute = ss.grabImage(0, 0, 50, 50);
        this.imgMute = ss.grabImage(1, 0, 50, 50);
    }

    public void render(Graphics g) {
        if (this.backgroundAudio.musicPlaying()) {
            g.drawImage(this.imgUnmute, 1100, 50, null);
        }else {
            g.drawImage(this.imgMute, 1100, 50, null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Utilities.mouseOverBox(mx, my, 1100,50,50,50)) {
            if (this.backgroundAudio.musicPlaying()) {
                this.backgroundAudio.mute();
            }else {
                this.backgroundAudio.unmute();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {

    }

    public abstract void tick();

}

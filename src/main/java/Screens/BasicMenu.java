/**
 * Blueprint class for all Menu
 * Includes all important variables and methods any Menu needs to have
 */

package Screens;

import Essentials.Game;
import Essentials.Handler;
import Settings.Audio;
import Utilities.SpriteSheet;
import Utilities.FileHandler;

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

    //methods
    public void render(Graphics g) {    //method for all graphic calculations
        if (this.backgroundAudio.musicPlaying()) {
            g.drawImage(this.imgUnmute, 1100, 50, null);
        }else {
            g.drawImage(this.imgMute, 1100, 50, null);
        }
    }

    public void loadSprites() { //loading of the sprites the object needs
        SpriteSheet ss = new SpriteSheet(FileHandler.loadImage("src/main/resources/soundImg.png"));
        this.imgUnmute = ss.grabImage(0, 0, 50, 50);
        this.imgMute = ss.grabImage(1, 0, 50, 50);
    }

    //mouse events
    public void mouseMoved(MouseEvent e) {} //handles all events when mouse is moved

    @Override
    public void mousePressed(MouseEvent e) {    //handles all events when mouse is pressed
        int mx = e.getX();
        int my = e.getY();
        if (this.mouseOverBox(mx, my, 1100,50,50,50)) {
            if (this.backgroundAudio.musicPlaying()) {
                this.backgroundAudio.mute();
            }else {
                this.backgroundAudio.unmute();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {}   //handles all events when mouse is released

    public boolean mouseOverBox(int mx, int my, int x, int y, int width, int height) {  //calculates if the mouse is within the given box
        if (mx > x && mx < x + width) {
            return (my > y && my < y + height);
        } else {
            return false;
        }
    }

    //abstract methods
    public abstract void tick();    //physic calculations in this method
}

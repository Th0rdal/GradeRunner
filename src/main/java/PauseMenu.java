
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PauseMenu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Audio backgroundAudio;
    private BufferedImage imgUnmute, imgMute;
    private boolean mouseOverButton1 = false;
    private boolean mouseOverButton2 = false;
    private boolean mouseOverButtonMute = false;

    public PauseMenu(Game game, Handler handler, Audio audio){
        this.game = game;
        this.handler = handler;
        this.backgroundAudio = audio;
        this.loadSprites();
    }

    public void loadSprites() {
        SpriteSheet ss = new SpriteSheet(Game.loader.loadImage("/soundImg.png"));
        this.imgUnmute = ss.grabImage(0, 0, 50, 50);
        this.imgMute = ss.grabImage(1, 0, 50, 50);
    }

    public void pass(){}
    public void tick() {

    }

    public void render(Graphics g) {
        Font menu = new Font("arial", 1, 100);
        Font buttonFont = new Font("arial", 1, 70);
        Font buttonHighlightFont = new Font("arial", 1, 80);
        int tempX = 0;

        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Paused",450,150);

        if (this.mouseOverButton1) {
            g.setFont(buttonHighlightFont);
            tempX = 450;
            g.setColor(Color.gray);
        } else {
            g.setFont(buttonFont);
            tempX = 475;
            g.setColor(Color.black);
        }
        g.fillRect(375, 200, 500, 100);
        g.setColor(Color.white);
        g.drawString("Continue", tempX, 275);

        if (this.mouseOverButton2) {
            g.setFont(buttonHighlightFont);
            tempX = 425;
            g.setColor(Color.gray);
        } else {
            g.setFont(buttonFont);
            tempX = 450;
            g.setColor(Color.black);
        }
        g.fillRect(375, 350, 500, 100);
        g.setColor(Color.white);
        g.drawString("Main Menu", tempX, 425);

        if (this.backgroundAudio.musicPlaying()) {
            g.drawImage(this.imgUnmute, 1100, 50, null);
        }else {
            g.drawImage(this.imgMute, 1100, 50, null);

        }
    }

    public boolean mouseOverBox(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gamestate == Game.STATE.Pause) {

            if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
                game.gamestate = Game.STATE.Game;
                startGame();
            }

            if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
                game.setGamestate(Game.STATE.Menu);
            }

            if (mouseOverBox(mx, my, 1100,50,50,50)) {
                if (this.backgroundAudio.musicPlaying()) {
                    this.backgroundAudio.mute();
                }else {
                    this.backgroundAudio.unmute();
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        this.mouseOverButton1 = false;
        this.mouseOverButton2 = false;
        this.mouseOverButtonMute = false;


        if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
            this.mouseOverButton1 = true;
        } else if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
            this.mouseOverButton2 = true;
        }else if (mouseOverBox(mx, my, 1100,50,50,50)) {
            this.mouseOverButtonMute  = true;
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void startGame() {
    }
}

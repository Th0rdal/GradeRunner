import java.awt.*;
import java.awt.event.*;

public class PauseMenu extends BasicMenu{

    private boolean mouseOverButton1 = false;
    private boolean mouseOverButton2 = false;

    public PauseMenu(Game game, Handler handler, Audio audio){
        super(game, handler, audio);
        this.loadSprites();
    }

    public void loadSprites() {
        super.loadSprites();
    }
    public void tick() {

    }

    public void render(Graphics g) {
        Font menu = new Font("arial", Font.PLAIN, 100);
        Font buttonFont = new Font("arial", Font.PLAIN, 70);
        Font buttonHighlightFont = new Font("arial", Font.PLAIN, 80);
        int tempX;

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


    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gamestate == Game.STATE.Pause) {

            if (Utilities.mouseOverBox(mx, my, 375, 200, 500, 100)) {
                game.gamestate = Game.STATE.Game;
                startGame();
            }

            if (Utilities.mouseOverBox(mx, my, 375, 350, 500, 100)) {
                game.setGamestate(Game.STATE.Menu);
            }

            if (Utilities.mouseOverBox(mx, my, 1100,50,50,50)) {
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


        if (Utilities.mouseOverBox(mx, my, 375, 200, 500, 100)) {
            this.mouseOverButton1 = true;
        } else if (Utilities.mouseOverBox(mx, my, 375, 350, 500, 100)) {
            this.mouseOverButton2 = true;
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void startGame() {
    }
}

/**
 * This class handles all KeyInput Events
 * KeyPressed, KeyReleased
 * Go left      A
 * Go right     D
 * Jump         SPACE
 * Sprint       SHIFT
 * Pause game   P
 * Close Game   ESC
 * Show DevTools V
 */

import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private static boolean keydown[] = new boolean[3];  //tracks if the key is currently pressed 0 = A, 1 = D, 2 = SHIFT
    private Game game;
    private float vel = 5.0f;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        keydown[0] = false; //A down
        keydown[1] = false; //d down
        keydown[2] = false; //shift down
    }

    public void keyPressed(KeyEvent e) {    //this method is activated when a key is pressed
        int key = e.getKeyCode();   //gets the keyCode as an integer
        for (GameObject tempObject : handler.getObjectList()) {
            if(tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_A) {tempObject.setVelX(-1.0f * this.vel); keydown[0] = true;}    //sets velocity of player when A is pressed
                if (key == KeyEvent.VK_D) {tempObject.setVelX(this.vel); keydown[1] = true;}            //sets velocity of player when D is pressed
                if (key == KeyEvent.VK_SPACE) { //checks if a jump is allowed and sets velocity when true
                    if (((Player) tempObject).isOnPlatform()) {
                        tempObject.setVelY(15.0f);
                    }
                }
                if (key == KeyEvent.VK_SHIFT && !keydown[2]) {  //doubles velocity if shift is not already pressed
                    this.vel = this.vel*2;
                    tempObject.setVelX(tempObject.getVelX() * 2);
                    keydown[2] = true;
                }
            }
        }
        if (key == KeyEvent.VK_P) { //changes gamestate into state pause
            if (this.game.getGamestate() == Game.STATE.Game) {
                game.setGamestate(Game.STATE.Pause);
            }else if (this.game.getGamestate() == Game.STATE.Pause) {
                game.setGamestate(Game.STATE.Game);
            }
        }
        if (key == KeyEvent.VK_V) {
            DeveloperTools.toggleVisibility();
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);  //closes the game
    }

    public void keyReleased(KeyEvent e) {   //this method is activated when a key is released
        int key = e.getKeyCode();
        for (GameObject tempObject : handler.getObjectList()) {
            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_A) keydown[0] = false;
                if (key == KeyEvent.VK_D) keydown[1] = false;
                if (!keydown[0] && !keydown[1]) {   //if A and D aren't pressed set velocity to 0
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_SHIFT && keydown[2]) {   //halves velocity if shift is not pressed anymore
                    this.vel = this.vel/2;
                    tempObject.setVelX(tempObject.getVelX() / 2);
                    keydown[2] = false;
                }
            }
        }
    }
}

/**
 * This class handles all Settings.KeyInput Events
 * KeyPressed, KeyReleased
 * Go left      A
 * Go right     D
 * Jump         SPACE
 * Sprint       SHIFT
 * Pause game   P
 * Show DevTools V
 */

package Settings;

import Essentials.Game;
import Essentials.Handler;
import DevTools.DeveloperTools;
import GameObjects.GameObject;
import GameObjects.ID;
import GameObjects.Player;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private static final boolean[] keydown = new boolean[3];  //tracks if the key is currently pressed 0 = A, 1 = D, 2 = SHIFT
    private final Game game;

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
                if (key == KeyEvent.VK_A) {((Player)tempObject).move(Player.moveState.left); keydown[0] = true;}    //sets velocity of player when A is pressed
                if (key == KeyEvent.VK_D) {((Player)tempObject).move(Player.moveState.right); keydown[1] = true;}            //sets velocity of player when D is pressed
                if (key == KeyEvent.VK_SPACE) { //checks if a jump is allowed and sets velocity when true
                    if (handler.isOnPlatform(tempObject)) {
                        ((Player)tempObject).move(Player.moveState.jump);
                    }
                }
                if (key == KeyEvent.VK_SHIFT && !keydown[2]) {  //doubles velocity if shift is not already pressed
                    ((Player)tempObject).move(Player.moveState.sprint);
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
    }

    public void keyReleased(KeyEvent e) {   //this method is activated when a key is released
        int key = e.getKeyCode();
        for (GameObject tempObject : handler.getObjectList()) {
            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_A) keydown[0] = false;
                if (key == KeyEvent.VK_D) keydown[1] = false;
                if (!keydown[0] && !keydown[1]) {   //if A and D aren't pressed set velocity to 0
                    ((Player)tempObject).move(Player.moveState.stop);
                }
                if (key == KeyEvent.VK_SHIFT && keydown[2]) {   //halves velocity if shift is not pressed anymore
                    ((Player)tempObject).move(Player.moveState.sprintStop);
                    keydown[2] = false;
                }
            }
        }
    }
}

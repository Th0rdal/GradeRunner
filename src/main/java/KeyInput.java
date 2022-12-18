import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private static boolean keydown[] = new boolean[4];
    private Game game;
    private float vel = 5.0f;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        keydown[0] = false; //A down
        keydown[1] = false; //d down
        keydown[2] = false; //shift down
        keydown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject tempObject : handler.getObjectList()) {
            if(tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_A) {tempObject.setVelX(-1.0f * this.vel); keydown[0] = true;}
                if (key == KeyEvent.VK_D) {tempObject.setVelX(this.vel); keydown[1] = true;}
                if (key == KeyEvent.VK_SPACE) {
                    if (this.handler.isOnPlatform(tempObject)) {
                        tempObject.setVelY(15.0f);
                    }
                }
                if (key == KeyEvent.VK_SHIFT && !keydown[2]) {
                    this.vel = this.vel*2;
                    tempObject.setVelX(tempObject.getVelX() * 2);
                    keydown[2] = true;
                }
            }
        }
        if (key == KeyEvent.VK_P) {
            if (this.game.getGamestate() == Game.STATE.Game) {
                game.setGamestate(Game.STATE.Pause);
            }else if (this.game.getGamestate() == Game.STATE.Pause) {
                game.setGamestate(Game.STATE.Game);
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject tempObject : handler.getObjectList()) {
            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_A) keydown[0] = false;
                if (key == KeyEvent.VK_D) keydown[1] = false;
                if (!keydown[0] && !keydown[1]) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_SHIFT && keydown[2]) {
                    this.vel = this.vel/2;
                    tempObject.setVelX(tempObject.getVelX() / 2);
                    keydown[2] = false;
                }
            }
        }
    }
}

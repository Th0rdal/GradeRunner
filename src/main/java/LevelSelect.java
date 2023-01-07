
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class LevelSelect extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private Color color;

    public LevelSelect(Game game, Handler handler){
        this.game = game;
        this.handler = handler;

    }



    public void tick() {

    }
    public void pass(){}

    public void render(Graphics g) {
        Font menu = new Font("arial", 1, 100);
        Font buttons = new Font("arial", 1, 70);
        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Level Select",300,150);

        g.setColor(Color.black);
        g.fillRect(350, 700, 500, 100);
        g.setColor(Color.white);
        g.setFont(buttons);
        g.drawString("Main Menu", 425, 775);

        g.setColor(Color.black);
        g.drawRect(200, 200, 200, 120);
        g.drawRect(200, 350, 200, 120);
        g.drawRect(200, 500, 200, 120);
        g.drawRect(500, 200, 200, 120);
        g.drawRect(500, 350, 200, 120);
        g.drawRect(500, 500, 200, 120);
        g.drawRect(800, 200, 200, 120);
        g.drawRect(800, 350, 200, 120);
        g.drawRect(800, 500, 200, 120);



        //g.drawString("Play", 555, 275);
        //g.drawString("Level-Select", 425, 425);
        //g.drawString("Exit", 555, 575);
        //g.drawRect(400, 775, 200, 120);
        //g.setColor(Color.black);
        //g.fillRect(375, 700, 500, 100);

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

        if (game.gamestate == Game.STATE.Levelselect) {

//            if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
//                game.gamestate = Game.STATE.Game;
//                startGame();
//            }

            if (mouseOverBox(mx, my, 200, 200, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 200, 350, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 200, 500, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 500, 200, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 500, 350, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 500, 500, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 800, 200, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 800, 350, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 800, 500, 200, 120)) {pass();}

            if (mouseOverBox(mx, my, 350, 700, 500, 100)) {
                //game.gamestate = Game.STATE.Menu;
                game.setGamestate(Game.STATE.Menu);
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void startGame() {
        //creating player
//    handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32 , ID.Player, handler));
//    try{ handler.ClearEnemies();
//    } catch(NullPointerException d) {
//      System.out.println("The d hit again");
//    }
        //creating enemy
        //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-200), r.nextInt(Game.HEIGHT-300)+60, ID.BasicEnemy, handler, Color.red));
    }
}

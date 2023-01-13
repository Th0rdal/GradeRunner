import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends MouseAdapter{


  private Game game;
  private Handler handler;
  private Random r = new Random();
  private Color color;

  private boolean mouseOverButton1 = false;
  private boolean mouseOverButton2 = false;
  private boolean mouseOverButton3 = false;


  public Menu(Game game, Handler handler){
    this.game = game;
    this.handler = handler;

  }


  public void tick() {

  }

  public void render(Graphics g) {
    Font menu = new Font("arial", 1, 100);
    Font buttons = new Font("arial", 1, 70);
    Font buttonsAlt = new Font("arial", 1, 80);
    Font buttons2 = new Font("arial", 1, 70);
    Font buttons2Alt = new Font("arial", 1, 80);
    Font buttons3 = new Font("arial", 1, 70);
    Font buttons3Alt = new Font("arial", 1, 80);


    g.setFont(menu);
    g.setColor(Color.black);
    g.drawString("Grade Runner",280,150);

    if (this.mouseOverButton1) {
      g.setColor(Color.gray);
    } else {
      g.setColor(Color.black);
    }
    g.fillRect(375, 200, 500, 100);
    if (this.mouseOverButton2) {
      g.setColor(Color.gray);
    } else {
      g.setColor(Color.black);
    }
    g.fillRect(375, 350, 500, 100);
    if (this.mouseOverButton3) {
      g.setColor(Color.gray);
    } else {
      g.setColor(Color.black);
    }
    g.fillRect(375, 500, 500, 100);
    //g.drawRect(375, 700, 500, 100);

    g.setColor(Color.white);


    if (this.mouseOverButton1) {
      g.setFont(buttonsAlt);
      g.drawString("Play", 545, 275);
    } else {
      g.setFont(buttons);
      g.drawString("Play", 555, 275);
    }


    if (this.mouseOverButton2) {
      g.setFont(buttons2Alt);
      g.drawString("Level-Select", 390, 425);
    } else {
      g.setFont(buttons2);
      g.drawString("Level-Select", 425, 425);
    }

    if (this.mouseOverButton3) {
      g.setFont(buttons3Alt);
      g.drawString("Exit", 545, 575);
    } else {
      g.setFont(buttons3);
      g.drawString("Exit", 555, 575);
    }

    //g.drawString("Quit", 555, 775);

  }

  public boolean mouseOverBox(int mx, int my, int x, int y, int width, int height) {
    if (mx > x && mx < x + width) {
      if (my > y && my < y + height) {
        return true;
      } else return false;
    } else return false;
  }

  public void mouseMoved(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    this.mouseOverButton1 = false;
    this.mouseOverButton2 = false;
    this.mouseOverButton3 = false;

    if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
      this.mouseOverButton1 = true;
    } else if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
      this.mouseOverButton2 = true;
    } else if (mouseOverBox(mx, my, 375, 500, 500, 100)) {
      this.mouseOverButton3 = true;
    }
  }

  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();


    if (game.gamestate == Game.STATE.Menu) {

      if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
        game.gamestate = Game.STATE.Game;
        startGame();
      }

      if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
        //game.gamestate = Game.STATE.Levelselect;
        game.setGamestate(Game.STATE.Levelselect);
      }

//      if (mouseOverBox(mx, my, 375, 500, 500, 100)) {
//        game.gamestate = Game.STATE.Options;
//      }

      if (mouseOverBox(mx, my, 375, 500, 500, 100)) {
        System.exit(1);
      }
    }
  }


  public void mouseReleased(MouseEvent e){

  }

  public void startGame() {
  }
}

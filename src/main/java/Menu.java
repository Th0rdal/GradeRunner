import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class Menu extends BasicMenu{

  private boolean mouseOverButton1 = false;
  private boolean mouseOverButton2 = false;
  private boolean mouseOverButton3 = false;


  public Menu(Game game, Handler handler, Audio backgroundAudio){
    super(game, handler, backgroundAudio);
    this.loadSprites();
  }

  public void loadSprites() {
    super.loadSprites();
    /*SpriteSheet ss = new SpriteSheet(Game.loader.loadImage("/soundImg.png"));
    this.imgUnmute = ss.grabImage(0, 0, 50, 50);
    this.imgMute = ss.grabImage(1, 0, 50, 50);*/
  }
  public void pass(){}

  public void tick(){}

  public void render(Graphics g) {
    super.render(g);

    Font menu = new Font("arial", 1, 100);
    Font buttonFont = new Font("arial", 1, 70);
    Font buttonHighlightedFont = new Font("arial", 1, 80);
    int tempX = 0;

    g.setFont(menu);
    g.setColor(Color.black);
    g.drawString("Grade Runner",280,150);

    if (this.mouseOverButton1) {
      g.setFont(buttonHighlightedFont);
      tempX = 545;
      g.setColor(Color.gray);
    } else {
      g.setFont(buttonFont);
      tempX = 555;
      g.setColor(Color.black);
    }
    g.fillRect(375, 200, 500, 100);
    g.setColor(Color.white);
    g.drawString("Play", tempX, 275);

    if (this.mouseOverButton2) {
      g.setFont(buttonHighlightedFont);
      tempX = 390;
      g.setColor(Color.gray);
    } else {
      g.setFont(buttonFont);
      tempX = 425;
      g.setColor(Color.black);
    }
    g.fillRect(375, 350, 500, 100);
    g.setColor(Color.white);
    g.drawString("Level-Select", tempX, 425);

    if (this.mouseOverButton3) {
      g.setFont(buttonHighlightedFont);
      tempX = 545;
      g.setColor(Color.gray);
    } else {
      g.setFont(buttonFont);
      tempX = 555;
      g.setColor(Color.black);
    }
    g.fillRect(375, 500, 500, 100);
    g.setColor(Color.white);
    g.drawString("Exit", tempX, 575);

  }

  public void mouseMoved(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    this.mouseOverButton1 = false;
    this.mouseOverButton2 = false;
    this.mouseOverButton3 = false;

    if (Utilities.mouseOverBox(mx, my, 375, 200, 500, 100)) {
      this.mouseOverButton1 = true;
    } else if (Utilities.mouseOverBox(mx, my, 375, 350, 500, 100)) {
      this.mouseOverButton2 = true;
    } else if (Utilities.mouseOverBox(mx, my, 375, 500, 500, 100)) {
      this.mouseOverButton3 = true;
    }
  }

  public void mousePressed(MouseEvent e) {

    if (this.game.gamestate == Game.STATE.Menu) {
      super.mousePressed(e);
      int mx = e.getX();
      int my = e.getY();
      if (Utilities.mouseOverBox(mx, my, 375, 200, 500, 100)) {
        game.setGamestate(Game.STATE.Game);
        this.game.startGame();
      }else if (Utilities.mouseOverBox(mx, my, 375, 350, 500, 100)) {
        game.setGamestate(Game.STATE.Levelselect);
      }else if (Utilities.mouseOverBox(mx, my, 375, 500, 500, 100)) {
        System.exit(1);
      }
    }
  }

  public void mouseReleased(MouseEvent e){

  }
}

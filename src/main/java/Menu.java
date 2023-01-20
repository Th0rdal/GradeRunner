import java.awt.*;
import java.awt.event.*;

public class Menu extends BasicMenu{

  private boolean mouseOverPlayButton = false;
  private boolean mouseOverLevelSelect = false;
  private boolean mouseOverExitButton = false;


  public Menu(Game game, Handler handler, Audio backgroundAudio){
    super(game, handler, backgroundAudio);
    this.loadSprites();
  }

  public void loadSprites() {
    super.loadSprites();
  }

  public void tick(){}

  public void render(Graphics g) {
    super.render(g);

    Font titleFont = new Font("arial", Font.PLAIN, 100);
    Font buttonFont = new Font("arial", Font.PLAIN, 70);
    Font buttonHighlightedFont = new Font("arial", Font.PLAIN, 80);
    int tempX;

    g.setFont(titleFont);
    g.setColor(Color.black);
    g.drawString("Grade Runner",280,150);

    if (this.mouseOverPlayButton) {
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

    if (this.mouseOverLevelSelect) {
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

    if (this.mouseOverExitButton) {
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

    this.mouseOverPlayButton = false;
    this.mouseOverLevelSelect = false;
    this.mouseOverExitButton = false;

    if (Utilities.mouseOverBox(mx, my, 375, 200, 500, 100)) {
      this.mouseOverPlayButton = true;
    } else if (Utilities.mouseOverBox(mx, my, 375, 350, 500, 100)) {
      this.mouseOverLevelSelect = true;
    } else if (Utilities.mouseOverBox(mx, my, 375, 500, 500, 100)) {
      this.mouseOverExitButton = true;
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
        System.exit(0);
      }
    }
  }

  public void mouseReleased(MouseEvent e){

  }
}

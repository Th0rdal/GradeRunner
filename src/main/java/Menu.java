import java.awt.*;
import java.awt.event.*;

public class Menu extends BasicMenu{

  private final Button Button_PlayButton = new Button(375, 200, 500, 100, "Play", true);
  private final Button Button_LevelSelect = new Button(375, 350, 500, 100, "LevelSelect", true);
  private final Button Button_Exit = new Button(375, 500, 500, 100, "Exit", true);


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
    g.setFont(titleFont);
    g.setColor(Color.black);
    g.drawString("Grade Runner",(Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Grade Runner") / 2) ,150);

    this.Button_PlayButton.render(g);
    this.Button_LevelSelect.render(g);
    this.Button_Exit.render(g);

  }

  public void mouseMoved(MouseEvent e) {

    if (game.getGamestate() != Game.STATE.Menu) {
      return;
    }

    int mx = e.getX();
    int my = e.getY();
    super.mouseMoved(e);

    this.Button_PlayButton.changeHighlight(Utilities.mouseOverBox(mx, my, 375, 200, 500, 100));
    this.Button_LevelSelect.changeHighlight(Utilities.mouseOverBox(mx, my, 375, 350, 500, 100));
    this.Button_Exit.changeHighlight(Utilities.mouseOverBox(mx, my, 375, 500, 500, 100));
  }

  public void mousePressed(MouseEvent e) {

    if (this.game.gamestate != Game.STATE.Menu) {
      return;
    }
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

  public void mouseReleased(MouseEvent e){

  }
}

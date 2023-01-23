/**
 * Handles mouse events, sprite loading, game start and render of the menu
 */

package Screens;

import Essentials.Game;
import Essentials.Handler;
import Settings.Audio;

import java.awt.*;
import java.awt.event.MouseEvent;


public class Menu extends BasicMenu {

  //buttons
  private final Screens.Button Button_PlayButton = new Screens.Button(375, 200, 500, 100, "Play", true);
  private final Screens.Button Button_LevelSelect = new Screens.Button(375, 350, 500, 100, "LevelSelect", true);
  private final Screens.Button Button_Exit = new Screens.Button(375, 500, 500, 100, "Exit", true);


  public Menu(Game game, Handler handler, Audio backgroundAudio){
    super(game, handler, backgroundAudio);
    this.loadSprites();
  }

  public void tick(){}  //physic calculations in this method

  public void render(Graphics g) {  //method for all graphic calculations
    super.render(g);

    Font titleFont = new Font("arial", Font.PLAIN, 100);
    g.setFont(titleFont);
    g.setColor(Color.black);
    g.drawString("Grade Runner",(Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Grade Runner") / 2) ,150);

    this.Button_PlayButton.render(g);
    this.Button_LevelSelect.render(g);
    this.Button_Exit.render(g);

  }

  public void loadSprites() { //loading of the sprites the object needs
    super.loadSprites();
  }

  //mouse events
  public void mouseMoved(MouseEvent e) {  //handles all events when mouse is moved

    if (game.getGamestate() != Game.STATE.Menu) {
      return;
    }

    super.mouseMoved(e);
    int mx = e.getX();
    int my = e.getY();

    this.Button_PlayButton.toggleHighlighted(this.mouseOverBox(mx, my, 375, 200, 500, 100));
    this.Button_LevelSelect.toggleHighlighted(this.mouseOverBox(mx, my, 375, 350, 500, 100));
    this.Button_Exit.toggleHighlighted(this.mouseOverBox(mx, my, 375, 500, 500, 100));
  }

  public void mousePressed(MouseEvent e) {  //handles all events when mouse is pressed

    if (this.game.gamestate != Game.STATE.Menu) {
      return;
    }
    super.mousePressed(e);
    int mx = e.getX();
    int my = e.getY();

    if (this.mouseOverBox(mx, my, 375, 200, 500, 100)) {
      this.game.startGame();
    }else if (this.mouseOverBox(mx, my, 375, 350, 500, 100)) {
      game.setGamestate(Game.STATE.Levelselect);
    }else if (this.mouseOverBox(mx, my, 375, 500, 500, 100)) {
      System.exit(0);
    }
  }

  public void mouseReleased(MouseEvent e){}  //handles all events when mouse is released

}

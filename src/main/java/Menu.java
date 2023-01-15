import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class Menu extends MouseAdapter{


  private Game game;
  private Handler handler;
  private Audio backgroundAudio;
  private boolean mouseOverButton1 = false;
  private boolean mouseOverButton2 = false;
  private boolean mouseOverButton3 = false;
  private boolean mouseOverButtonMute = false;
  private BufferedImage imgUnmute, imgMute;


  public Menu(Game game, Handler handler, Audio backgroundAudio){
    this.game = game;
    this.handler = handler;
    this.backgroundAudio = backgroundAudio;
    this.loadSprites();
  }

  public void loadSprites() {
    SpriteSheet ss = new SpriteSheet(Game.loader.loadImage("/soundImg.png"));
    this.imgUnmute = ss.grabImage(0, 0, 50, 50);
    this.imgMute = ss.grabImage(1, 0, 50, 50);
  }
  public void pass(){}

  public void tick(){}

  public void render(Graphics g) {
    Font menu = new Font("arial", 1, 100);
    Font button = new Font("arial", 1, 70);
    Font buttonAlt = new Font("arial", 1, 80);
    Font button2 = new Font("arial", 1, 70);
    Font button2Alt = new Font("arial", 1, 80);
    Font button3 = new Font("arial", 1, 70);
    Font button3Alt = new Font("arial", 1, 80);
    Font muteButton = new Font("arial", 1, 30);
    Font muteButtonAlt = new Font("arial", 1, 40);


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
    if (this.mouseOverButtonMute) {
      g.setColor(Color.gray);
    } else {
      g.setColor(Color.black);
    }
    //g.fillOval(1100,50,50,50);
    if (this.backgroundAudio.musicPlaying()) {
      g.drawImage(this.imgUnmute, 1100, 50, null);
    }else {
      g.drawImage(this.imgMute, 1100, 50, null);

    }

    g.setColor(Color.white);

    if (this.mouseOverButton1) {
      g.setFont(buttonAlt);
      g.drawString("Play", 545, 275);
    } else {
      g.setFont(button);
      g.drawString("Play", 555, 275);
    }

    if (this.mouseOverButton2) {
      g.setFont(button2Alt);
      g.drawString("Level-Select", 390, 425);
    } else {
      g.setFont(button2);
      g.drawString("Level-Select", 425, 425);
    }

    if (this.mouseOverButton3) {
      g.setFont(button3Alt);
      g.drawString("Exit", 545, 575);
    } else {
      g.setFont(button3);
      g.drawString("Exit", 555, 575);
    }

    /*if (this.mouseOverButtonMute) {
      g.setFont(muteButtonAlt);
      g.drawString("M", 1109, 90);
    } else {
      g.setFont(muteButton);
      g.drawString("M", 1112, 85);
    }*/

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
    this.mouseOverButtonMute = false;

    if (mouseOverBox(mx, my, 375, 200, 500, 100)) {
      this.mouseOverButton1 = true;
    } else if (mouseOverBox(mx, my, 375, 350, 500, 100)) {
      this.mouseOverButton2 = true;
    } else if (mouseOverBox(mx, my, 375, 500, 500, 100)) {
      this.mouseOverButton3 = true;
    }else if (mouseOverBox(mx, my, 1100,50,50,50)) {
      this.mouseOverButtonMute  = true;
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
      if (mouseOverBox(mx, my, 1100,50,50,50)) {
        if (this.backgroundAudio.musicPlaying()) {
          this.backgroundAudio.mute();
        }else {
          this.backgroundAudio.unmute();
        }
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

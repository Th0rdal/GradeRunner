
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class LevelSelect extends BasicMenu{

    private Random r = new Random();
    public static int selectedLevel = 0;
    private File fileList[];
    private Level[] levelList;

    private boolean mouseOverButton1 = false;
    private boolean mouseOverButton2 = false;
    private boolean mouseOverButton3 = false;
    private boolean mouseOverButton4 = false;
    private boolean mouseOverButton5 = false;
    private boolean mouseOverButton6 = false;
    private boolean mouseOverButton7 = false;
    private boolean mouseOverButton8 = false;
    private boolean mouseOverButton9 = false;
    private boolean mouseOverButton10 = false;


    public LevelSelect(Game game, Handler handler, Audio audio){
        super(game, handler, audio);
        //this.loadLevels();
        LevelSelect.selectedLevel = 1;
        this.loadSprites();
    }

    public int getSelectedLevel()
    {
        return selectedLevel;
    }

    public void tick() {}

    public void loadSprites() {
        super.loadSprites();
    }
    public void pass(){}

    public void render(Graphics g) {
        super.render(g);

        Font menu = new Font("arial", 1, 100);
        Font buttons = new Font("arial", 1, 70);
        Font button1 = new Font("arial", 1, 70);
        Font button1Alt = new Font("arial", 1, 80);
        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Level Select",300,150);
        String tempString[] = new String[9];
        for (int i = 0; i < tempString.length; i++) {
            tempString[i] = Integer.toString(i+1);
        }


        if (this.mouseOverButton10) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.fillRect(350, 700, 500, 100);
        g.setColor(Color.white);
        if (this.mouseOverButton10) {
            g.setFont(button1Alt);
            g.drawString("Main Menu", 400, 775);
        } else {
            g.setFont(button1);
            g.drawString("Main Menu", 425, 775);
        }

        g.setFont(buttons);

        if (this.mouseOverButton1) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(200, 200, 200, 120);
        g.drawString(tempString[0], 280, 285);

        if (this.mouseOverButton2) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(500, 200, 200, 120);
        g.drawString(tempString[1], 580, 285);

        if (this.mouseOverButton3) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(800, 200, 200, 120);
        g.drawString(tempString[2], 880, 285);

        if (this.mouseOverButton4) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(200, 350, 200, 120);
        g.drawString(tempString[3], 280, 435);

        if (this.mouseOverButton5) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(500, 350, 200, 120);
        g.drawString(tempString[4], 580, 435);

        if (this.mouseOverButton6) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(800, 350, 200, 120);
        g.drawString(tempString[5], 880, 435);

        if (this.mouseOverButton7) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(200, 500, 200, 120);
        g.drawString(tempString[6], 280, 585);

        if (this.mouseOverButton8) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(500, 500, 200, 120);
        g.drawString(tempString[7], 580, 585);

        if (this.mouseOverButton9) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(800, 500, 200, 120);
        g.drawString(tempString[8], 880, 585);

        if(selectedLevel == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 200, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[0], 280, 285);
        }
        else if(selectedLevel == 2)
        {
            g.setColor(Color.BLACK);
            g.fillRect(500, 200, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[1], 580, 285);
        }
        else if(selectedLevel == 3)
        {
            g.setColor(Color.BLACK);
            g.fillRect(800, 200, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[2], 880, 285);
        }
        else if(selectedLevel == 4)
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 350, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[3], 280, 435);
        }
        else if(selectedLevel == 5)
        {
            g.setColor(Color.BLACK);
            g.fillRect(500, 350, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[4], 580, 435);
        }
        else if(selectedLevel == 6)
        {
            g.setColor(Color.BLACK);
            g.fillRect(800, 350, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[5], 880, 435);
        }
        else if(selectedLevel == 7)
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 500, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[6], 280, 585);
        }
        else if(selectedLevel == 8)
        {
            g.setColor(Color.BLACK);
            g.fillRect(500, 500, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[7], 580, 585);
        }
        else if(selectedLevel == 9)
        {
            g.setColor(Color.BLACK);
            g.fillRect(800, 500, 200, 120);
            g.setColor(Color.WHITE);
            g.drawString(tempString[8], 880, 585);
        }

    }

    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        this.mouseOverButton1 = false;
        this.mouseOverButton2 = false;
        this.mouseOverButton3 = false;
        this.mouseOverButton4 = false;
        this.mouseOverButton5 = false;
        this.mouseOverButton6 = false;
        this.mouseOverButton7 = false;
        this.mouseOverButton8 = false;
        this.mouseOverButton9 = false;
        this.mouseOverButton10 = false;

        if (Utilities.mouseOverBox(mx, my, 350, 700, 500, 100)) {
            this.mouseOverButton10 = true;
        } else if (Utilities.mouseOverBox(mx, my, 200, 200, 200, 120)) {
            this.mouseOverButton1 = true;
        } else if (Utilities.mouseOverBox(mx, my, 500, 200, 200, 120)) {
            this.mouseOverButton2 = true;
        }else if (Utilities.mouseOverBox(mx, my, 800, 200, 200, 120)) {
            this.mouseOverButton3 = true;
        } else if (Utilities.mouseOverBox(mx, my, 200, 350, 200, 120)) {
            this.mouseOverButton4 = true;
        }else if (Utilities.mouseOverBox(mx, my, 500, 350, 200, 120)) {
            this.mouseOverButton5 = true;
        } else if (Utilities.mouseOverBox(mx, my, 800, 350, 200, 120)) {
            this.mouseOverButton6 = true;
        }else if (Utilities.mouseOverBox(mx, my, 200, 500, 200, 120)) {
            this.mouseOverButton7 = true;
        } else if (Utilities.mouseOverBox(mx, my, 500, 500, 200, 120)) {
            this.mouseOverButton8 = true;
        }else if (Utilities.mouseOverBox(mx, my, 800, 500, 200, 120)) {
            this.mouseOverButton9 = true;
        }
    }

    public void mousePressed(MouseEvent e) {

        if (game.gamestate == Game.STATE.Levelselect) {
            super.mousePressed(e);
            int mx = e.getX();
            int my = e.getY();
            if (Utilities.mouseOverBox(mx, my, 200, 200, 200, 120)) {
                selectedLevel = 1;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 200, 350, 200, 120)) {
                selectedLevel = 4;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 200, 500, 200, 120)) {
                selectedLevel = 7;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 500, 200, 200, 120)) {
                selectedLevel = 2;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 500, 350, 200, 120)) {
                selectedLevel = 5;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 500, 500, 200, 120)) {
                selectedLevel = 8;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 800, 200, 200, 120)) {
                selectedLevel = 3;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 800, 350, 200, 120)) {
                selectedLevel = 6;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 800, 500, 200, 120)) {
                selectedLevel = 9;
                pass();
            }else if (Utilities.mouseOverBox(mx, my, 350, 700, 500, 100)) {
                game.setGamestate(Game.STATE.Menu);
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void loadLevels() {
        this.fileList = Utilities.loadFileContent("saves/worlds");
        this.levelList = new Level[this.fileList.length];
        for (int i = 0; i < this.levelList.length; i++) {
            this.levelList[i] = (Level) Utilities.loadObjectFromFile("saves/worlds/" + this.fileList[i].getName());
        }
    }

    public Level[] getLevelList() {
        return this.levelList;
    }

}

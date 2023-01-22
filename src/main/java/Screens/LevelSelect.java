package Screens;

import Essentials.Game;
import Essentials.Handler;
import Saves.Level;
import Settings.Audio;
import Utilities.FileHandler;

import java.io.File;
import java.awt.*;
import java.awt.event.*;

public class LevelSelect extends BasicMenu {

    private int selectedLevel;
    private Screens.Button selectedButton;
    private int LevelCounter = 0;
    private Level[] levelList;
    private final Screens.Button Button_MainMenu = new Screens.Button(350, 700, 500, 100, "Main Menu", true);
    private final Screens.Button Button_Level1 = new Screens.Button(225, 200, 200, 120, false);
    private final Screens.Button Button_Level2 = new Screens.Button(525, 200, 200, 120, false);
    private final Screens.Button Button_Level3 = new Screens.Button(825, 200, 200, 120, false);
    private final Screens.Button Button_Level4 = new Screens.Button(225, 350, 200, 120, false);
    private final Screens.Button Button_Level5 = new Screens.Button(525, 350, 200, 120, false);
    private final Screens.Button Button_Level6 = new Screens.Button(825, 350, 200, 120, false);
    private final Screens.Button Button_Level7 = new Screens.Button(225, 500, 200, 120, false);
    private final Screens.Button Button_Level8 = new Screens.Button(525, 500, 200, 120, false);
    private final Screens.Button Button_Level9 = new Screens.Button(825, 500, 200, 120, false);
    private final Screens.Button Button_next = new Screens.Button(1052, 360, 160, 100, "next", true);
    private final Screens.Button Button_prev = new Screens.Button(27, 360, 160, 100, "prev", true);
    private final Screens.Button[] levelButtonList = new Screens.Button[9];

    public LevelSelect(Game game, Handler handler, Audio audio){
        super(game, handler, audio);
        this.loadSprites();
        this.levelButtonList[0] = this.Button_Level1;
        this.levelButtonList[1] = this.Button_Level2;
        this.levelButtonList[2] = this.Button_Level3;
        this.levelButtonList[3] = this.Button_Level4;
        this.levelButtonList[4] = this.Button_Level5;
        this.levelButtonList[5] = this.Button_Level6;
        this.levelButtonList[6] = this.Button_Level7;
        this.levelButtonList[7] = this.Button_Level8;
        this.levelButtonList[8] = this.Button_Level9;
        this.loadLevels();
        this.selectedLevel = 1;
        this.selectedButton = this.Button_Level1;
        this.selectedButton.changeSelected();

    }

    public int getSelectedLevel()
    {
        return this.selectedLevel;
    }

    public void tick() {}

    public void loadSprites() {
        super.loadSprites();
    }

    public void render(Graphics g) {
        super.render(g);

        Font menu = new Font("arial", Font.PLAIN, 100);
        g.setFont(menu);
        g.setColor(Color.black);
        g.drawString("Level Select",300,150);

        this.Button_MainMenu.render(g);
        this.Button_next.render(g);
        this.Button_prev.render(g);
        this.Button_Level1.render(g);
        this.Button_Level2.render(g);
        this.Button_Level3.render(g);
        this.Button_Level4.render(g);
        this.Button_Level5.render(g);
        this.Button_Level6.render(g);
        this.Button_Level7.render(g);
        this.Button_Level8.render(g);
        this.Button_Level9.render(g);

    }

    public void mouseMoved(MouseEvent e) {

        if (game.getGamestate() != Game.STATE.Levelselect) {
            return;
        }

        int mx = e.getX();
        int my = e.getY();
        super.mouseMoved(e);
        this.Button_MainMenu.changeHighlight(this.mouseOverBox(mx, my, 350, 700, 500, 100));
        this.Button_next.changeHighlight(this.mouseOverBox(mx, my, 1062, 360, 150, 100));
        this.Button_prev.changeHighlight(this.mouseOverBox(mx, my, 37, 360, 150, 100));
        this.Button_Level1.changeHighlight(this.mouseOverBox(mx, my, 200, 200, 200, 120));
        this.Button_Level2.changeHighlight(this.mouseOverBox(mx, my, 500, 200, 200, 120));
        this.Button_Level3.changeHighlight(this.mouseOverBox(mx, my, 800, 200, 200, 120));
        this.Button_Level4.changeHighlight(this.mouseOverBox(mx, my, 200, 350, 200, 120));
        this.Button_Level5.changeHighlight(this.mouseOverBox(mx, my, 500, 350, 200, 120));
        this.Button_Level6.changeHighlight(this.mouseOverBox(mx, my, 800, 350, 200, 120));
        this.Button_Level7.changeHighlight(this.mouseOverBox(mx, my, 200, 500, 200, 120));
        this.Button_Level8.changeHighlight(this.mouseOverBox(mx, my, 500, 500, 200, 120));
        this.Button_Level9.changeHighlight(this.mouseOverBox(mx, my, 800, 500, 200, 120));
    }

    public void changeLevelSelection(int buttonIndex) {
        if (this.selectedLevel == buttonIndex) {
            return;
        }else if (buttonIndex > this.levelList.length) {
            game.getWindow().warning("There is no Level in this save slot!");
            return;
        }
        this.levelButtonList[buttonIndex-1].changeSelected();
        this.selectedButton.changeSelected();
        this.selectedLevel = buttonIndex;
        this.selectedButton = this.levelButtonList[buttonIndex-1];

    }
    public void mousePressed(MouseEvent e) {

        if (game.gamestate != Game.STATE.Levelselect) {
            return;
        }
        super.mousePressed(e);
        int mx = e.getX();
        int my = e.getY();
        if (this.mouseOverBox(mx, my, 200, 200, 200, 120)) {
            this.changeLevelSelection(1);
        }else if (this.mouseOverBox(mx, my, 200, 350, 200, 120)) {
            this.changeLevelSelection(4);
        }else if (this.mouseOverBox(mx, my, 200, 500, 200, 120)) {
            this.changeLevelSelection(7);
        }else if (this.mouseOverBox(mx, my, 500, 200, 200, 120)) {
            this.changeLevelSelection(2);
        }else if (this.mouseOverBox(mx, my, 500, 350, 200, 120)) {
            this.changeLevelSelection(5);
        }else if (this.mouseOverBox(mx, my, 500, 500, 200, 120)) {
            this.changeLevelSelection(8);
        }else if (this.mouseOverBox(mx, my, 800, 200, 200, 120)) {
            this.changeLevelSelection(3);
        }else if (this.mouseOverBox(mx, my, 800, 350, 200, 120)) {
            this.changeLevelSelection(6);
        }else if (this.mouseOverBox(mx, my, 800, 500, 200, 120)) {
            this.changeLevelSelection(9);
        }else if (this.mouseOverBox(mx, my, 350, 700, 500, 100)) {
            if (this.selectedLevel > this.levelList.length) {
                game.getWindow().error("Level selection error! A non-existent Level was selected!");
            }else {
                game.setGamestate(Game.STATE.Menu);
            }
        }else if (this.mouseOverBox(mx, my, 1062, 360, 150, 100)) {
            this.LevelCounter++;
            this.writeToLevelButton();
        }else if (this.mouseOverBox(mx, my, 37, 360, 150, 100)) {
            if (this.LevelCounter > 0){
                this.LevelCounter--;
                this.writeToLevelButton();
            }
        }
    }

    public void mouseReleased(MouseEvent e){}

    public void loadLevels() {
        File[] fileList = FileHandler.loadFileContent("saves/worlds");
        this.levelList = new Level[fileList.length];
        for (int i = 0; i < this.levelList.length; i++) {
            this.levelList[i] = (Level) FileHandler.loadObjectFromFile("saves/worlds/" + fileList[i].getName());
        }
        writeToLevelButton();
    }

    public void writeToLevelButton() {
        for (int i = 0; i < 9; i++) {
            try {
                this.levelButtonList[i].reset();
                this.levelButtonList[i].setText(this.levelList[9 * this.LevelCounter + i].getName());
            }catch (IndexOutOfBoundsException | NullPointerException e) {
                this.levelButtonList[i].setText(Integer.toString(9 * this.LevelCounter + i + 1));
            }
        }
    }
    public Level[] getLevelList() {
        return this.levelList;
    }
}

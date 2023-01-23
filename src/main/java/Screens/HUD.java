/**
 * Handles the score of the player, the time left for level and render of HUD
 */

package Screens;

import Essentials.Game;

import java.awt.*;

public class HUD {
    private final Game game;
    private int time = 0;   //time for level completion left
    private int startTime = 0;  //total time for level completion
    private long score = 0; //score of the player
    private char grade = 'F';   //grade of the player
    private final Font textFont = new Font("arial", Font.PLAIN, 30);

    public HUD(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {    //method for all graphic calculations
        g.setFont(this.textFont);
        g.drawString("Time: " + this.time, 800,32);
        g.drawString("Score: " + String.format("%010d", this.score), 950, 32);
    }

    public void finish() {  //adds time to score and calculates grade
        this.score += this.time * 1000L;
        for (int i = 1; i < 6; i++) {
            if (this.time > (this.startTime/6) * i) {
                this.grade -= 1;
            }
        }
    }
    public void addToScore(long amount) {
        this.score += amount;
    }
    public void subtractSecond() {
        if (this.time == 0) {
            game.setGamestate(Game.STATE.DeathScreen);
        }
        this.time -= 1;
    }
    public void setupHUD(int time){ //sets the time and score of the level up for the HUD
        this.time = time;
        this.startTime = time;
        this.score = 0;
    }

    //getters
    public int getTime() {
        return this.time;
    }
    public long getScore() {
        return this.score;
    }
    public char getGrade() {
        return this.grade;
    }
}

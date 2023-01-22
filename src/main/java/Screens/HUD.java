package Screens;

import Essentials.Game;

import java.awt.*;

public class HUD {
    private int time = 0;
    private int startTime = 0;
    private long score = 0;
    private char grade = 'F';
    private final Font textFont = new Font("arial", Font.PLAIN, 30);

    public void render(Graphics g) {
        g.setFont(this.textFont);
        g.drawString("Time: " + this.time, 800,32);
        g.drawString("Score: " + String.format("%010d", this.score), 950, 32);
    }
    public void addToScore(long amount) {
        this.score += amount;
    }
    public void addSecond() {
        this.time -= 1;
    }
    public void setupHUD(int time){
        this.time = time;
        this.startTime = time;
        this.score = 0;
    }
    public void finish() {
        this.score += this.time * 1000;
        for (int i = 1; i < 6; i++) {
            if (this.time > (this.startTime/6) * i) {
                this.grade -= 1;
            }
        }
    }
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

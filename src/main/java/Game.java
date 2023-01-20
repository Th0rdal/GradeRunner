/**
 * Main class Game. This class handles the game-loop and all important variables for the game.
 *
 */

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    //variables used for the Window
    public static final int WIDTH = 1250, HEIGHT = WIDTH / 12 * 9;  //width and height of the window

    //variables used to control the scrolling
    public static final int TOTALLENGTH = 1500;
    public static final float scrollWidthLeft = 200.0f; //width at which the screen starts to scroll to the left
    public static final float scrollWidthRight = 1050.0f; //width at which the screen starts to scroll right
    public static boolean canScrollLeft = false, canScrollRight = true, scroll = false; //check variables if scrolling is allowed

    //variables used for the game-loop
    public static final int MAX_FRAMES_PER_SECOND = 30;
    public static final int MAX_TICKS_PER_SECOND = 30;
    public static final int OPTIMAL_TIME_FRAMES = 1000000000 / Game.MAX_FRAMES_PER_SECOND;  //this is the optimal time a single render calculates
    public static final int OPTIMAL_TIME_TICK = 1000000000 / Game.MAX_TICKS_PER_SECOND; //this is the optimal time a single tick calculates
    boolean running = false;


    //class variable declaration
    private Thread thread;
    private final Handler handler;
    private final Menu menu;
    private final LevelSelect levelSelect;
    private final PauseMenu pause;
    private final VictoryScreen victoryScreen;
    private final DeathScreen deathScreen;
    private final DeveloperTools developerTools;
    private final WindowX window;
    private final BufferedImage background;


    //variables used for gamestate
    public enum STATE { //saves the current state of the game
        Menu,   //this state is active when in the menu
        Game,   //this state is active when the game is running
        Pause,   //this state is active when the game is paused
        DeathScreen,
        VictoryScreen,
        Levelselect
    }
    public STATE gamestate = STATE.Menu; //variable holding the current gamestate

    public Game() { //default constructor

        //load sprites
        background = Utilities.loadImage("src/main/resources/bg.jpg");

        //audio initialization
        Audio backgroundAudio = new Audio("src/main/resources/backgroundMusic.wav");
        backgroundAudio.mute();

        //create all object instances here
        this.handler = new Handler(this, Game.TOTALLENGTH);
        this.addKeyListener(new KeyInput(this.handler, this));
        this.menu = new Menu(this, handler, backgroundAudio);
        this.levelSelect = new LevelSelect(this, this.handler, backgroundAudio);
        this.pause = new PauseMenu(this, handler, backgroundAudio);
        this.deathScreen = new DeathScreen(this, this.handler, backgroundAudio);
        this.victoryScreen = new VictoryScreen(this, this.handler, backgroundAudio);
        this.addMouseListener(this.levelSelect);
        this.addMouseMotionListener(this.levelSelect);
        this.addMouseListener(this.menu);
        this.addMouseListener(this.pause);
        this.addMouseListener(this.deathScreen);
        this.addMouseListener(this.victoryScreen);
        this.developerTools = new DeveloperTools(this.handler, this.levelSelect);

        //add all Gameobjects to handler
        /*this.handler.addObject(new Player(700.0f, 200.f, this.handler));
        this.handler.addObject(new Platform(300.0f, 700.0f, 2000, 32, true, handler));
        this.handler.addObject(new Platform(0.0f, 750.0f, 500, 32, true, handler));
        this.handler.addObject(new Platform(-32, 0, 32, Game.HEIGHT, false, this.handler));
        this.handler.addObject(new Platform(1500, 0, 32, Game.HEIGHT, false, this.handler));
        this.handler.addObject(new Enemy(25.0f, 50.0f, this.handler));
        this.handler.addObject(new Goal(500.0f, 600.0f, this.handler));
        this.handler.addObject(new Enemy(100.0f, 50.0f, this.handler));
        this.handler.addObject(new Enemy(200.0f, 50.0f, this.handler));
        this.handler.loadImages();*/

        //Level l = new Level("test", this.handler, 1500);
        //l.save();

        //Level l = (Level) Utilities.loadObjectFromFile("saves/worlds/619327f1a946f2112f2fa86feb2a9922bb240025202e7e34ebbffb1a4c7ef75ea4f274e35db7422272b75f361e7fb50bcec6bbc972f2cfd4499ee4f4bf571969.world");
        //l.load(this.handler);

         this.window = new WindowX(Game.WIDTH, Game.HEIGHT, "GradeRunner", this);

    }

    public void startGame() {

        this.levelSelect.getLevelList()[this.levelSelect.getSelectedLevel()-1].load(this.handler);
        this.handler.loadImages();
        this.developerTools.getPlayer();
    }

    public synchronized void start() { //starts the game
        thread = new Thread(this);
        thread.start();
        this.running = true;
    }

    public void stop() { //stops the game
        try {
            thread.join();
            this.running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() { //game-loop
        this.requestFocus();

        //game-loop variable initialisation
        double DeltaTimeFrames = 0;
        double DeltaTimeTicks = 0;
        long currentTime;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        //game-loop
        while (this.running) {
            currentTime = System.nanoTime();    //gets current time
            DeltaTimeFrames += (currentTime - startTime);   //adds a delta
            DeltaTimeTicks += (currentTime - startTime);    //adds a delta
            startTime = currentTime;
            if (DeltaTimeTicks >= Game.OPTIMAL_TIME_TICK) { //if delta is larger than optimal time, call tick and subtract the optimal time from the delta
                tick();
                this.developerTools.incrementTicks();
                DeltaTimeTicks -= Game.OPTIMAL_TIME_TICK;
            }
            if (DeltaTimeFrames >= Game.OPTIMAL_TIME_FRAMES) {  //if delta is larger than optimal time, call tick and subtract the optimal time from the delta
                render();
                this.developerTools.incrementFrames();
                DeltaTimeFrames -= Game.OPTIMAL_TIME_FRAMES;
            }
            if (System.currentTimeMillis() - timer > 1000) {    //updates the frames and ticks for developerTools
                timer += 1000;
                this.developerTools.updateFrames();
                this.developerTools.updateTicks();
            }
        }
        stop();
    }
    public void tick() { //method for all physics calculation
        //enter tick methods specific to gamestate

        if (this.gamestate == STATE.Game) {
            this.handler.tick();
        } else if (this.gamestate == STATE.Menu) {
            menu.tick();
        } else if (this.gamestate == STATE.Pause) {
            pause.tick();
        }else if (this.gamestate == STATE.Levelselect){
            levelSelect.tick();
        }
        //enter all gamestate
    }
    public void render() { //method for all graphic calculations
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.drawImage(background, 0, 0, null);


        //enter render method specific to gamestate
        this.developerTools.render(g);
        if (gamestate == STATE.Game) {
            this.handler.render(g);
        } else if (gamestate == STATE.Menu) {
            this.menu.render(g);
        } else if (gamestate == STATE.Pause) {
            this.pause.render(g);
        }else if (this.gamestate == STATE.Levelselect){
            this.levelSelect.render(g);
        }else if (this.gamestate == STATE.DeathScreen) {
            this.deathScreen.render(g);
        }else if (this.gamestate == STATE.VictoryScreen) {
            this.victoryScreen.render(g);
        }

        bs.show();
        g.dispose();
    }

    public STATE getGamestate() {return this.gamestate;}    //returns current gamestate
    public void setGamestate(STATE tempState) {this.gamestate = tempState;} //sets current gamestate

    public static float WallCollision(float var, float min, float max) {    //calculates collision with a wall
        if (var >= max) {
            return max;
        }else if (var <= min) {
            return min;
        }
        return var;
    }

    public static float ScrollCollision(float var, float min, float max) {  //calculates collision with a "scroll wall"
        if (var >= max && Game.canScrollRight) {
            Game.toggleScroll();
            return max;
        }else if (var <= min && Game.canScrollLeft) {
            Game.toggleScroll();
            return min;
        }
        return var;
    }

    public static boolean canScroll() { //returns true if the screen can scroll in any direction
        return (Game.canScrollLeft || Game.canScrollRight);
    }
    public static void toggleScroll() {Game.scroll = !Game.scroll;}
    public static boolean getScroll() {return Game.scroll;}
    public WindowX getWindow() {
        return this.window;
    }
    public static void main(String[] args) {
        new Game();
    }
}

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    //variables used for the Window
    public static final int WIDTH = 1250, HEIGHT = WIDTH / 12 * 9;  //width and height of the window
    //variables used to control the scrolling
    public static final float scrollWidthLeft = 200.0f; //width at which the screen starts to scroll to the left
    public static final float scrollWidthRight = 1050.0f; //width at which the screen starts to scroll right
    public static boolean canScrollLeft = false, canScrollRight = true, scroll = false; //check variables if scrolling is allowed

    //variables used for the gameloop
    public static final int MAX_FRAMES_PER_SECOND = 30;
    public static final int MAX_TICKS_PER_SECOND = 30;
    public static final double OPTIMAL_TIME_FRAMES = 1000000000 / Game.MAX_FRAMES_PER_SECOND;  //this is the optimal time a single render calculates
    public static final double OPTIMAL_TIME_TICK = 1000000000 / Game.MAX_TICKS_PER_SECOND; //this is the optimal time a single tick calculates
    boolean running = false;

    //class variabe declaration
    Thread thread;
    Handler handler;
    WindowX windowX;
    DeveloperTools developerTools = null;

    //variables used for gamestate
    public enum STATE { //saves the current state of the game
        Menu,   //this state is active when in the menu
        Game,   //this state is active when the game is running
        Pause   //this state is active when the game is paused
    }
    public STATE gamestate = STATE.Menu; //variable holding the current gamestate

    public Game() { //default constructor
        //create all object instances here
        this.handler = new Handler();
        this.developerTools = new DeveloperTools();
        this.addKeyListener(new KeyInput(this.handler, this));
        windowX = new WindowX(Game.WIDTH, Game.HEIGHT, "GradeRunner", this);

        //create objects here for now

        //add all Gameobjects to handler
        this.handler.addObject(new Player(200.f, 200.f, ID.Player, this.handler));
        this.handler.addObject(new Box(900.0f, 100.0f));
        this.handler.addObject(new Platform(300, 700.0f, true, handler));
        this.handler.addObject(new Platform(100.0f, 800.0f, true, handler));
        this.handler.addObject(this.developerTools);
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

    public void run() { //gameloop
        this.requestFocus();

        //gameloop variable initialisation
        double DeltaTimeFrames = 0;
        double DeltaTimeTicks = 0;
        long currentTime;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        //gameloop
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
            if (this.developerTools != null && System.currentTimeMillis() - timer > 1000) {    //updates the frames and ticks for developerTools
                timer += 1000;
                this.developerTools.updateFrames();
                this.developerTools.updateTicks();
            }
        }
        stop();
    }
    public void tick() { //method for all physics calculation
        //enter tick methods specific to gamestate
        this.handler.tick();
        if (this.gamestate == STATE.Game) {
            pass();
        } else if (this.gamestate == STATE.Menu) {
            pass();
        } else if (this.gamestate == STATE.Pause) {
            pass();
        }
        //enter all gamestates
    }
    public void render() { //method for all graphic calculations
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        this.handler.render(g);
        if (gamestate == STATE.Game) {
            pass();
        } else if (gamestate == STATE.Menu) {
            pass();
        } else if (gamestate == STATE.Pause) {
            pass();
        }
        bs.show();
        g.dispose();
    }

    private void pass() { //method that does nothing. only for development purpose
        return;
    }

    public STATE getGamestate() {return this.gamestate;}    //returns current gamestate
    public void setGamestate(STATE tempState) {this.gamestate = tempState;} //sets current gamestate

    public static int WallCollision(int var, int min, int max) {
        if (var >= max) {
            return max;
        }else if (var <= min) {
            return min;
        }else {
            return var;
        }
    }

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
        if (Game.canScrollLeft || Game.canScrollRight) {
            return true;
        }
        return false;
    }
    public static void toggleScroll() {Game.scroll = !Game.scroll;}
    public static boolean getScroll() {return Game.scroll;}
    public static void main(String args[]) {
        new Game();
    }
}

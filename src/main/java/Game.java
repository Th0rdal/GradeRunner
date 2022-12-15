import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1250, HEIGHT = WIDTH / 12 * 9;
    Thread thread;
    Handler handler;
    boolean running = false;
    public enum STATE {
        Menu,   //this state is active when in the menu
        Game,   //this state is active when the game is running
        Pause   //this state is active when the game is paused
    }
    public STATE gamestate = STATE.Menu; //variable holding the current gamestate
    public Game() { //default constructor
        //create all object instances here
        this.handler = new Handler();

        this.addKeyListener(new KeyInput(this.handler, this));

        new WindowX(this.WIDTH, this.HEIGHT, "GradeRunner", this);
        Player player = new Player(200.f, 200.f, ID.Player, this.handler);
        this.handler.addObject(player);
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
        while (true) {
            tick();
            render();

        }
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
        this.handler.render(g);
        if (gamestate == STATE.Game) {
            pass();
        } else if (gamestate == STATE.Menu) {
            pass();
        } else if (gamestate == STATE.Pause) {
            pass();
        }

        g.dispose();
        bs.show();
    }

    private int pass() { //method that does nothing
        return 1;
    }

    public STATE getGamestate() {return this.gamestate;}
    public void setGamestate(STATE tempState) {this.gamestate = tempState;}

    public static int WallCollision(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        }else if (var <= min) {
            return var = min;
        }else {
            return var;
        }
    }

    public static float WallCollision(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        }else if (var <= min) {
            return var = min;
        }else {
            return var;
        }
    }
    public static void main(String args[]) {
        new Game();
    }
}

import game.GameFrame;
import menuDesign.MenuDesignFrame;
import thread.ThreadNewGame;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadNewGame thread = new ThreadNewGame();

        new MenuDesignFrame();

        thread.start();
        thread.join();

        new GameFrame();

    }

}
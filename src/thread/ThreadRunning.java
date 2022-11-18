package thread;

import game.GameFrame;
import game.GamePanel;
import inputs.MouseInput;

public class ThreadRunning extends Thread {

    GamePanel panel;
    GameFrame frame;
    static boolean exitWindow = false;

    public ThreadRunning(GamePanel panel, GameFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void run() {

        while (panel.running && !MouseInput.newGamePressed) {

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (!panel.running) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            if (panel.gameOver != null) {

                panel.remove(panel.gameOver);
                panel.revalidate();
                panel.repaint();

            }

        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        GamePanel.DELAY = 120;
        inputs.MyKeyAdapter.oppositeDirection = false;

        frame.setTitle("alta");
        frame.dispose();

        try {
            new GameFrame();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}

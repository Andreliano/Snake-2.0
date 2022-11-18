package thread;

import inputs.MouseInputDesign;

public class ThreadNewGame extends Thread {

    @Override
    public void run() {
        while (!MouseInputDesign.newGameDesignPressed) {

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

}

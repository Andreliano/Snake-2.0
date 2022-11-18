package game;

import javax.swing.JFrame;

import inputs.MouseInput;
import thread.ThreadRunning;

public class GameFrame extends JFrame {

    GamePanel panel;

    public GameFrame() throws InterruptedException {

        GamePanel.escapePressed = false;
        MouseInput.newGamePressed = false;
        MouseInput.continuePressed = false;
        MouseInput.exitPressed = false;

        panel = new GamePanel(this);


        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        ThreadRunning threadRunning = new ThreadRunning(panel, this);

        threadRunning.start();
        threadRunning.join();

//		if(this.panel.gameOver != null)
//		{
//
//			this.panel.remove(this.panel.gameOver);
//			this.panel.revalidate();
//			this.panel.repaint();
//
//		}

    }


}

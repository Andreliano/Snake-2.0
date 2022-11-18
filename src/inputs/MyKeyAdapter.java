package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;

import game.GameFrame;
import game.GamePanel;
import menu.MenuFrame;

public class MyKeyAdapter implements KeyListener {

    public GamePanel panel;
    public GameFrame frame;
    public static boolean oppositeDirection = false;

    public MyKeyAdapter(GamePanel panel, GameFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int cod = e.getKeyCode();

        if (KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, java.awt.event.InputEvent.ALT_DOWN_MASK) != null && e.getKeyCode() == KeyEvent.VK_4) {
            System.exit(0);
        }

        switch (cod) {
            case KeyEvent.VK_UP -> {
                if (panel.direction == 'U')
                    break;
                if (panel.direction == 'D')
                    oppositeDirection = true;
                panel.direction = 'U';
            }
            case KeyEvent.VK_DOWN -> {
                if (panel.direction == 'D')
                    break;
                if (panel.direction == 'U')
                    oppositeDirection = true;
                panel.direction = 'D';
            }
            case KeyEvent.VK_LEFT -> {
                if (panel.direction == 'L')
                    break;
                if (panel.direction == 'R')
                    oppositeDirection = true;
                panel.direction = 'L';
            }
            case KeyEvent.VK_RIGHT -> {
                if (panel.direction == 'R')
                    break;
                if (panel.direction == 'L')
                    oppositeDirection = true;
                panel.direction = 'R';
            }
        }

//		if(cod == KeyEvent.VK_UP || cod == KeyEvent.VK_DOWN || cod == KeyEvent.VK_LEFT || cod == KeyEvent.VK_RIGHT)
//		{
//			if(acceleratie == false)
//			{
//				try
//				{
//					panel.move();
//				} catch (InterruptedException | UnsupportedAudioFileException | IOException
//						| LineUnavailableException e1)
//				{
//					e1.printStackTrace();
//				}
//			}
//
//		}

        if (cod == KeyEvent.VK_ESCAPE) {
            GamePanel.escapePressed = true;
            MouseInput.continuePressed = false;
            MouseInput.newGamePressed = false;
            MouseInput.exitPressed = false;


            frame.setVisible(false);
            new MenuFrame();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

        char command = e.getKeyChar();

        command = Character.toLowerCase(command);

        switch (command) {
            case 'w' -> {
                if (panel.direction == 'U')
                    break;
                if (panel.direction == 'D')
                    oppositeDirection = true;
                panel.direction = 'U';
            }
            case 's' -> {
                if (panel.direction == 'D')
                    break;
                if (panel.direction == 'U')
                    oppositeDirection = true;
                panel.direction = 'D';
            }
            case 'a' -> {
                if (panel.direction == 'L')
                    break;
                if (panel.direction == 'R')
                    oppositeDirection = true;
                panel.direction = 'L';
            }
            case 'd' -> {
                if (panel.direction == 'R')
                    break;
                if (panel.direction == 'L')
                    oppositeDirection = true;
                panel.direction = 'R';
            }
        }

//		if(command == 'w' || command == 's' || command == 'a' || command == 'd')
//		{
//			if(acceleratie == false)
//			{
//				try
//				{
//					panel.move();
//				} catch (InterruptedException | UnsupportedAudioFileException | IOException
//						| LineUnavailableException e1)
//				{
//					e1.printStackTrace();
//				}
//			}
//		}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}


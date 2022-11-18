package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GamePanel;
import menu.MenuFrame;
import menu.MenuPanel;

public class MouseInput implements MouseListener {

    public static boolean continuePressed = false;
    public static boolean newGamePressed = false;
    public static boolean exitPressed = false;

    MenuFrame menuFrame;
    MenuPanel menuPanel;

    public MouseInput(MenuFrame menuFrame, MenuPanel menuPanel) {
        this.menuFrame = menuFrame;
        this.menuPanel = menuPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        int X = e.getX();
        int Y = e.getY();

        if (X >= menuPanel.newGameX && X <= menuPanel.newGameX + menuPanel.newGameLength + 2 && Y >= menuPanel.newGameY - 70 && Y <= menuPanel.newGameY - 70 + menuPanel.newGameHeight - 50) {

            GamePanel.escapePressed = false;
            newGamePressed = true;

            menuFrame.removeAll();
            menuFrame.dispose();
        } else if (X >= menuPanel.continueX && X <= menuPanel.continueX + menuPanel.continueLength + 2 && Y >= menuPanel.continueY - 60 && Y <= menuPanel.continueY - 60 + menuPanel.continueHeight - 50) {
            GamePanel.escapePressed = false;
            continuePressed = true;

            menuFrame.removeAll();
            menuFrame.dispose();
        } else if (X >= menuPanel.exitX && X <= menuPanel.exitX + menuPanel.exitLength + 2 && Y >= menuPanel.exitY - 70 && Y <= menuPanel.exitY - 70 + menuPanel.exitHeight - 50) {
            GamePanel.escapePressed = false;
            exitPressed = true;

            menuFrame.removeAll();
            menuFrame.dispose();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}

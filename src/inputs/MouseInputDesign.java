package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import design.DesignColor;
import menuDesign.MenuDesignFrame;
import menuDesign.MenuDesignPanel;

public class MouseInputDesign implements MouseListener {

    public static boolean backgroundPressed = false;
    public static boolean snakePressed = false;
    public static boolean wallPressed = false;
    public static boolean newGameDesignPressed = false;
    MenuDesignFrame menuDesignFrame;

    public MouseInputDesign(MenuDesignFrame menuDesignFrame) {
        this.menuDesignFrame = menuDesignFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        int X = e.getX();
        int Y = e.getY();

        int length = MenuDesignPanel.length + 10;
        int height = MenuDesignPanel.height;

        if (X >= MenuDesignPanel.backgroundX && X <= MenuDesignPanel.backgroundX + length && Y >= MenuDesignPanel.backgroundY && Y <= MenuDesignPanel.backgroundY + height) {
			/*
			  In cazul in care am apasat deja pe o optiune din fereastra de design,
			  de exemplu background color, nu mai pot apasa pe alta optiune
			  sau pe aceeasi inca odata pana nu inchid fereastra de colorare
			*/
            if (!DesignColor.DesignOptionOpened) {
                new DesignColor(menuDesignFrame);
                backgroundPressed = true;
                DesignColor.DesignOptionOpened = true;
                System.out.println("background");
            }
        } else if (X >= MenuDesignPanel.snakeX && X <= MenuDesignPanel.snakeX + length && Y >= MenuDesignPanel.snakeY && Y <= MenuDesignPanel.snakeY + height) {
            if (!DesignColor.DesignOptionOpened) {
                new DesignColor(menuDesignFrame);
                snakePressed = true;
                DesignColor.DesignOptionOpened = true;
                System.out.println("snake");
            }
        } else if (X >= MenuDesignPanel.wallX && X <= MenuDesignPanel.wallX + length && Y >= MenuDesignPanel.wallY && Y <= MenuDesignPanel.wallY + height) {
            if (!DesignColor.DesignOptionOpened) {
                new DesignColor(menuDesignFrame);
                wallPressed = true;
                DesignColor.DesignOptionOpened = true;
                System.out.println("wall");
            }
        } else if (X >= MenuDesignPanel.newGameDesignX && X <= MenuDesignPanel.newGameDesignX + length && Y >= MenuDesignPanel.newGameDesignY && Y <= MenuDesignPanel.newGameDesignY + height) {
            newGameDesignPressed = true;
            new DesignColor(menuDesignFrame);
            DesignColor.DesignOptionOpened = true;
            System.out.println("new game");
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

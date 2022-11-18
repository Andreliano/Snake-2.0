package menu;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {

    MenuPanel menuPanel;

    public MenuFrame() {

        menuPanel = new MenuPanel(this);

        this.add(menuPanel);
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }

}

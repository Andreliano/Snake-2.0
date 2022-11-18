package menuDesign;

import javax.swing.JFrame;

public class MenuDesignFrame extends JFrame {

    MenuDesignPanel menuDesignPanel;

    public MenuDesignFrame() {
        menuDesignPanel = new MenuDesignPanel(this);


        this.add(menuDesignPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocation(0, 0);


    }

}

package TopBar;

import javax.swing.*;

public class TopBarHomeScreen extends TopBar {


    public TopBarHomeScreen() {
        super();

        // ajout du prolongement du background
        ImageIcon iconImageBackground = new ImageIcon(ClassLoader.getSystemResource("Images/wallpaper_PNG.png"));
        JLabel labelImageBackground = new JLabel();
        labelImageBackground.setIcon(iconImageBackground);
        labelImageBackground.setBounds(16, 20, 300, 644);
        add(labelImageBackground);

    }
}


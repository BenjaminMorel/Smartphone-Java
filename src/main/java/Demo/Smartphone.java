package Demo;


import General.CloseButton;
import General.Time;
import Screen.HomeScreen;
import Screen.LockedScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class Smartphone extends JFrame{
    private Image png;
    private LockedScreen lockedScreen;


    public Smartphone() {

        // ajout de l'image du smartphone
        png = new ImageIcon("src/main/java/Images/smartphone_PNG.png").getImage();

        // modification de la forme pour qu'elle prenne celui du smartphone
        this.setSize(600,690);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(20, 15, 300, 623, 70, 70));
        setLocationRelativeTo(null);

        // Calling the lockedScreen
        lockedScreen = new LockedScreen();
    }

    public void paint(Graphics g){
        lockedScreen.paint(g);
        g.drawImage(png, 18, 13, null);
    }
}

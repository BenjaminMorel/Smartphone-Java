package Demo;


import General.CloseButton;
import General.Time;
import Screen.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class Smartphone extends JFrame{
    private Image png ;
    private HomeScreen homeScreen ;


    public Smartphone() {

        // ajout de l'image du smartphone
        png = new ImageIcon("src/main/java/Images/smartphone_PNG.png").getImage();

        // modification de la forme pour qu'elle prenne celui du smartphone
        this.setSize(600,690);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(20, 15, 300, 623, 70, 70));

        // on appelle le homescreen
        homeScreen = new HomeScreen() ;


    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        homeScreen.paint(g2D);

        g2D.drawImage(png, 18, 13, null);
    }
}

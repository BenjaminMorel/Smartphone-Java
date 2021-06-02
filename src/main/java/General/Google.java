package General;

import javax.swing.*;
import java.awt.*;

public class Google extends JPanel {


    public Google() {
        setLayout(null);

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone) ;

        // ajout du logo google et de la searchBar
        ImageIcon iconGoogle = new ImageIcon("src/main/java/Images/googleSearchLogo.PNG") ;
        Image imageGoogle = iconGoogle.getImage();
        Image newimgGoogle = imageGoogle.getScaledInstance(280, 160,  java.awt.Image.SCALE_SMOOTH);
        iconGoogle = new ImageIcon(newimgGoogle);

        JLabel labelIconGoogle = new JLabel() ;
        labelIconGoogle.setIcon(iconGoogle);
        labelIconGoogle.setBounds(30, -30, 320, 600);
        add(labelIconGoogle) ;

        setBackground(Color.white);
    }
}

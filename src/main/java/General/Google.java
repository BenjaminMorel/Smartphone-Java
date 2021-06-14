package General;

import javax.swing.*;
import java.awt.*;

public class Google extends JPanel {

    /**
     * Constructeur de classe décoration
     * cette classe sert à faire joli
     */

    public Google() {
        setLayout(null);
        setSmartphoneShape();
        googleSearchBar();
        setBackground(Color.white);
    }

    /**
     * ajout du contour latéral du smartphone
     */

    public void setSmartphoneShape() {
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));  // va chercher l'image relativement à son positionnement sur votre orginateur
        JLabel labelContourSmartphone = new JLabel();                                                                       // création du JLabel dans lequel l'image sera ajouté
        labelContourSmartphone.setIcon(iconContourSmartphone);                                                              // ajout de l'image dans le JLabel
        labelContourSmartphone.setBounds(9, -8, 320, 600);                                                // positionnement du JLabel relativement au pannel HomeScreen
        add(labelContourSmartphone);                                                                                        // ajout du JLabel dans le JPanel HomeScreen
    }

    /**
     * ajout du logo google et de la searchBar
     */

    public void googleSearchBar(){
        ImageIcon iconGoogle = new ImageIcon(ClassLoader.getSystemResource("Images/googleSearchLogo.PNG")) ;
        Image imageGoogle = iconGoogle.getImage();
        Image newimgGoogle = imageGoogle.getScaledInstance(280, 160,  java.awt.Image.SCALE_SMOOTH);
        iconGoogle = new ImageIcon(newimgGoogle);

        JLabel labelIconGoogle = new JLabel() ;
        labelIconGoogle.setIcon(iconGoogle);
        labelIconGoogle.setBounds(30, -30, 320, 600);
        add(labelIconGoogle) ;
    }

}

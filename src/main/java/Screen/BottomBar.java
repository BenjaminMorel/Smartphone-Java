package Screen;

import Demo.Smartphone;
import Errors.SmartphoneException;
import TopBar.TopBarHomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomBar extends JPanel implements ActionListener {
    // Création de variables générales
    private JPanel panel ;
    private JLabel label1 ;
    private Dimension dimension = new Dimension(300, 78) ;
    private Smartphone switchapp ;
    private JButton button ;
    private JButton closeButton ;
    private TopBarHomeScreen topBar ;

    /**
     * Constructeur créant le bouton central ainsi que la shape sud du smartphone
     */

    public BottomBar() {
        setLayout(null);
        setPreferredSize(dimension);
        setSmartphoneShape();                                                                                               // Ajout du contour du smartphone
        homeScreenButton();                                                                                                 // appel de la méthode qui va créer le bouton central

    }

    /**
     * ajoute le contour du smartphone au panel BottomScreen
     */

    public void setSmartphoneShape() {
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -609, 320, 660);
        add(labelContourSmartphone);
    }

    public void homeScreenButton(){
        button = new JButton();                                                                                             // Ajout du bouton, il est en setBorder/Focus/ContentAreaFilled false pour paraitre invisible mais reste cliquable
        button.addActionListener(this);
        button.setBounds(135, 7, 70, 23);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        add(button);
    }



    /**
     * Méthode ActionListener
     * @param e
     * un bouton permettant de revenir au menu principal
     * un bouton permettant d'éteindre le smartphone
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            // changement de panel principal par le homescreen
            try {
                switchapp = new Smartphone(new HomeScreen(), new TopBarHomeScreen());
            } catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}

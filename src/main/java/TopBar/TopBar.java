package TopBar;

import Demo.Smartphone;
import General.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel implements ActionListener {

    private Dimension dimension = new Dimension(300, 43);

    private JLabel labelHomeScreen ;
    private Time timeGeneral = new Time() ;
    private Font fontTime = new Font("Roboto", Font.PLAIN, 13) ;
    private JButton closeButton ;

    /**
     * Constructeur permettant de créer une topBar
     */

    public TopBar() {
        setLayout(null);
        setPreferredSize(dimension);
        contourSmartphone();                    // ajout du contour du smartphone
        infoHours();                            // ajout de l'heure en haut à gauche
        closeButton();                          // appel de la méthode qui va créer le bouton close
    }

    /**
     * Méthode permettant l'affichage nord du smartphone
     */

    public void contourSmartphone(){
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, 3, 320, 660);
        add(labelContourSmartphone);
    }

    /**
     * Méthode permettant d'afficher les heures en haut à gauche de la topBAr
     */

    public void infoHours(){
        JLabel labelHours = new JLabel(timeGeneral.getTime());
        labelHours.setForeground(Color.white);
        labelHours.setFont(fontTime);
        labelHours.setBounds(42, 17, 70, 30);
        add(labelHours);
    }

    /**
     * Méthode permettant d'afficher le bouton close en haut à gauche de l'écran
     */

    public void closeButton(){
        ImageIcon imageCloseButton = new ImageIcon(ClassLoader.getSystemResource("Images/onOff.png")) ;
        closeButton = new JButton() ;
        closeButton.addActionListener(this);
        closeButton.setBounds(277, 17, 30, 30);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setIcon(imageCloseButton);
        add(closeButton) ;
    }

    /**
     * Méthode ActionListener contenant les actions du bouton close
     * @param e
     * close button qui permet de fermer le smartphone
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton){
            System.out.println("ok je m'éteins");
            SwingUtilities.getWindowAncestor(this).dispose();                                                           // va chercher le parent (la JFrame) et la ferme
        }
    }
}

package Screen;

import General.Time;

import javax.swing.*;
import java.awt.*;

public class TopBarContactApp extends JPanel{
    private Dimension dimension = new Dimension(300, 43);

    private JLabel labelHomeScreen ;
    private Time timeGeneral = new Time() ;
    private Font fontTime = new Font("Roboto", Font.PLAIN, 13) ;



    public TopBarContactApp() {
        setLayout(null);
        setPreferredSize(dimension);


        setBackground(Color.BLUE);

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, 3, 320, 660);
        add(labelContourSmartphone);

        // ajout de l'heure en haut à gauche
        JLabel labelHours = new JLabel(timeGeneral.getTime()) ;
        labelHours.setForeground(Color.BLACK);
        labelHours.setFont(fontTime);
        labelHours.setBounds(42, 17, 70, 30);
        add(labelHours);
}
}

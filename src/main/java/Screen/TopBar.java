package Screen;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel{

    private Dimension dimension = new Dimension(300, 43) ;

    public TopBar() {
        setLayout(null);
        setPreferredSize(dimension);
        setBackground(Color.BLUE);

        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, 3, 320, 660);
        add(labelContourSmartphone) ;
    }

}

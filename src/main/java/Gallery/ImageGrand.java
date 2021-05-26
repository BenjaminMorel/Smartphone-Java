package Gallery;

import javax.swing.*;
import java.awt.*;

public class ImageGrand extends JPanel {

    private JPanel panel;
    private JLabel label;
    private Image image;
    private int width;
    private int height;

    public ImageGrand(String name) {

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone) ;

        height = 500;
        width = 300;

        setLayout(null);

        panel = new JPanel();

        System.out.println(name);
        image = new ImageIcon(name).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);


        label = new JLabel(new ImageIcon(image));
        panel.add(label);
        panel.setBackground(Color.green);
        panel.setBounds(20,20,width-10,height-10);
        add(panel);

    }
}

package Demo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Smartphone extends JFrame{
    private JPanel panel;
    private JLabel label;
    private ImageIcon png ;
    private Shape shape ;
    private JLayeredPane layeredPane ;

    public Smartphone() {

        panel = new JPanel();
        label = new JLabel();

        // ajout de l'image du smartphone
        ImageIcon png = new ImageIcon("C:\\Users\\choff\\IdeaProjects\\smartphone\\src\\main\\java\\smartphone_PNG.png");
        label.setIcon(png);
        panel.add(label);

        // ajout du panel
        add(panel);

        // modification de la forme pour qu'elle prenne celui du smartphone
        setSize(340,690);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(20, 15, 300, 623, 70, 70));

        // créer label wallpaper
        panel.setBackground(Color.yellow);

        // layeredPane sert à créer un système de couches afin d'organiser les panels
        layeredPane = new JLayeredPane();
        layeredPane.highestLayer();
    }

}

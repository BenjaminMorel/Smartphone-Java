package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class LockedScreen extends JFrame implements ActionListener {

    private Image wallpaper;
    private Image image1;
    private JButton button1;

    public LockedScreen() {
        this.setSize(600, 690);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(20, 15, 300, 623, 70, 70));
        wallpaper = new ImageIcon("Smartphone/src/main/resources/Images/LockedScreen.jpg").getImage();
    }


    public void paint(Graphics g){
        g.drawImage(wallpaper, 18,13,null);
        g.drawImage(image1, 60,350,null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("coucou");
        }
    }
}

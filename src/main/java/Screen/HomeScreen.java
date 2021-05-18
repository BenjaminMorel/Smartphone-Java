package Screen;
import Demo.Smartphone ;
import General.Time;
import Meteo.WeatherWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;


public class HomeScreen extends JPanel implements ActionListener {
    private Time hours;
    private JPanel panel;
    private Image imageBackround ;
    private Dimension dimension = new Dimension(300, 200) ;
    private Smartphone switchApp ;
    private JButton button = new JButton("yeslife") ;
    private JButton button1 = new JButton("yeslife1") ;


    public HomeScreen() {
        imageBackround = new ImageIcon("src/main/java/Images/wallpaper_PNG.png").getImage() ;
        setPreferredSize(dimension);

        setLayout(new FlowLayout());

        button.addActionListener(this);

        add(button) ;
        add(button1) ;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            try {
                switchApp = new Smartphone(new WeatherWindow("Sierre"));
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g ;
        g2D.drawImage(imageBackround, 15, 25, 310, 580, null) ;
    }


}

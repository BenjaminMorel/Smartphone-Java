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
    private Time timeGeneral = new Time();
    private ImageIcon imageBackround ;
    private Smartphone switchApp ;
    private JButton buttonWeather ;



    public HomeScreen() {
        setLayout(null);



        // bouton weatherapp
        buttonWeather = new JButton("yeslife") ;
        buttonWeather.addActionListener(this);
        buttonWeather.setBounds(50, 50, 100, 40);
        add(buttonWeather) ;


        // set background
        //forme grise au top
        ImageIcon iconThingOnTop = new ImageIcon("src/main/java/Images/HomeScreen/grayThingOnTop.png") ;
        JLabel labelThingOnTop = new JLabel() ;
        labelThingOnTop.setIcon(iconThingOnTop);
        labelThingOnTop.setBounds(42, 0, 290, 150);
        add(labelThingOnTop) ;

        //image background
        imageBackround = new ImageIcon("src/main/java/Images/wallpaper_PNG.png");
        JLabel labelBackground = new JLabel() ;
        labelBackground.setIcon(imageBackround);
        labelBackground.setBounds(17,0 , 303, 600);
        add(labelBackground) ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonWeather){
            try {
                switchApp = new Smartphone(new WeatherWindow("Sierre"));
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }



}

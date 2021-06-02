package TopBar;

import javax.swing.*;


public class TopBarWeatherApp extends TopBar {


    public TopBarWeatherApp() {
        super();

        // ajout du prolongement du background
        ImageIcon iconImageBackground = new ImageIcon("src/main/java/Images/WeatherIcon/meteoBackground.png") ;
        JLabel labelImageBackground = new JLabel() ;
        labelImageBackground.setIcon(iconImageBackground);
        labelImageBackground.setBounds(16, 20, 300, 644);
        add(labelImageBackground) ;

    }
}

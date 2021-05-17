package Meteo;

import Demo.Smartphone;
import General.Time;
import Screen.HomeScreen;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class WeatherWindow extends JPanel implements ActionListener {

    private JButton button = new JButton("test") ;
    private Smartphone switchApp;
    private Weather weather = new Weather() ;
    private Time timeGeneral = new Time() ;

    // text font + color
    private Font locationFont = new Font("Roboto", Font.PLAIN, 19);
    private Font moyenFont = new Font("Roboto", Font.PLAIN, 18) ;
    private Font timeFont = new Font("Roboto", Font.PLAIN, 16) ;
    private Font bigTemperatureFont = new Font("Roboto", Font.PLAIN, 65) ;
    private Color liightGray = new Color(0xDBDBDB) ;

    // séparation de la page en panels
    private JPanel panelVille = new JPanel() ;
    private JPanel panelTime = new JPanel() ;
    private JPanel panelMaxMinTemp = new JPanel() ;
    private JPanel panelDescription = new JPanel() ;


    public WeatherWindow() {
        setLayout(null); // permet d'ajouter des labels et panels avec setBounds


        // les panels sont créés en dehors du constructeur
        // Panel + label Ville
        panelVille.setOpaque(false);
        panelVille.setBounds(17, 50, 303, 20);
        panelVille.setLayout(new GridBagLayout());
        add(panelVille) ;

        String stringVille = weather.getVille();
        JLabel labelVille = new JLabel(stringVille) ;
        labelVille.setForeground(Color.white);
        labelVille.setFont(locationFont);
        panelVille.add(labelVille) ;



        // Panel + label time
        panelTime.setOpaque(false);
        panelTime.setBounds(17, 80, 303, 20);
        panelTime.setLayout(new GridBagLayout());
        add(panelTime);

        String stringTime = timeGeneral.getDate() ;     // input de la date
        stringTime += " " ;
        stringTime += timeGeneral.getMonth() ;          // input du mois
        stringTime += " " ;
        stringTime += timeGeneral.getTime() ;           // input de l'heure
        JLabel time = new JLabel(stringTime) ;
        time.setForeground(Color.lightGray);
        time.setFont(timeFont);
        panelTime.add(time) ;


        // Label température actuelle (grand) + Image logo temps
        String[] contentTemperatureActuelle = weather.getTemperature() ;
        String stringTemperatureActuelle = contentTemperatureActuelle[0] ;
        stringTemperatureActuelle = stringTemperatureActuelle.substring(5) ;                // découpage du string pour ne laisser que le nombre
        double doubleTemperatureActuelle = Double.parseDouble(stringTemperatureActuelle);   // méthode pour convertir les Strings en double
        JLabel actualTemperature = new JLabel((int)doubleTemperatureActuelle + "°");   // on force le int dans le label pour ne garder que ce qui nous intéresse
        actualTemperature.setBounds(170, 90, 150, 130);
        actualTemperature.setFont(bigTemperatureFont);
        actualTemperature.setForeground(Color.white);
        add(actualTemperature);

        // ajout d'une image(test)
        JLabel labelImage = new JLabel();
        String[] contentStringIcon = weather.getWeather() ;
        String stringIcon = contentStringIcon[3] ;
        stringIcon = stringIcon.substring(6) ;                                  // découpage du string de l'image à choisir
        String stringIconPath = "src/main/java/Images/WeatherIcon/" ;          // path sans l'image
        stringIconPath += stringIcon ;                                          // rajout de l'image à choisir et du .png
        stringIconPath += ".png" ;
        ImageIcon image = new ImageIcon(stringIconPath);                        // on va chercher l'image avec le lien que l'on vient de créer
        labelImage.setIcon(image);
        labelImage.setBounds(55, 100, 120, 100);
        add(labelImage);


        // Label + panel MAX/MIN temp, temp feels_like
        panelMaxMinTemp.setOpaque(false);
        panelMaxMinTemp.setBounds(17, 210, 303, 20);
        panelMaxMinTemp.setLayout(new GridBagLayout());
        add(panelMaxMinTemp);

        JLabel maxMinTemp = new JLabel("yoyoyoyoyoyo") ;
        maxMinTemp.setForeground(liightGray);
        maxMinTemp.setFont(moyenFont);
        panelMaxMinTemp.add(maxMinTemp) ;


        // label + panel Description meteo
        panelDescription.setOpaque(false);
        panelDescription.setBounds(17, 247, 303, 20);
        panelDescription.setLayout(new GridBagLayout());
        add(panelDescription);

        JLabel labelDescription = new JLabel("bashdbbvasdbh") ;
        labelDescription.setForeground(Color.white);
        labelDescription.setFont(moyenFont);
        panelDescription.add(labelDescription) ;


        // label sunrise + sunset
//        JLabel labelSunrise = new JLabel();
//        ImageIcon imageSunrise = new ImageIcon("src/main/java/Images/WeatherIcon/sunrise.png");
//        labelSunrise.setIcon(imageSunrise);
//        labelSunrise.setBounds(40, 300, 100, 100);
//        add(labelSunrise) ;
//
//        JLabel labelSet = new JLabel();
//        ImageIcon imageSet = new ImageIcon("src/main/java/Images/WeatherIcon/sunset.png");
//        labelSet.setIcon(imageSet);
//        labelSet.setBounds(40, 334, 100, 100);
//        add(labelSet) ;




        //ajout du background
        //label background top
        JLabel backgroundTop = new JLabel();
        ImageIcon imageBT = new ImageIcon("src/main/java/Images/WeatherIcon/backgroundTop.png");
        backgroundTop.setIcon(imageBT);
        backgroundTop.setBounds(28, 195, 303, 350);
        add(backgroundTop);

        // label background bottom
        JLabel backgroundBottom = new JLabel();
        ImageIcon imageBB = new ImageIcon("src/main/java/Images/WeatherIcon/backgroundBottom.png");
        backgroundBottom.setIcon(imageBB);
        backgroundBottom.setBounds(28, 335, 303, 350);
        add(backgroundBottom);

        // background image
        JLabel backgroundImage = new JLabel();
        ImageIcon iconBackgroundImage = new ImageIcon("src/main/java/Images/WeatherIcon/meteoBackground.png") ;
        backgroundImage.setIcon(iconBackgroundImage);
        backgroundImage.setBounds(10, 0, 330, 600);
        add(backgroundImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            switchApp = new Smartphone(new HomeScreen()) ;
        }
    }

}

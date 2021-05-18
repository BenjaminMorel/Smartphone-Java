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
import java.net.MalformedURLException;

public class WeatherWindow extends JPanel implements ActionListener {

    private JTextField textField ;
    private JButton searchButton ;
    private Smartphone switchApp;
    private String setVille = "Sierre" ;
    private Weather weather ;
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


    public WeatherWindow(String setVille) throws IOException {
        this.setVille=setVille ;
        setLayout(null); // permet d'ajouter des labels et panels avec setBounds


        textField = new JTextField() ;
        textField.addActionListener(this);
        textField.setBounds(70, 6, 200, 30);
        textField.setOpaque(false);
        textField.setFont(moyenFont);
        textField.setForeground(Color.white);
        textField.setVisible(false);
        add(textField);

        searchButton = new JButton() ;
        searchButton.addActionListener(this);
        searchButton.setBounds(275, 4, 40, 40);
        ImageIcon iconSearchButton = new ImageIcon("src/main/java/Images/WeatherIcon/searchBar.png") ;
        searchButton.setIcon(iconSearchButton);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        add(searchButton) ;

        weather = new Weather(setVille) ;
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

        // ajout d'une image de la grande icone
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


        // Label + panel MAX/MIN temp, temp feels_like                      pattern recherché : 30°/17° Feels like 31°
        panelMaxMinTemp.setOpaque(false);
        panelMaxMinTemp.setBounds(17, 210, 303, 20);
        panelMaxMinTemp.setLayout(new GridBagLayout());
        add(panelMaxMinTemp);

        String[] contentMaxMinTemp = weather.getTemperature() ;             // même principe que plus haut mais répété 3x
        String stringMaxTemp = contentMaxMinTemp[3];
        stringMaxTemp = stringMaxTemp.substring(10);
        double doubleMaxTemp = Double.parseDouble(stringMaxTemp) ;
        String stringMinTemp = contentMaxMinTemp[2];
        stringMinTemp = stringMinTemp.substring(10);
        double doubleMinTemp = Double.parseDouble(stringMinTemp) ;
        String stringFeelsLike = contentMaxMinTemp[1];
        stringFeelsLike = stringFeelsLike.substring(12);
        double doubleFeelsLike = Double.parseDouble(stringFeelsLike) ;
        JLabel maxMinTemp = new JLabel((int)doubleMaxTemp + "°/" + (int)doubleMinTemp + "° Feels like " + (int)doubleFeelsLike + "°") ;
        maxMinTemp.setForeground(liightGray);
        maxMinTemp.setFont(moyenFont);
        panelMaxMinTemp.add(maxMinTemp) ;


        // label + panel Description meteo
        panelDescription.setOpaque(false);
        panelDescription.setBounds(17, 247, 303, 20);
        panelDescription.setLayout(new GridBagLayout());
        add(panelDescription);

        String[] stringDescriptionContent = weather.getWeather() ;
        String stringDescription = stringDescriptionContent[2] ;
        stringDescription = stringDescription.substring(13) ;
        JLabel labelDescription = new JLabel(stringDescription) ;
        labelDescription.setForeground(Color.white);
        labelDescription.setFont(moyenFont);
        panelDescription.add(labelDescription) ;


        // Texte + icone sunrise et sunset
        // icone sunrise
        JLabel labelSunrise = new JLabel();
        ImageIcon imageSunrise = new ImageIcon("src/main/java/Images/WeatherIcon/sunrise.png");
        labelSunrise.setIcon(imageSunrise);
        labelSunrise.setBounds(40, 275, 100, 100);
        add(labelSunrise) ;
        // texte sunrise
        String[] stringContentSunrise = weather.getSun() ;                              // transformation de String[] en int (même procédure que plus haut)
        String stringSunrise = stringContentSunrise[3];
        stringSunrise = stringSunrise.substring(9) ;
        int intStringSunrise = Integer.parseInt(stringSunrise) ;
        stringSunrise = timeGeneral.unixToDate(intStringSunrise) ;                      // changement de secondes unix en String (méthode dans General.Time)
        JLabel labelStringSunrise = new JLabel(stringSunrise) ;
        labelStringSunrise.setForeground(Color.white);
        labelStringSunrise.setFont(timeFont);
        labelStringSunrise.setBounds(90, 307, 150, 30);
        add(labelStringSunrise) ;

        // icone sunset
        JLabel labelSet = new JLabel();
        ImageIcon imageSet = new ImageIcon("src/main/java/Images/WeatherIcon/sunset.png");
        labelSet.setIcon(imageSet);
        labelSet.setBounds(40, 322, 100, 100);
        add(labelSet) ;
        // texte sunset
        String[] stringContentSunset = weather.getSun() ;
        String stringSunset = stringContentSunset[4] ;
        stringSunset = stringSunset.substring(8) ;
        int intStringSunset = Integer.parseInt(stringSunset);
        stringSunset = timeGeneral.unixToDate(intStringSunset);             // fonction qui transforme les heures unix en heures compréhensibles
        JLabel labelStringSunset = new JLabel(stringSunset) ;
        labelStringSunset.setForeground(Color.white);
        labelStringSunset.setFont(timeFont);
        labelStringSunset.setBounds(90, 355, 150, 30);
        add(labelStringSunset) ;

        // label + icone humidité
        // icone humidité
        JLabel labelHumidite = new JLabel() ;
        ImageIcon iconHumidite = new ImageIcon("src/main/java/Images/WeatherIcon/humidity.png") ;
        labelHumidite.setIcon(iconHumidite);
        labelHumidite.setBounds(52, 390, 50, 50);
        add(labelHumidite);

        // label humidité
        String[] humiditeContent = weather.getTemperature() ;
        String humiditeString = humiditeContent[5] ;
        humiditeString = humiditeString.substring(10) ;
        humiditeString = humiditeString.substring(0,humiditeString.length()-1) ;
        JLabel labelStringHumidite = new JLabel("humidité : " + humiditeString + "%") ;
        labelStringHumidite.setForeground(Color.white);
        labelStringHumidite.setFont(timeFont);
        labelStringHumidite.setBounds(90, 390, 150, 50);
        add(labelStringHumidite) ;


        // ajout de toutes les heures au bas de l'écran (hourly)
        String stingGetTime = timeGeneral.getTime() ;
        stingGetTime = stingGetTime.substring(0, stingGetTime.length()-3) ;
        int intStingGetTime = Integer.parseInt(stingGetTime) ;
        int intActualHour = intStingGetTime ;
        intStingGetTime++ ;                                                         // heure +1 pour démarrer les guess depuis la seconde heure
        int hBounds = 48 ;                                                          // valeur horizontale de base
        JLabel hourlyLabelHour ;


        for (int i = 0 ; i < 5 ; i++){                                              // boucle for qui va s'occuper de créer 5 labels
            hourlyLabelHour = new JLabel(""+ intStingGetTime + ":00");
            hourlyLabelHour.setForeground(Color.white);
            hourlyLabelHour.setFont(timeFont);
            hourlyLabelHour.setBounds(hBounds, 460, 50, 50);   // hBounds s'incrémente à chaque passage, c'est la seule variable qui change dans setBounds.
            add(hourlyLabelHour) ;
            hBounds += 50 ;
            if((intStingGetTime == 21) || (intStingGetTime ==  22) || (intStingGetTime == 23) || (intStingGetTime == 24)){ // jour suivant => remise à 0
                intStingGetTime = intStingGetTime-24 ; // on enlève 24(h) au compteur
                intStingGetTime += 3 ;

            }
            else{
                intStingGetTime += 3 ; // incrémentation des heures (+3)
            }
        }

        // ajout des icones en bas de l'écran (hourly)
        JLabel labelImagesDuBas ;
        ImageIcon iconImageDuBas ;
        hBounds = 40 ;
        String stringGetHoursSunset = timeGeneral.unixToHours(intStringSunset) ;
        int intGetHoursSunset = Integer.parseInt(stringGetHoursSunset) ;                // les heures du coucher/lever soleil servent à adapter l'icone à l'heure
        String stringGetHoursSunrise = timeGeneral.unixToHours(intStringSunrise) ;
        int intGetHoursSunrise = Integer.parseInt(stringGetHoursSunrise) ;
        String stringGetIconPath = "" ;
        stringIcon = stringIcon.substring(0, stringIcon.length()-1) ; // on coupe le "d" ou "n" après car il sera désormais variable selon l'heure
        System.out.println(stringIcon);

        for(int i = 0 ; i < 5 ; i++){
            labelImagesDuBas = new JLabel() ;
            stringGetIconPath = "src/main/java/Images/WeatherIcon/" ; // on reset le path
            if((intActualHour>=intGetHoursSunrise) && (intActualHour<=intGetHoursSunset)){
                // icone jour
                stringGetIconPath += stringIcon + "d.png" ;
                iconImageDuBas = new ImageIcon(stringGetIconPath) ;
                Image imageDuBas = iconImageDuBas.getImage();           // changer la taille de l'icone (la rétrécir)
                Image newimg = imageDuBas.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                iconImageDuBas = new ImageIcon(newimg);
            }
            else{
                // icone nuit
                stringGetIconPath += stringIcon + "n.png" ;
                iconImageDuBas = new ImageIcon(stringGetIconPath) ;
                Image imageDuBas = iconImageDuBas.getImage();
                Image newimg = imageDuBas.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                iconImageDuBas = new ImageIcon(newimg);
            }
            labelImagesDuBas.setIcon(iconImageDuBas);
            labelImagesDuBas.setBounds(hBounds, 490, 200, 50);
            add(labelImagesDuBas) ;
            hBounds += 50 ;

        }




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
        if(e.getSource() == searchButton){
            System.out.println("buttonABF;AKBSHFH");
            if(textField.getText().equals("")){
                textField.setVisible(true);
            }
            else{
                try {
                    System.out.println(textField.getText());
                    switchApp = new Smartphone(new WeatherWindow(textField.getText())) ;
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getSource() == textField){
            setVille = textField.getText() ;
        }
    }

}

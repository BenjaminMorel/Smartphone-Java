package Apps.Weather;

import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import General.Internet;
import General.Time;
import TopBar.TopBarWeatherApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherWindow extends JPanel implements ActionListener {

    // variables générales
    private JTextField textField ;
    private JButton searchButton ;
    private final Weather weather ;
    private final Time timeGeneral = new Time() ;

    // variables utilisées dans plusieurs méthodes
    private String stringIcon ;
    private int intStringSunset ;
    private int intStringSunrise ;

    // text font + color
    private final Font locationFont = new Font("Roboto", Font.PLAIN, 19);
    private final Font moyenFont = new Font("Roboto", Font.PLAIN, 18) ;
    private final Font timeFont = new Font("Roboto", Font.PLAIN, 16) ;
    private final Font bigTemperatureFont = new Font("Roboto", Font.PLAIN, 65) ;
    private final Color liightGray = new Color(0xDBDBDB) ;

    // séparation de la page en panels
    private final JPanel panelVille = new JPanel() ;
    private final JPanel panelTime = new JPanel() ;
    private final JPanel panelMaxMinTemp = new JPanel() ;
    private final JPanel panelDescription = new JPanel() ;

    /**
     * Constructeur
     * @param setVille
     * initialise les données selon la ville choisie (string)
     * @throws SmartphoneException
     * si il y a une erreur lors de l'initialisation / lors du changement de ville
     */

    public WeatherWindow(String setVille) throws SmartphoneException {
        setLayout(null);                                // permet d'ajouter des labels et panels avec setBounds
        try {
            weather = new Weather(setVille);            // va chercher toutes les données météo
        }catch (SmartphoneException sm){
            throw new SmartphoneException(sm.getErrorMessage(), ErrorCode.BAD_PARAMETER) ;
        }

        // méthodes permettant l'ajout de la partie graphique
        contourSmartphone();                            // ajout du contour du smartphone
        boutonSearch();                                 // ajout du bouton search en haut à droite
        infoCity();                                     // ajout des informations sur la ville
        infoTime();                                     // ajout de l'info sur la date actuelle
        infoTemperatureAndWeather();                    // ajout de la température et de l'icone centrale
        infoMinMaxTemp();                               // ajout de l'info sur la température minimum et maximum
        infoDescriptionWeather();                       // ajout de la description de la météo
        infoSunriseAndSunset();                         // ajout de l'info du lever/coucher de soleil
        infoHumidity();                                 // ajout de l'info sur l'humidité
        infoHourly();                                   // ajout de toutes les heures au bas de l'écran (hourly)
        backgroundApp();                                // ajout du background
    }

    /**
     * ajoute le contour du smartphone
     */

    public void contourSmartphone(){
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));  // ajout de l'image du countour du smartphone en icone
        JLabel labelContourSmartphone = new JLabel() ;                                                                      // création d'un JLabel qui va acueillir l'imageIcon
        labelContourSmartphone.setIcon(iconContourSmartphone);                                                              // ajout de l'imageIcon dans le JLabel
        labelContourSmartphone.setBounds(9, -8, 320, 600);                                                // placement du JLabel relativement au panel de la classe
        add(labelContourSmartphone) ;                                                                                       // ajout du JLabel dans le panel de la classe
    }

    /**
     * ajoute le bouton search en haut à droite
     * ajoute la barre de recherche
     * format : (image de loupe)
     */

    public void boutonSearch(){
        // barre de recherche
        textField = new JTextField() ;
        textField.addActionListener(this);
        textField.setBounds(70, 6, 200, 30);                                                              // placement de la barre de recherche relativement au panel de la classe
        textField.setOpaque(false);
        textField.setFont(moyenFont);                                                                                       // changement de la police
        textField.setForeground(Color.white);                                                                               // changement de la couleur du texte en blanc
        textField.setVisible(false);                                                                                        // passe à true une fois le search cliqué (invisible de base)
        add(textField);

        // bouton
        searchButton = new JButton() ;
        searchButton.addActionListener(this);
        searchButton.setBounds(275, 4, 40, 40);                                                           // placement du bouton relativement au panel de la classe
        ImageIcon iconSearchButton = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/searchBar.png"));
        searchButton.setIcon(iconSearchButton);
        searchButton.setBorderPainted(false);                                                                               // bordure transparente
        searchButton.setFocusPainted(false);                                                                                // pas d'affichage du focus
        searchButton.setContentAreaFilled(false);                                                                           // bouton transparent
        add(searchButton) ;
    }

    /**
     * ajout de l'affichage des informations relatives à la ville
     * pattern recherché : Sierre
     */

    public void infoCity(){
        // Panel Ville
        panelVille.setOpaque(false);
        panelVille.setBounds(17, 50, 303, 20);                                                            // les bounds sont égaux à la dimention du smartphone
        panelVille.setLayout(new GridBagLayout());                                                                          // on crée un JPanel avec un GridBagLayout afin de pouvoir centrer le futur label
        add(panelVille) ;

        // Label Ville
        String stringVille = weather.getVille();
        JLabel labelVille = new JLabel(stringVille) ;
        labelVille.setForeground(Color.white);
        labelVille.setFont(locationFont);
        panelVille.add(labelVille) ;                                                                                        // le label sera donc placé au centre du Panel grâce au GridBagLayout
    }

    /**
     * ajout de l'affichage des informations relatives à la date/heure
     * pattern recherché : dimanche 13 juin 23:44
     */

    public void infoTime(){
        panelTime.setOpaque(false);
        panelTime.setBounds(17, 80, 303, 20);
        panelTime.setLayout(new GridBagLayout());                                                                           // gridBagLayout pour centrer
        add(panelTime);
        String stringTime = timeGeneral.getDate() ;                                                                         // va chercher la date dans la classe Time
        stringTime += " " ;
        stringTime += timeGeneral.getMonth() ;                                                                              // va chercher le mois dans la classe Time
        stringTime += " " ;
        stringTime += timeGeneral.getTime() ;                                                                               // va chercher l'heure dans la classe Time
        JLabel time = new JLabel(stringTime) ;
        time.setForeground(Color.lightGray);
        time.setFont(timeFont);
        panelTime.add(time) ;
    }

    /**
     * ajout de l'affichage des informations relatives à la température et à l'icône du temps actuel
     * pattern recherché : (image temps) 13°
     */

    public void infoTemperatureAndWeather(){
        // Label température
        String[] contentTemperatureActuelle = weather.getTemperature() ;                                                    // va chercher le tableau de string contenant les informations de la température
        String stringTemperatureActuelle = contentTemperatureActuelle[0] ;                                                  // va chercher le string recherché à l'intérieur du tableau de string (ici à la position 0 du tableau)
        stringTemperatureActuelle = stringTemperatureActuelle.substring(5) ;                                                // découpage du string pour ne laisser que le nombre
        double doubleTemperatureActuelle = Double.parseDouble(stringTemperatureActuelle);                                   // méthode pour convertir les Strings en double
        JLabel actualTemperature = new JLabel((int)doubleTemperatureActuelle + "°");                                   // on force le int dans le label pour ne garder que ce qui nous intéresse
        actualTemperature.setBounds(170, 90, 150, 130);
        actualTemperature.setFont(bigTemperatureFont);
        actualTemperature.setForeground(Color.white);
        add(actualTemperature);

        // Label icone
        JLabel labelImage = new JLabel();
        String[] contentStringIcon = weather.getWeather() ;                                                                 // va chercher le tableau de string contenant les informations de la météo (ici retourne le tableau contenant la météo)
        stringIcon = contentStringIcon[3] ;                                                                                 // va chercher le string du numéro de l'icone de la météo actuelle
        stringIcon = stringIcon.substring(6) ;                                                                              // découpage du string de l'image à choisir
        String stringIconPath = "Images/WeatherIcon/" ;                                                                     // path sans l'image
        stringIconPath += stringIcon ;                                                                                      // rajout de l'image à choisir et du .png
        stringIconPath += ".png" ;                                                                                          // rajout de la terminaison du path au string
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource(stringIconPath));                                     // on va chercher l'image avec le lien que l'on vient de créer
        labelImage.setIcon(image);
        labelImage.setBounds(55, 100, 120, 100);
        add(labelImage);
    }

    /**
     * ajout de l'affichage des informations relatives à la température minimum/maximum
     * pattern recherché : 30°/17° Feels like 31°
     */

    public void infoMinMaxTemp(){
        panelMaxMinTemp.setOpaque(false);
        panelMaxMinTemp.setBounds(17, 210, 303, 20);
        panelMaxMinTemp.setLayout(new GridBagLayout());                                                                     // panel gridBagLayout pour centrer
        add(panelMaxMinTemp);

        String[] contentMaxMinTemp = weather.getTemperature() ;                                                             // même principe que plus haut mais répété 3x
        String stringMaxTemp = contentMaxMinTemp[3];                                                                        // va chercher la température maximale et l'ajoute à un string
        stringMaxTemp = stringMaxTemp.substring(10);
        double doubleMaxTemp = Double.parseDouble(stringMaxTemp) ;
        String stringMinTemp = contentMaxMinTemp[2];                                                                        // va chercher la température minimale et l'ajoute au string
        stringMinTemp = stringMinTemp.substring(10);
        double doubleMinTemp = Double.parseDouble(stringMinTemp) ;
        String stringFeelsLike = contentMaxMinTemp[1];                                                                      // va chercher la température ressentie et l'ajoute au string
        stringFeelsLike = stringFeelsLike.substring(12);
        double doubleFeelsLike = Double.parseDouble(stringFeelsLike) ;
        JLabel maxMinTemp = new JLabel((int)doubleMaxTemp + "°/" + (int)doubleMinTemp + "° Feels like " + (int)doubleFeelsLike + "°") ;     // ajout dans le jlabel avec quelques modifications
        maxMinTemp.setForeground(liightGray);                                                                               // modifie la couleur du texte (légèrement gris)
        maxMinTemp.setFont(moyenFont);
        panelMaxMinTemp.add(maxMinTemp) ;                                                                                   // ajout du label dans le panel
    }

    /**
     * ajout de l'affichage des informations relatives à la description de la météo actuelle
     * pattern recherché : clear sky
     */

    public void infoDescriptionWeather(){
        // panel
        panelDescription.setOpaque(false);                                                                                  // panel transparent
        panelDescription.setBounds(17, 247, 303, 20);
        panelDescription.setLayout(new GridBagLayout());                                                                    // gridBagLayout pour centrer
        add(panelDescription);

        // label
        String[] stringDescriptionContent = weather.getWeather() ;                                                          // va chercher le tableau de string
        String stringDescription = stringDescriptionContent[2] ;                                                            // va chercher le string dans le tableau
        stringDescription = stringDescription.substring(13) ;                                                               // fait un substring qui va rogner les parties non voulues du string
        JLabel labelDescription = new JLabel(stringDescription) ;
        labelDescription.setForeground(Color.white);
        labelDescription.setFont(moyenFont);
        panelDescription.add(labelDescription) ;
    }

    /**
     * ajout de l'affichage des informations relatives au lever/coucher du soleil
     * pattern recherché : (image lever/coucher) 05:37 GMT+02:00
     */

    public void infoSunriseAndSunset(){
        // label sunrise
        JLabel labelSunrise = new JLabel();
        ImageIcon imageSunrise = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/sunrise.png"));
        labelSunrise.setIcon(imageSunrise);
        labelSunrise.setBounds(40, 275, 100, 100);
        add(labelSunrise) ;

        // texte sunrise
        String[] stringContentSunrise = weather.getSun() ;                                                                  // va chercher et tronque ce qu'il se trouve dans le tableau de string
        String stringSunrise = stringContentSunrise[3];
        stringSunrise = stringSunrise.substring(9) ;
        intStringSunrise = Integer.parseInt(stringSunrise) ;                                                                // transformation de string en int grâce à la méthode .parseInt
        stringSunrise = timeGeneral.unixToDate(intStringSunrise) ;                                                          // changement de secondes unix en String (méthode dans General.Time)
        JLabel labelStringSunrise = new JLabel(stringSunrise) ;                                                             // ajout du string final dans un JLabel
        labelStringSunrise.setForeground(Color.white);                                                                      // changement de la couleur à blanc
        labelStringSunrise.setFont(timeFont);
        labelStringSunrise.setBounds(90, 307, 150, 30);
        add(labelStringSunrise) ;

        // icone sunset
        JLabel labelSet = new JLabel();
        ImageIcon imageSet = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/sunset.png"));           // classLoader permet d'aller chercher le lien du system ressource relatif à sa position dans notre ordinateur
        labelSet.setIcon(imageSet);
        labelSet.setBounds(40, 322, 100, 100);
        add(labelSet) ;

        // texte sunset
        String[] stringContentSunset = weather.getSun() ;
        String stringSunset = stringContentSunset[4] ;
        stringSunset = stringSunset.substring(8) ;
        intStringSunset = Integer.parseInt(stringSunset);                                                                   // va chercher et transforme les heures (voir sunrise plus haut)
        stringSunset = timeGeneral.unixToDate(intStringSunset);                                                             // fonction qui transforme les heures unix en heures compréhensibles
        JLabel labelStringSunset = new JLabel(stringSunset) ;
        labelStringSunset.setForeground(Color.white);
        labelStringSunset.setFont(timeFont);
        labelStringSunset.setBounds(90, 355, 150, 30);
        add(labelStringSunset) ;
    }

    /**
     * ajout de l'affichage des informations relatives à l'humidité
     * pattern recherché : (image humidité) humidité : 60%
     */

    public void infoHumidity(){
        // icone humidité
        JLabel labelHumidite = new JLabel() ;
        ImageIcon iconHumidite = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/humidity.png"));
        labelHumidite.setIcon(iconHumidite);
        labelHumidite.setBounds(52, 390, 50, 50);
        add(labelHumidite);

        // label humidité
        String[] humiditeContent = weather.getTemperature() ;
        String humiditeString = humiditeContent[5] ;
        humiditeString = humiditeString.substring(10) ;
        humiditeString = humiditeString.substring(0,humiditeString.length()-1) ;
        JLabel labelStringHumidite = new JLabel("humidité : " + humiditeString + "%") ;                                 // ajout dans le JLabel et transformation pour match le pattern voulu
        labelStringHumidite.setForeground(Color.white);
        labelStringHumidite.setFont(timeFont);
        labelStringHumidite.setBounds(90, 390, 150, 50);
        add(labelStringHumidite) ;
    }

    /**
     * ajout de l'affichage des informations relatives à l'affichage de la météo par heures en bas de la page
     * pattern recherché :
     * (24:00) (3:00)  (6:00)  (9:00)  (12:00)
     * (image) (image) (image) (image) (image)
     */

    public void infoHourly(){
        String stingGetTime = timeGeneral.getTime() ;                                                                       // création d'un string avec l'heure actuelle
        stingGetTime = stingGetTime.substring(0, stingGetTime.length()-3) ;                                                 // tronque afin de garder que les heures (08h20 --> 08)
        int intStingGetTime = Integer.parseInt(stingGetTime) ;                                                              // transforme le résultat en int grâce à la méthode Integer.parseInt(string)
        int intActualHour = intStingGetTime ;                                                                               // création d'une variable possédant ce résultat
        intStingGetTime++ ;                                                                                                 // heure +1 pour démarrer les guess depuis l'heure suivante (8 --> 9)
        int hBounds = 48 ;                                                                                                  // création d'une variable qui a la valeur horizontale de la base
        JLabel hourlyLabelHour ;                                                                                            // création du JLabel

        // JLabels heures
        for (int i = 0 ; i < 5 ; i++){                                                                                      // boucle for qui va s'occuper de créer 5 labels
            hourlyLabelHour = new JLabel(""+ intStingGetTime + ":00");                                                 // initialisation du JLabel
            hourlyLabelHour.setForeground(Color.white);                                                                     // changement de la couleur du JLabel
            hourlyLabelHour.setFont(timeFont);                                                                              // changement de la police du JLabel
            hourlyLabelHour.setBounds(hBounds, 460, 50, 50);                                                 // hBounds s'incrémente à chaque passage, c'est la seule variable qui change dans setBounds.
            add(hourlyLabelHour) ;
            hBounds += 50 ;                                                                                                 // incrémentation du hBounds
            if((intStingGetTime == 21) || (intStingGetTime ==  22) || (intStingGetTime == 23) || (intStingGetTime == 24)){ // jour suivant => remise à 0 (gère le changement de jours)
                intStingGetTime = intStingGetTime-24 ;                                                                      // on enlève 24(h) au compteur des heures
                intStingGetTime += 3 ;                                                                                      // on incrémente les heures de 3 heures (20h --> 23h)

            }
            else{
                intStingGetTime += 3 ;                                                                                      // si pas de changement de jour, seulement incrémentation des heures (+3)
            }
        }

        // JLabels icones
        JLabel labelImagesDuBas ;
        ImageIcon iconImageDuBas ;
        hBounds = 40 ;
        String stringGetHoursSunset = timeGeneral.unixToHours(intStringSunset) ;
        int intGetHoursSunset = Integer.parseInt(stringGetHoursSunset) ;                                                    // les heures du coucher/lever soleil servent à adapter l'icone à l'heure
        String stringGetHoursSunrise = timeGeneral.unixToHours(intStringSunrise) ;                                          // exemple (coucher de soleil à 22h donc 21h = iconeJour, 23h = iconeNuit
        int intGetHoursSunrise = Integer.parseInt(stringGetHoursSunrise) ;
        String stringGetIconPath ;                                                                                          // construction du lien qui sera variable en fonction de l'heure (voir plus haut)
        stringIcon = stringIcon.substring(0, stringIcon.length()-1) ;                                                       // on coupe le "d" ou "n" après car il sera désormais variable selon l'heure
        intActualHour = intActualHour+1 ;                                                                                   // reinitialisation de l'heure actuelle

        for(int i = 0 ; i < 5 ; i++){
            labelImagesDuBas = new JLabel() ;
            stringGetIconPath = "Images/WeatherIcon/";                                                                      // on reset le path à chaque passage
            if((intActualHour>=intGetHoursSunrise) && (intActualHour<=intGetHoursSunset)){                                  // on choisit le mode jour ou nuit de l'icone en fonction du lever/coucher du soleil
                // icone jour
                stringGetIconPath += stringIcon + "d.png" ;                                                                 // si c'est le jour, on rajoute alors "d" au path (plus la terminaison .png)
                iconImageDuBas = new ImageIcon(ClassLoader.getSystemResource(stringGetIconPath)) ;                          // classLoader qui va chercher le chemin relatif à notre ordinateur
                Image imageDuBas = iconImageDuBas.getImage();                                                               // changer l'icone en image afin de pouvoir la rétrécir (image trop grande de base)
                Image newimg = imageDuBas.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);              // rétrécissement de l'image grâce au .getScaledInstance
                iconImageDuBas = new ImageIcon(newimg);
            }
            else{
                // icone nuit
                stringGetIconPath += stringIcon + "n.png" ;                                                                 // si c'est la nuit, on rajoute alors "n" au path (plus la terminaison .png)
                iconImageDuBas = new ImageIcon(ClassLoader.getSystemResource(stringGetIconPath)) ;
                Image imageDuBas = iconImageDuBas.getImage();
                Image newimg = imageDuBas.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);              // on rétrécit l'image (voir plus haut)
                iconImageDuBas = new ImageIcon(newimg);
            }
            labelImagesDuBas.setIcon(iconImageDuBas);                                                                       // ajout de l'image au JLabel et positionnement de celui-ci
            labelImagesDuBas.setBounds(hBounds, 490, 200, 50);
            add(labelImagesDuBas) ;
            hBounds += 50 ;                                                                                                 // incrémentation du hBounds pour décaler les images
            if((intActualHour == 21) || (intActualHour ==  22) || (intActualHour == 23) || (intActualHour == 24)){          // jour suivant => remise à 0
                intActualHour = intActualHour-24 ;                                                                          // on enlève 24(h) au compteur si changement de jour
                intActualHour += 3 ;

            }
            else{
                intActualHour += 3 ;                                                                                        // si pas de changement de jour, incrémentation des heures (+3)
            }
        }
    }

    /**
     * ajout de l'affichage du background (image de fond) + deux images (formes) par dessus
     */

    public void backgroundApp(){
        //label background du haut
        JLabel backgroundTop = new JLabel();
        ImageIcon imageBT = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/backgroundTop.png"));
        backgroundTop.setIcon(imageBT);
        backgroundTop.setBounds(28, 195, 303, 350);
        add(backgroundTop);

        // label background du bas
        JLabel backgroundBottom = new JLabel();
        ImageIcon imageBB = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/backgroundBottom.png"));
        backgroundBottom.setIcon(imageBB);
        backgroundBottom.setBounds(28, 335, 303, 350);
        add(backgroundBottom);

        // background image de fond
        JLabel backgroundImage = new JLabel();
        ImageIcon iconBackgroundImage = new ImageIcon(ClassLoader.getSystemResource("Images/WeatherIcon/meteoBackground.png"));
        backgroundImage.setIcon(iconBackgroundImage);
        backgroundImage.setBounds(10, 0, 330, 600);
        add(backgroundImage);
    }

    /**
     * ActionListener
     * @param e
     * recharge la page en cas de nouvelle recherche de ville
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == searchButton) {
                if (textField.getText().equals("")) {                                                                       // premier clic sur le bouton : il affiche la search bar
                    textField.setVisible(true);                                                                             // si la searchBar est vide, elle se rend visible
                } else {                                                                                                    // sinon (c'est que c'est pas la première fois que l'on appuye sur le bouton)
                    Internet internet = new Internet() ;                                                                    // appel de la classe internet
                    if(internet.isReachableByPing("api.openweathermap.org")){                                          // test d'accès à un site internet
                        new Smartphone(new WeatherWindow(textField.getText()), new TopBarWeatherApp());                     // si on a accès à internet, on refresh la page avec le nom de la nouvelle ville
                    }
                    else{
                        throw new SmartphoneException("Pas de connexion Internet", ErrorCode.CONNEXION_ERROR) ;             // si pas d'internet, une nouvelle erreur est balancée
                    }
                }
            }
        }catch (SmartphoneException sme) {                                                                                  // try catch qui entoure la re-création du smartphone
            System.out.println(sme.getErrorMessage());                                                                      // va chercher les erreurs éventuelles qui pourraient arriver
            System.out.println(sme.getErrorCode());                                                                         // va chercher le code de l'erreur
        }
    }
}

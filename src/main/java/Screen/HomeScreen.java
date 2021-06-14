package Screen;
import Apps.Contacts.ContactWindow;
import Demo.Smartphone ;
import Apps.Gallery.GalleryWindow;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import General.Google;
import General.Internet;
import General.Time;
import Apps.Weather.WeatherWindow;
import TopBar.TopBarColor;
import TopBar.TopBarWeatherApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeScreen extends JPanel implements ActionListener {
    // Variables générales
    private final Time timeGeneral = new Time();
    private JButton buttonWeather ;
    private JButton buttonContact ;
    private JButton buttonGallery ;
    private JButton searchBar ;

    // Variables de polices et de couleur
    private final Font timeGrisMini = new Font("Roboto", Font.PLAIN, 12) ;
    private final Font timeGrisGrand = new Font("Roboto", Font.PLAIN, 26) ;
    private final Color black = Color.BLACK;

    /**
     * Constructeur
     */

    public HomeScreen() {
        setLayout(null);                    // set le layout du panel a null afin de pouvoir placer les éléments grâce à setBounds

        // ajout des éléments graphiques
        setSmartphoneShape();               // Ajout du contour du smartphone
        infoGreyPanel() ;                   // Date et heure panel grisé
        applicationsButtons();              // ajout des icones des applications et du texte des applications
        googleSearchButton();               // Bouton google search
        backgroundPanel();                  // ajout du background

    }

    /**
     * ajout du contour latéral du smartphone
     */

    public void setSmartphoneShape() {
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));  // va chercher l'image relativement à son positionnement sur votre orginateur
        JLabel labelContourSmartphone = new JLabel();                                                                       // création du JLabel dans lequel l'image sera ajouté
        labelContourSmartphone.setIcon(iconContourSmartphone);                                                              // ajout de l'image dans le JLabel
        labelContourSmartphone.setBounds(9, -8, 320, 600);                                                // positionnement du JLabel relativement au pannel HomeScreen
        add(labelContourSmartphone);                                                                                        // ajout du JLabel dans le JPanel HomeScreen
    }

    /**
     * ajout du panel gris en haut de l'écran
     * contenu : la date et le jour à droite, l'heure à droite
     */

    public void infoGreyPanel(){
        // Jour gauche
        JLabel labelJourGauche = new JLabel(timeGeneral.getDate());                                                         // va chercher la date dans la classe Time crée en variable plus haut
        labelJourGauche.setForeground(black);                                                                               // choix de la couleur du texte du label
        labelJourGauche.setFont(timeGrisMini);                                                                              // choix de la police du label (initialisée dans les variables tout en haut)
        labelJourGauche.setBounds(50,55, 100, 20);                                                        // positionnement du JLabel relativement au HomeScreen
        add(labelJourGauche);                                                                                               // ajout du JLabel dans le panel du HomeScreen

        // Date gauche
        JLabel labelDateGauche = new JLabel(timeGeneral.getMonth());                                                        // répétition de ce même processus pour la date
        labelDateGauche.setForeground(black);
        labelDateGauche.setFont(timeGrisMini);
        labelDateGauche.setBounds(50,72, 100, 20);
        add(labelDateGauche);

        // Heure droite
        JLabel labelHeureDroite = new JLabel(timeGeneral.getTime());                                                        // répétition de ce même processus pour l'heure'
        labelHeureDroite.setForeground(black);
        labelHeureDroite.setFont(timeGrisGrand);
        labelHeureDroite.setBounds(220,65, 100, 20);
        add(labelHeureDroite);
    }

    /**
     * ajout des applications sur l'écran
     * pattern recherché :
     * (image) (image) (image)
     * (titre) (titre) (titre)
     */

    public void applicationsButtons(){
        // Bouton GalleryApp
        ImageIcon iconeGallery = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeGallery.png"));            // ajout d'une image comme mentionné plus haut
        buttonGallery = new JButton();
        setButtonShape(buttonGallery);                                                                                      // modification de la forme du bouton grâce à une méthode crée plus bas
        buttonGallery.addActionListener(this);                                                                           // ajout de l'action listener au bouton
        buttonGallery.setIcon(iconeGallery);                                                                                // ajout de l'image au bouton
        buttonGallery.setBounds(60, 340, 50, 50);                                                         // positionnement du bouton
        add(buttonGallery);                                                                                                 // ajout du bouton au panel HomeScreen

        // Bouton ContactsApp
        ImageIcon iconeContact = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeContact.png"));  // répétition pour l'application de contacts
        buttonContact = new JButton();
        setButtonShape(buttonContact);
        buttonContact.addActionListener(this);
        buttonContact.setIcon(iconeContact);
        buttonContact.setBounds(230, 340, 50, 50);
        add(buttonContact);

        // Bouton WeatherApp
        ImageIcon iconeWeather = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeWeather.png"));   // répétition pour l'application météo
        buttonWeather = new JButton();
        setButtonShape(buttonWeather);
        buttonWeather.addActionListener(this);
        buttonWeather.setIcon(iconeWeather);
        buttonWeather.setBounds(147, 340, 50, 50);
        add(buttonWeather);

        // Texte Application WeatherApp
        JLabel labelTexteAppWeather = new JLabel("Weather");
        labelTexteAppWeather.setForeground(Color.white);
        labelTexteAppWeather.setFont(timeGrisMini);
        labelTexteAppWeather.setBounds(150, 370, 50, 50);
        add(labelTexteAppWeather);

        // Texte Application ContactsApp
        JLabel labelTexteAppContacts = new JLabel("Contacts");
        labelTexteAppContacts.setForeground(Color.white);
        labelTexteAppContacts.setFont(timeGrisMini);
        labelTexteAppContacts.setBounds(230, 370, 50, 50);
        add(labelTexteAppContacts);

        // Texte Application GalleryApp
        JLabel labelTexteAppGallery = new JLabel("Gallery");
        labelTexteAppGallery.setForeground(Color.white);
        labelTexteAppGallery.setFont(timeGrisMini);
        labelTexteAppGallery.setBounds(66, 370, 50, 50);
        add(labelTexteAppGallery);
    }

    /**
     * ajout de la google search bar en bas de l'écran
     * image : google search bar
     */

    public void googleSearchButton(){
        ImageIcon iconSearchBar = new ImageIcon(ClassLoader.getSystemResource("Images/googleSearchBar_PNG.png"));     // ajout du bouton de l'application Google
        searchBar = new JButton();
        setButtonShape(searchBar);
        searchBar.addActionListener(this);
        searchBar.setIcon(iconSearchBar);
        searchBar.setBounds(22, 450, 300, 50);
        add(searchBar);
    }

    /**
     * ajout du background au pannel Homescreen
     * Forme grise au dessus
     * Image en dessous
     */

    public void backgroundPanel(){
        // Forme grise en haut
        ImageIcon iconThingOnTop = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/grayThingOnTop.png"));
        JLabel labelThingOnTop = new JLabel();
        labelThingOnTop.setIcon(iconThingOnTop);
        labelThingOnTop.setBounds(42, 0, 290, 150);
        add(labelThingOnTop);

        // Image background
        ImageIcon imageBackround = new ImageIcon(ClassLoader.getSystemResource("Images/wallpaper_PNG.png"));
        JLabel labelBackground = new JLabel();
        labelBackground.setIcon(imageBackround);
        labelBackground.setBounds(16,0, 304, 600);
        add(labelBackground);
    }

    /**
     *
     * @param button
     * Méthode pour modifier l'apparence des boutons (évite d'écrire ces 3 lignes plusieurs fois)
     */

    public void setButtonShape(JButton button) {
        button.setBorderPainted(false);                                                                                     // enlève les bordures des JButtons/JLabels
        button.setFocusPainted(false);                                                                                      // enlève l'affichage du focus
        button.setContentAreaFilled(false);                                                                                 // rend les JButtons/JLabels transparents
    }

    /**
     *
     * @param e
     * ActionListener, permet d'accéder aux différentes applications disponibles sur le HomeScreen
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == buttonWeather) {
                Internet internet = new Internet() ;                                                                        // appel de la classe Internet
                if(internet.isReachableByPing("api.openweathermap.org")){                                              // check si l'ordinateur peut accéder au site api.openweathermap.org
                    new Smartphone(new WeatherWindow("Sierre"), new TopBarWeatherApp());                             // action d'ouvrir l'application météo, ville de base = Sierre
                }
                else{
                    throw new SmartphoneException("Pas de connexion internet", ErrorCode.CONNEXION_ERROR) ;                 // si pas de connexion, on balance une nouvelle erreur
                }
            }
            if (e.getSource() == buttonContact) {
                new Smartphone(new ContactWindow(), new TopBarColor(new Color(0, 0, 0)));                          // action d'ouvrir l'application de contacts
            }
            if (e.getSource() == buttonGallery) {
                new Smartphone(new GalleryWindow(), new TopBarColor(black));                                                // action d'ouvrir l'application de la gallerie
            }
            if (e.getSource() == searchBar) {
                new Smartphone(new Google(), new TopBarWeatherApp());                                                       // action d'ouvrir l'application de Google
            }
        }catch (SmartphoneException sme) {
            System.out.println(sme.getErrorMessage());                                                                      // affiche les possibles messages d'erreurs liés à la création d'un nouveau smartphone
            System.out.println(sme.getErrorCode());                                                                         // affiche les possibles codes d'erreurs liés à la création d'un nouveau smartphone
        }
    }
}

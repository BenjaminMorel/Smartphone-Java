package Screen;
import Apps.Contacts.ContactWindow;
import Demo.Smartphone ;
import Apps.Gallery.GalleryWindow;
import Errors.SmartphoneException;
import General.Google;
import General.Time;
import Apps.Weather.WeatherWindow;
import TopBar.TopBarColor;
import TopBar.TopBarWeatherApp;

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
    private JButton buttonContact ;
    private JButton buttonGallery ;
    private JButton searchBar ;
    private ImageIcon iconeGallery;

    // Fonts
    private Font timeGrisMini = new Font("Roboto", Font.PLAIN, 12) ;
    private Font timeGrisGrand = new Font("Roboto", Font.PLAIN, 26) ;
    private Color black = Color.BLACK;

    public HomeScreen() {
        setLayout(null);

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Date et heure panel grisé
        // Jour gauche
        JLabel labelJourGauche = new JLabel(timeGeneral.getDate());
        labelJourGauche.setForeground(black);
        labelJourGauche.setFont(timeGrisMini);
        labelJourGauche.setBounds(50,55, 100, 20);
        add(labelJourGauche);

        // Date gauche
        JLabel labelDateGauche = new JLabel(timeGeneral.getMonth());
        labelDateGauche.setForeground(black);
        labelDateGauche.setFont(timeGrisMini);
        labelDateGauche.setBounds(50,72, 100, 20);
        add(labelDateGauche);

        // Heure droite
        JLabel labelHeureDroite = new JLabel(timeGeneral.getTime());
        labelHeureDroite.setForeground(black);
        labelHeureDroite.setFont(timeGrisGrand);
        labelHeureDroite.setBounds(220,65, 100, 20);
        add(labelHeureDroite);

        // Bouton GalleryApp
        iconeGallery = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeGallery.png"));
        buttonGallery = new JButton();
        setButtonShape(buttonGallery);
        buttonGallery.addActionListener(this);
        buttonGallery.setIcon(iconeGallery);
        buttonGallery.setBounds(60, 340, 50, 50);
        add(buttonGallery);

        // Bouton ContactsApp
        ImageIcon iconeContact = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeContact.png"));
        buttonContact = new JButton();
        setButtonShape(buttonContact);
        buttonContact.addActionListener(this);
        buttonContact.setIcon(iconeContact);
        buttonContact.setBounds(230, 340, 50, 50);
        add(buttonContact);

        // Bouton WeatherApp
        ImageIcon iconeWeather = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/iconeWeather.png"));
        buttonWeather = new JButton();
        setButtonShape(buttonWeather);
        buttonWeather.addActionListener(this);
        buttonWeather.setIcon(iconeWeather);
        buttonWeather.setBounds(147, 340, 50, 50);
        add(buttonWeather);

        // Bouton google search
        ImageIcon iconSearchBar = new ImageIcon(ClassLoader.getSystemResource("Images/googleSearchBar_PNG.png"));
        searchBar = new JButton();
        setButtonShape(searchBar);
        searchBar.addActionListener(this);
        searchBar.setIcon(iconSearchBar);
        searchBar.setBounds(22, 450, 300, 50);
        add(searchBar);

        // Ajout du texte du nom des applications, sous les applications
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

        // Set background
        // Forme grise en haut
        ImageIcon iconThingOnTop = new ImageIcon(ClassLoader.getSystemResource("Images/HomeScreen/grayThingOnTop.png"));
        JLabel labelThingOnTop = new JLabel();
        labelThingOnTop.setIcon(iconThingOnTop);
        labelThingOnTop.setBounds(42, 0, 290, 150);
        add(labelThingOnTop);

        // Image background
        imageBackround = new ImageIcon(ClassLoader.getSystemResource("Images/wallpaper_PNG.png"));
        JLabel labelBackground = new JLabel();
        labelBackground.setIcon(imageBackround);
        labelBackground.setBounds(16,0, 304, 600);
        add(labelBackground);

    }

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

    public void setButtonShape(JButton button) {
        // Méthode pour modifier l'apparence des boutons (évite d'écrire ces 3 lignes plusieurs fois)
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == buttonWeather) {
                switchApp = new Smartphone(new WeatherWindow("Sierre"), new TopBarWeatherApp());
            }
            if (e.getSource() == buttonContact) {
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(new Color(0, 0, 0)));
            }
            if (e.getSource() == buttonGallery) {
                switchApp = new Smartphone(new GalleryWindow(), new TopBarWeatherApp());
            }
            if (e.getSource() == searchBar) {
                switchApp = new Smartphone(new Google(), new TopBarWeatherApp());
            }
        }catch (SmartphoneException sme) {
            System.out.println(sme.getErrorMessage());
            System.out.println(sme.getErrorCode());
        }
    }

}

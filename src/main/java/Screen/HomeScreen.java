package Screen;
import Contacts.ContactWindow;
import Demo.Smartphone ;
import General.Google;
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
    private JButton buttonContact ;
    private JButton buttonGallery ;
    private JButton searchBar ;

    // fonts
    private Font timeGrisMini = new Font("Roboto", Font.PLAIN, 12) ;
    private Font timeGrisGrand = new Font("Roboto", Font.PLAIN, 26) ;

    public HomeScreen() {
        setLayout(null);

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone) ;

        // date et heure panel grisé
        // jour gauche
        JLabel labelJourGauche = new JLabel(timeGeneral.getDate()) ;
        labelJourGauche.setForeground(Color.black);
        labelJourGauche.setFont(timeGrisMini);
        labelJourGauche.setBounds(50,55, 100, 20);
        add(labelJourGauche) ;

        // date gauche
        JLabel labelDateGauche = new JLabel(timeGeneral.getMonth()) ;
        labelDateGauche.setForeground(Color.black);
        labelDateGauche.setFont(timeGrisMini);
        labelDateGauche.setBounds(50,72, 100, 20);
        add(labelDateGauche) ;

        // heure droite
        JLabel labelHeureDroite = new JLabel(timeGeneral.getTime()) ;
        labelHeureDroite.setForeground(Color.black);
        labelHeureDroite.setFont(timeGrisGrand);
        labelHeureDroite.setBounds(220,65, 100, 20);
        add(labelHeureDroite) ;

        // bouton Gallery
        ImageIcon iconeGallery = new ImageIcon("src/main/java/Images/HomeScreen/iconeGallery.png") ;
        buttonGallery = new JButton() ;
        buttonGallery.setBorderPainted(false);
        buttonGallery.setFocusPainted(false);
        buttonGallery.setContentAreaFilled(false);
        buttonGallery.addActionListener(this);
        buttonGallery.setIcon(iconeGallery);
        buttonGallery.setBounds(60, 340, 50, 50);
        add(buttonGallery) ;

        // bouton ContactsApp
        ImageIcon iconeContact = new ImageIcon("src/main/java/Images/HomeScreen/iconeContact.png") ;
        buttonContact = new JButton() ;
        buttonContact.setBorderPainted(false);
        buttonContact.setFocusPainted(false);
        buttonContact.setContentAreaFilled(false);
        buttonContact.addActionListener(this);
        buttonContact.setIcon(iconeContact);
        buttonContact.setBounds(230, 340, 50, 50);
        add(buttonContact) ;

        // bouton weatherapp
        ImageIcon iconeWeather = new ImageIcon("src/main/java/Images/HomeScreen/iconeWeather.png") ;
        buttonWeather = new JButton() ;
        buttonWeather.setBorderPainted(false);
        buttonWeather.setFocusPainted(false);
        buttonWeather.setContentAreaFilled(false);
        buttonWeather.addActionListener(this);
        buttonWeather.setIcon(iconeWeather);
        buttonWeather.setBounds(147, 340, 50, 50);
        add(buttonWeather) ;


        // bouton google search
        ImageIcon iconSearchBar = new ImageIcon("src/main/java/Images/googleSearchBar_PNG.png") ;
        searchBar = new JButton() ;
        searchBar.setBorderPainted(false);
        searchBar.setFocusPainted(false);
        searchBar.setContentAreaFilled(false);
        searchBar.addActionListener(this);
        searchBar.setIcon(iconSearchBar);
        searchBar.setBounds(22, 450, 300, 50);
        add(searchBar) ;


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
        labelBackground.setBounds(16,0 , 304, 600);
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
        if(e.getSource() == buttonContact){
            switchApp = new Smartphone(new ContactWindow()) ;
        }
        if(e.getSource() == buttonGallery){

        }
        if(e.getSource() == searchBar){
            switchApp = new Smartphone(new Google()) ;
        }
    }

}

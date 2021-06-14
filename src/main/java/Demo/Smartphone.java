package Demo;


import Errors.SmartphoneException;
import General.Time;
import Screen.BottomBar;
import TopBar.TopBarHomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class Smartphone extends JFrame {

    // création de la taille et de la forme du smartphone
    private static final Dimension SIZE = new Dimension(320, 690) ;
    private RoundRectangle2D.Double SHAPE = new RoundRectangle2D.Double(16, 14, 305, 640, 75, 75) ;

    // Création de diverses variables générales
    private Time timer = new Time() ;
    private TopBarHomeScreen topBar = new TopBarHomeScreen() ;
    private BottomBar bottomBar = new BottomBar() ;
    private static JPanel contenuApp = new JPanel() ;
    private static JPanel app ;
    private static JPanel topBarTP ;
    private static JPanel contenuTopBar = new JPanel() ;

    // Cardlayout va spécifier comment le smartphone va fonctionner
    private static CardLayout cardLayout = new CardLayout() ;
    private static CardLayout cardLayoutTP = new CardLayout() ;
    private InitialisationSmartphone initialisationSmartphone = new InitialisationSmartphone();

    /**
     * Méthode principale du smartphone, elle s'occupe de l'initialiser et de changer d'application
     * @param newApp
     * si newApp est null, c'est que le smartphone n'est pas initialiser, va donc procéder à l'initialisation
     * si newApp contient une application (JPanel), va procéder au switch de cette même application
     * @param newTopBar
     * si newTopBar est null, c'est que la topBar n'est pas initialisée, va donc procéder à l'initialisation
     * si newTopBar contient une topBar (JPanel), va procéder au switch de cette même topBar
     * @throws SmartphoneException
     * permet d'escalader les erreurs possibles
     */

    public Smartphone(JPanel newApp, JPanel newTopBar) throws SmartphoneException {
        if(newApp == null){                                                                                                 // si newApp est null, il faut donc initialiser le smartphone
            app = initialisationSmartphone.getHomeScreen() ;                                                                // initialise le panel de base, va chercher ce pannel dans la classe InitialisationSmartphone car sinon crée des classes infiniment
            contenuApp.add(app);                                                                                            // ajout de cette même application dans le cardLayout
            topBarTP = initialisationSmartphone.getTopBarHomeScreen();                                                      // initialise la top bar de base comme pour le panel de base plus haut
            contenuTopBar.add(topBarTP) ;                                                                                   // ajoute la topBar dans le cardLayout de la topBar

            setPreferredSize(SIZE);                                                                                         // Création de la JFrame
            setUndecorated(true);                                                                                           // enlève la barre du haut (avec la croix)
            setShape(SHAPE);                                                                                                // prend la shape qu'on lui a donné dans la variable SHAPE tout en haut de la classe
            setVisible(true);                                                                                               // affiche la JFrame

            contenuApp.setLayout(cardLayout);                                                                               // contenuApp est le panel qui va acueillir les applications, on lui met un cardLayout pour pouvoir par la suite mieux switch ces mêmes applications
            contenuTopBar.setLayout(cardLayoutTP);                                                                          // on fait de même pour la topBar

            setLayout(new BorderLayout());                                                                                  // la JFrame est un borderLayout séparé en 3 compartiments
            add(contenuTopBar, BorderLayout.NORTH) ;                                                                        // la topBar est au nord
            add(contenuApp, BorderLayout.CENTER) ;                                                                          // le contenu des applications se trouve au centre
            add(bottomBar, BorderLayout.SOUTH) ;                                                                            // la bottomBar contenant le bouton central se trouve au sud

            pack();
            setLocationRelativeTo(null);                                                                                    // on positionne le smartphone au centre de l'écran
        }
        else{
            switchApplication(newApp, newTopBar);                                                                           // si newApp contient une application, c'est que l'on doit changer d'application, on fait donc appel à la méthode switchApplication (description plus bas)
        }
    }

    /**
     * Méthode permettant de changer d'application en application
     * @param newApp
     * Nouveau panel voulant être ajouté
     * @param newTopBar
     * Nouvelle topBar voulant être ajoutée
     */

    public void switchApplication(JPanel newApp, JPanel newTopBar){
        // changement de l'app
        contenuApp.remove(app);                                                                                             // 1. enlève l'application actuelle du cardLayout contenuApp
        app = newApp ;                                                                                                      // 2. change l'application avec la nouvelle application
        contenuApp.add(app);                                                                                                // 3. ajoute la nouvelle app au cardLayout contenuApp
        cardLayout.next(contenuApp);                                                                                        // 4. utilise la méthode .next afin de passer de l'ancien layout au nouveau contenant la nouvelle application

        // changement de la topBar
        contenuTopBar.remove(topBarTP);                                                                                     // même processus que plus haut mais pour la TopBar
        topBarTP = newTopBar ;
        contenuTopBar.add(topBarTP);
        cardLayoutTP.next(contenuTopBar);
    }



}

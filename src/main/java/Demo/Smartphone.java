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

    // rajouter le contenu de l'image de base plus du futur contenu des applications
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


    public Smartphone(JPanel newApp, JPanel newTopBar) throws SmartphoneException {
        if(newApp == null){
            // initialise le panel de base
            app = initialisationSmartphone.getHomeScreen() ;
            contenuApp.add(app);

            // initialise la top bar de base
            topBarTP = initialisationSmartphone.getTopBarHomeScreen();
            contenuTopBar.add(topBarTP) ;

            // Création de la JFrame
            setPreferredSize(SIZE);
            setUndecorated(true);
            setShape(SHAPE);
            setVisible(true);

            // contenuApp et le vieux panel qui va acueillir les applications et autres
            contenuApp.setLayout(cardLayout);
            contenuTopBar.setLayout(cardLayoutTP);

            setLayout(new BorderLayout());
            add(contenuTopBar, BorderLayout.NORTH) ;
            add(contenuApp, BorderLayout.CENTER) ;
            add(bottomBar, BorderLayout.SOUTH) ;

            pack();
            setLocationRelativeTo(null);
        }
        else{
            switchApplication(newApp, newTopBar);
        }
    }

    public void switchApplication(JPanel newApp, JPanel newTopBar){
        // changement de l'app
        contenuApp.remove(app);
        app = newApp ;
        contenuApp.add(app);
        cardLayout.next(contenuApp);

        // changement de la topBar
        contenuTopBar.remove(topBarTP);
        topBarTP = newTopBar ;
        contenuTopBar.add(topBarTP);
        cardLayoutTP.next(contenuTopBar);
    }



}

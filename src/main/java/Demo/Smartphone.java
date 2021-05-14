package Demo;


import General.CloseButton;
import General.Time;
import Screen.BottomBar;
import Screen.HomeScreen;
import Screen.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class Smartphone extends JFrame {

    // création de la taille et de la forme du smartphone
    private static final Dimension SIZE = new Dimension(400, 690) ;
    private RoundRectangle2D.Double SHAPE = new RoundRectangle2D.Double(16, 14, 305, 640, 75, 75) ;

    // rajouter le contenu de l'image de base plus du futur contenu des applications
    private TopBar topBar = new TopBar() ;
    private BottomBar bottomBar = new BottomBar() ;
    private static JPanel contenuApp = new JPanel() ;
    private static JPanel app ;
    private Image contourSmartPhone ;
    private HomeScreen homeScreen ;

    // Cardlayout va spécifier comment le smartphone va fonctionner
    private static CardLayout cardLayout = new CardLayout() ;

    private boolean initialise = false ;

    public Smartphone(JPanel app) {
        this.app=app;

        // Création de la JFrame
        setPreferredSize(SIZE);
        setUndecorated(true);
        setShape(SHAPE);
        setVisible(true);

        // contenuApp et le vieux panel qui va acueillir les applications et autres
        contenuApp.setLayout(cardLayout);

        // on ajoute l'application (input de la classe) au contenu
        contenuApp.add(app);

        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH) ;
        add(contenuApp, BorderLayout.CENTER) ;
        add(bottomBar, BorderLayout.SOUTH) ;

        // importer image smartphone
        contourSmartPhone = new ImageIcon("src/main/java/Images/smartphone_PNG.png").getImage() ;

        pack();
        setLocationRelativeTo(null);

    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g ;

        g2D.drawImage(contourSmartPhone, 10, 10, null) ;
    }


    public void switchApplication(JPanel newApp){
        contenuApp.remove(app) ;
        app = newApp ;
        contenuApp.add(app) ;
        cardLayout.next(contenuApp);

    }




}
